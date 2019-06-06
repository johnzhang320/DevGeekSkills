package com.loan.agent.mvc.controller;

 
import java.util.HashMap;
import java.util.Map; 
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;

import com.basic.hibernate.dao.AgentTable;
import com.basic.hibernate.dao.AgentTableDAO;
import com.loan.agent.dao.Agents;
 
import com.loan.agent.mvc.validator.AgentSignupValidator;
 
 
/**
 * Class level Controller for form controlling
 * @author johnzhang
 *
 */
@Controller
@RequestMapping("/agentSignup.html")
public class AgentSignupController  {
 static final Logger LOG = Logger.getLogger(AgentSignupController.class); 	 
	@Autowired
    AgentSignupValidator agentSignupValidator;

	 
	
 	@RequestMapping(method = RequestMethod.GET)
	public String LoadData(Model model, HttpServletRequest request)
			throws Exception {
		LOG.info("LoadData() begin");
		
		AgentTable agentTable = (AgentTable) request.getSession().getAttribute("agentTable");
		if (null == agentTable) {
			agentTable = new AgentTable();
			agentTable.setIpAddressRangeSample("172.24.30.25/24");
		}
		model.addAttribute("agentTable",agentTable);
		
		LOG.info("LoadData() end");
		/**
		 *  Return to tile definition name: AgentSignup defined in tiles-spring.xml
		 */
		return "AgentSignup";
	} 
	
  
	@RequestMapping( method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("agentTable") AgentTable agentTable,
			BindingResult result, SessionStatus status,
			HttpServletRequest request
		)	throws Exception { 
		LOG.info("AgentSignupController prcessSubmit() begin");

		/**
		 *  before Validation, initialize locale 
		 */
		
		agentSignupValidator.initializeValidator(request);
		/**
		 *  Validation
		 */
		
		agentSignupValidator.validate(agentTable, result);
		
		if (result.hasErrors()) {
			return "AgentSignup";
		} else {
			AgentTableDAO dao = new AgentTableDAO();
			Session session = dao.getSession();
			try {
	 		    
	 		    session.beginTransaction();
	 		    dao.attachDirty(agentTable);
	 		    session.getTransaction().commit();
			} catch (Exception e) {
				session.getTransaction().rollback();
				LOG.error(e.getMessage());
			} finally {
				session.close();
			}
		
		    LOG.info("Save Agent to Database ..... user email is "+agentTable.getEmailAddress());
 	 
		    /**
		     *  Spring Command Model Data is available on page only,  cross pages must save command object to session scope
		     */
		    request.getSession().setAttribute("agentTable", agentTable);
			
		    LOG.info("AgentSignupController processSubmit() end");
	 		
		    status.setComplete();
		    
		    return "redirect:agentSignupSuccess.html"; 
		}
		 
	}
	 
	@ModelAttribute("stateMap")
	public Map populateStateMap() {
		
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
		return stateMap;
	}
} 

