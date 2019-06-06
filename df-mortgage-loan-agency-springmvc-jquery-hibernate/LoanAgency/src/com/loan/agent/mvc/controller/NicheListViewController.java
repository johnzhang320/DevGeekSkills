package com.loan.agent.mvc.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.loan.agent.calculators.vo.CompareLoanVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.Niches;
import com.loan.agent.dao.NichesDAO;
import com.loan.agent.mvc.formbean.NicheForm;
import com.loan.agent.mvc.utils.ProcessUploadFile;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;

public class NicheListViewController extends MultiActionController {
	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService;
	
	static final Logger LOG = Logger.getLogger(NicheListViewController.class);
	
	public ModelAndView nicheListViewHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		LOG.info("viewNichesListHandler begin");
		
		ui.setRequest(request);
	
		Integer agentId =ui.getSessionAttributeInt(Constant.LOGIN_AGENT_ID);
		
		if (agentId==null) {
			//agentId=10001;
			return null;
		}
		 
		String nicheIntro = quoteDBService.findNicheIntroByAgentId(agentId);
		if (null==nicheIntro) {
			nicheIntro = " ";
		}
		ui.setSessionAttribute(Constant.NICHES_INTRO, nicheIntro);
		LOG.info("viewNichesListHandler nicheIntro="+nicheIntro); 
		
	    NichesDAO nicheDAO = quoteDBService.getNichesDAO();
	    List<Niches> niches = nicheDAO.findByAgentId(agentId);
	    List<NicheForm> nicheList = new ArrayList<NicheForm>();
	    for (Niches n: niches) {
	    	NicheForm nicheForm = new NicheForm();
	    	nicheForm.renderNiche(n);
	    	nicheList.add(nicheForm );
	    }
	    
	    LOG.info("nichesList.size()="+niches.size());
	    
	    request.getSession().setAttribute(Constant.NICHES_LIST, nicheList);
	   
	    ModelAndView mode = new ModelAndView(Constant.FORWARD_NICHES_LIST_VIEW);
		
	    LOG.info("viewNichesListHandler end");
		return mode;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView nicheIntroSaveHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		LOG.info("nichesIntroSaveHandler begin");
		
		ui.setRequest(request);
		
		Integer agentId = (Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID);
		
		String nicheIntro =ServletRequestUtils.getStringParameter(request,"nicheIntro");
		
		String successSaveMessage = "";
		
		LOG.info("nichesIntroSaveHandler agentId="+agentId+",nicheIntro="+nicheIntro);
		
		if (null==agentId || null==nicheIntro) {
			successSaveMessage = "No Agent login , please login as Agent! or nicheIntro ==null";
		} else {		 
			
		 	LOG.info("nichesIntroSaveHandler nicheIntro="+nicheIntro); 
			if (quoteDBService.updateNicheIntro(nicheIntro, agentId)) {
			
				successSaveMessage = "Successfully save niche description!";
			}
			
		}
		
		
		PrintWriter out= Utility.SetPostResponseContent(response);		  
	        
		Gson gson = new Gson();
	    JsonObject myObj = new JsonObject();	        
        JsonElement sElem = gson.toJsonTree(successSaveMessage);
        myObj.add(Constant.SUCCESS_MESSAGE, sElem);
        
        out.println(myObj.toString());       
        
        out.close();
        
       
	   
		
	    LOG.info("nichesIntroSaveHandler end");
		return null;
	}
}
