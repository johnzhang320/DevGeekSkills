package com.loan.agent.mvc.controller;

 

 
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

 
import com.loan.agent.dao.AppCheckList;
import com.loan.agent.dao.AppCheckListDAO;
 
import com.loan.agent.mvc.formbean.AppCheckListForm;
 
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;

public class AppCheckListViewController extends MultiActionController {
	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService;
	
	static final Logger LOG = Logger.getLogger(AppCheckListViewController.class);
	
	public ModelAndView appCheckListViewHandler(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		LOG.info("AppCheckListsViewHandler begin");
		
		 
		 
	    AppCheckListDAO appCheckListDAO = quoteDBService.getAppCheckListDAO();
	    List<AppCheckList> appCheckList = appCheckListDAO.findAll();
	    List<AppCheckListForm> list = new ArrayList<AppCheckListForm>();
	    for (AppCheckList n: appCheckList) {
	    	AppCheckListForm appCheckListForm = new AppCheckListForm();
	    	appCheckListForm.render(n);
	    	list.add(appCheckListForm );
	    }
	    
	    LOG.info("appCheckList list.size()="+list.size());
	    
	    request.getSession().setAttribute(Constant.APP_CHECK_LIST_LIST, list);
	   
	    ModelAndView mode = new ModelAndView(Constant.FORWARD_CHECK_LIST_VIEW);
		
	    LOG.info("viewAppCheckListsListHandler end");
		return mode;
	}
 
}

