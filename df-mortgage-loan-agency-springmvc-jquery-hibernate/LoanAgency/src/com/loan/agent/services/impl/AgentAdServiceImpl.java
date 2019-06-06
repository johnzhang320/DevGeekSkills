package com.loan.agent.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.loan.agent.calculators.vo.AgentIdVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AgentsDAO;
import com.loan.agent.dao.Niches;
import com.loan.agent.dao.NichesDAO;
import com.loan.agent.dao.RateSheet;
import com.loan.agent.dao.RateSheetDAO;
import com.loan.agent.mvc.formbean.NicheForm;
import com.loan.agent.mvc.utils.SpringFramework;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;
import com.loan.agent.services.AgentAdService;
import com.loan.agent.services.QuoteDBService;

import org.apache.log4j.*;

public class AgentAdServiceImpl implements AgentAdService {
	Logger LOG=Logger.getLogger(AgentAdServiceImpl.class);
	
	 
	private QuoteDBService quoteDBService;	
	
	
	
	public QuoteDBService getQuoteDBService() {
		return quoteDBService;
	}

	public void setQuoteDBService(QuoteDBService quoteDBService) {
		this.quoteDBService = quoteDBService;
	}

	public void setAgentProfile(HttpServletRequest request,Integer agentId) {
			
		
		 //   LOG.info("setAgentProfile() Begin"); 
		  
		    request.getSession().setAttribute(Constant.AGENT_ID,agentId);	
		    
		    String turnOffNiche = (String) request.getSession().getAttribute(Constant.TURN_OFF_NICHE);
		    
		    quoteDBService = (QuoteDBService) SpringFramework.getBean("QuoteDBService");
			
			Agents agents = quoteDBService.getAgentsDAO().findById(agentId);
			
		 
			if (agents==null) {
				LOG.info("agentId is wrong !");
				return;
				
			}
			//LOG.info("agents="+agents.getFirstName()+",save agents to session!"); 
			/*String experience = agents.getDescription();
			if (!Utility.isEmpty(experience)) {
				experience = experience.replace("\n", "");
				agents.setDescription(experience);
				LOG.info("Experience = "+experience);
			}*/
			
			request.getSession().setAttribute(Constant.AGENT_PROFILE, agents );
			
			RateSheetDAO rateSheetDao = quoteDBService.getRateSheetDAO();
		    
			List<RateSheet> list = rateSheetDao.findByAgentId(agentId);
			
			//LOG.info("Rate List.size()="+list.size());
			 
			if (list==null || list.size()==0) {
				LOG.info("List<RateSheet> list =null");
				 
			} else {
				 	
				publishRateToSession(request, list);
			}
			
			
			/**
			 *  Fetch Niche data
			 */
			 
			 
			 
					 List<NicheForm> nicheList = new ArrayList<NicheForm>();
					 NichesDAO nicheDAO = quoteDBService.getNichesDAO();
					 List<Niches> niches = nicheDAO.findByAgentId(agentId);					 
					 for (Niches n: niches) {
						 NicheForm nicheForm = new NicheForm();
						 nicheForm.renderNiche(n);
						 nicheList.add(nicheForm );
					 }
			    
					// LOG.info("nichesList.size()="+niches.size());
					 request.getSession().setAttribute(Constant.NICHES_LIST, nicheList);	
				 
		 				 
			 
			 
		}
			
	
	
	public AgentIdVo getAgentId(HttpServletRequest request) {
		
		AgentIdVo retVal= new  AgentIdVo();
		 
		Integer loginAgentId =(Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID); 
		Integer paramAgentId = null;  
		Integer agentId = null;
		 
		LOG.info("Login AgentId="+loginAgentId);
		if (loginAgentId==null) {
			 paramAgentId =  (Integer) request.getSession().getAttribute(Constant.PARAM_SAVED_AGENT_ID); 
				LOG.info("paramAgentId="+paramAgentId);
			 if (paramAgentId!=null) {
				 agentId =paramAgentId; 
				 retVal.setAgentId(paramAgentId);
				 retVal.setAgentIdType(Constant.PARAM_SAVED_AGENT_ID);
			 }
		} else {
			 agentId = loginAgentId;
			 retVal.setAgentId(loginAgentId);
			 retVal.setAgentIdType(Constant.LOGIN_AGENT_ID);
		}
		LOG.info("AgentId="+agentId);
		if (agentId!=null) {
			AgentsDAO agentDao = new AgentsDAO();
			
			LOG.info("agentDao ="+agentDao );
			Agents agents=agentDao.findById(agentId);
			retVal.setAgents(agents);
		}
		return retVal;
	}
	
