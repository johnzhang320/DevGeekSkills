package com.loan.agent.mvc.controller;

 
import java.util.HashMap; 
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.validation.BindException; 
import org.springframework.web.servlet.ModelAndView; 
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.loan.agent.dao.Agents;
import com.loan.agent.mvc.formbean.AgentForm; 

public class AgentSignupController extends SimpleFormController {
 static final Logger LOG = Logger.getLogger(AgentSignupController.class);

    public AgentSignupController() {
		setCommandName("agentForm");
		setCommandClass(AgentForm.class);
 	}	
 	 
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
	 
	    LOG.info("formBackingObject begin");
		
		AgentForm agentForm = (AgentForm) request.getSession().getAttribute("agentForm");
		if (null == agentForm) {
			agentForm = new AgentForm();
		}
		LOG.info("formBackingObject end");
		return agentForm;
	} 
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 
		LOG.info("AgentSignupController OnSubmit() begin");
	
		AgentForm agentForm = (AgentForm) command;
		
		Agents agents = agentForm.modelAgents();
		
		LOG.info("Save Agent to Database ..... user email is "+agentForm.getEmailAddress());
		 
		/**
		 *  Redirect handler mapping AgentSignupSuccessRedirect is defined in spring-views.properties
		 */
		ModelAndView mode = new ModelAndView("AgentSignupSuccessRedirect");
		
		mode.addObject("agentForm", agentForm);		
	 
		request.getSession().setAttribute("agentForm", agentForm);
			
		LOG.info("AgentSignupController OnSubmit() end");
	 		
	 
		return mode; 
		 
	}	
	
	@Override
	public Map referenceData(HttpServletRequest request) {
		
		Map referenceData = new HashMap(); 
		 
		Map<String,String> stateMap = new HashMap<String,String>();
		
		stateMap.put("AL","Alabama");
		stateMap.put("AK","Alaska");
		stateMap.put("AZ","Arizona");
		stateMap.put("AR","Arkansas");
		stateMap.put("CA","California");
		stateMap.put("CO","Colorado");
		stateMap.put("CT","Connecticut");
		stateMap.put("DE","Delaware");
		stateMap.put("DC","District of Columbia");
		stateMap.put("FL","Florida");
		stateMap.put("GA","Georgia");
		stateMap.put("HI","Hawaii");
		stateMap.put("ID","Idaho");
		stateMap.put("IL","Illinois");
		stateMap.put("IN","Indiana");
		stateMap.put("IA","Iowa");
		stateMap.put("KS","Kansas");
		stateMap.put("KY","Kentucky");
		stateMap.put("LA","Louisiana");
		stateMap.put("ME","Maine");
		stateMap.put("MD","Maryland");
		stateMap.put("MA","Massachusetts");
		stateMap.put("MI","Michigan");
		stateMap.put("MN","Minnesota");
		stateMap.put("MS","Mississippi");
		stateMap.put("MO","Missouri");
		stateMap.put("MT","Montana");
		stateMap.put("NE","Nebraska");
		stateMap.put("NV","Nevada");
		stateMap.put("NH","New Hampshire");
		stateMap.put("NJ","New Jersey");
		stateMap.put("NM","New Mexico");
		stateMap.put("NY","New York");
		stateMap.put("NC","North Carolina");
		stateMap.put("ND","North Dakota");
		stateMap.put("OH","Ohio");
		stateMap.put("OK","Oklahoma");
		stateMap.put("OR","Oregon");
		stateMap.put("PA","Pennsylvania");
		stateMap.put("PR","Puerto Rico");
		stateMap.put("RI","Rhode Island");
		stateMap.put("SC","South Carolina");
		stateMap.put("SD","South Dakota");
		stateMap.put("TN","Tennessee");
		stateMap.put("TX","Texas");
		stateMap.put("UT","Utah");
		stateMap.put("VT","Vermont");
		stateMap.put("VA","Virginia");
		stateMap.put("WA","Washington");
		stateMap.put("WV","West Virginia");
		stateMap.put("WI","Wisconsin");
		stateMap.put("WY","Wyoming");
		 
		referenceData.put("stateMap", stateMap);		
 		 
		return referenceData;
	}
 
	
} 