	public byte[] getImageByteArray(Integer agentId, String fileType) {
		//LOG.info("getImageByteArray begin");
		/**
		 *  If no agent id is available , the pick up initialization agent id 
		 */
		if (agentId==null) {
			Agents agentInit = LookupDataInitialServiceImpl.getAgent();
			agentId= agentInit.getAgentId();
			
		}
		
		String imagePath = SysPath.getInstance().getAgentPicturePath();
		String [] extName={".jpg",".png",".gif",".jepg",".tiff"};
		byte[] bytes=null;
		
		
		File file= null;
		boolean found=false;
		for (int i=0;i<extName.length;i++) {
			String currentFile = imagePath+Constant.PREFIX_AGENT_PICTURE_FILE+agentId.toString()+extName[i];
		
			LOG.info("Current Picture FileName="+currentFile);
		
		     file= new File(currentFile);
		     if (file.exists()) {
		        found=true;
			    break;
		     }
		}
		if (!found) {
			LOG.info("Can not find the picture file! ");
			return null;
		}
		 FileInputStream fis=null;
		 ByteArrayOutputStream bos=null;
		// LOG.info("read image begin ");
		 try {
			   bos = new ByteArrayOutputStream();
		       byte[] buf = new byte[1024];
		       
		       fis= new FileInputStream(file);
		       
		       for (int readNum; (readNum = fis.read(buf)) != -1;) {
		          //Writes to this byte array output stream
		           bos.write(buf, 0, readNum); 
		       }
		       bytes = bos.toByteArray();       
		      // LOG.info("read image OK!  bytes size="+ bytes.length);
		 } catch (Exception e) {
			 LOG.info("Error read image byte! "+e.getMessage());
		 } finally {
			 if (fis!=null)
			 try {
				 fis.close();
				 bos.close();
			 } catch (Exception e)
			 {}
		 
		 }
	//	 LOG.info("getImageByteArray end");
		 return bytes;
	}
	 
	
	public void publishRateToSession(HttpServletRequest request,List<RateSheet> list) {
		List<RateSheet> cfList= this.getConformingList(list);
		List<RateSheet> scfList= this.getSuperConformingList(list);
		List<RateSheet> jbfList= this.getJumboList(list);
		
		request.getSession().setAttribute(Constant.CONFORMING_RATE_LIST, cfList);
		request.getSession().setAttribute(Constant.SUPER_CONFORMING_RATE_LIST, scfList);
		request.getSession().setAttribute(Constant.JUMBO_RATE_LIST, jbfList);
		request.getSession().setAttribute(Constant.DUTY_AGENT_ONLY,null);
    }
    
    public List<RateSheet> getConformingList(List<RateSheet> list) {
    	 if (list==null || list.size()==0) {
    		 return null;
    	 }
    	 List<RateSheet> cList = new ArrayList<RateSheet>();
    	 
    	 for (RateSheet r:list) {
    		RateSheet v = new RateSheet();
     		v.setTerm(r.getTerm());
     	  	v.setConformRate(r.getConformRate()); 
     	  	v.setConformApr(r.getConformApr()); 
     	  	v.setLoanToValue(r.getLoanToValue());      	
            v.setCreditScore(r.getCreditScore()); 
            cList.add(v);
    	 }
         return cList;
    }
    
    public List<RateSheet> getSuperConformingList(List<RateSheet> list) {
   	 if (list==null || list.size()==0) {
   		 return null;
   	 }
   	 List<RateSheet> cList = new ArrayList<RateSheet>();
   	 
   	 for (RateSheet r:list) {
   		RateSheet v = new RateSheet();
    		v.setTerm(r.getTerm());
    	  	v.setSuperConformRate(r.getSuperConformRate()); 
    	  	v.setSuperConformApr(r.getSuperConformApr()); 
    	  	v.setLoanToValue(r.getLoanToValue());      	
           v.setCreditScore(r.getCreditScore()); 
           cList.add(v);
   	 }
        return cList;
   } 
    
    public List<RateSheet> getJumboList(List<RateSheet> list) {
   	 if (list==null || list.size()==0) {
   		 return null;
   	 }
   	 List<RateSheet> cList = new ArrayList<RateSheet>();
   	 
   	 for (RateSheet r:list) {
   		RateSheet v = new RateSheet();
    		v.setTerm(r.getTerm());
    	  	v.setJumboRate(r.getJumboRate()); 
    	  	v.setJumboApr(r.getJumboApr()); 
    	  	v.setLoanToValue(r.getLoanToValue());      	
           v.setCreditScore(r.getCreditScore()); 
           cList.add(v);
   	 }
        return cList;
   } 
	    
}
