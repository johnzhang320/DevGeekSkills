package com.loan.agent.mvc.controller;

import java.security.PublicKey;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.basic.hibernate.dao.AgentTable;
import com.frontend.encrypt.utils.KeyPairManager;
import com.frontend.encrypt.utils.ui; 

import com.loan.agent.mvc.validator.AgentLoginValidator;

/**
* Class level Controller for login form controlling
* @author johnzhang
*
*/
@Controller
@RequestMapping("/")
public class AgentLoginController  {
    Logger LOG = Logger.getLogger( AgentLoginController.class);
	
    AgentLoginValidator agentLoginValidator;

	@Autowired
	public AgentLoginController( AgentLoginValidator  agentLoginValidator){
		this.agentLoginValidator =  agentLoginValidator;
	}	 
	@RequestMapping(value={"/agentLogin.html"},method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("agentTable") AgentTable agentTable,			
			BindingResult result, SessionStatus status,
			Model model,
			HttpServletRequest request
		)	throws Exception { 
		
		LOG.info("AgentLoginController prcessSubmit() begin");
		/**
		 *  before Validation, initialize locale 
		 */
		
		agentLoginValidator.initializeValidator(request);
		/**
		 *  Validation
		 */
		
		agentLoginValidator.validate(agentTable, result);
		
		if (result.hasErrors()) {
			return "AgentLogin";
		} else {
			/**
			 *  Decrypt the encrypted string of front end for login third party server 
			 *  and hashed descrypted string to save it to database or persist it
			 */
			String encrypted =agentTable.getPassword();	
			String decrypted = KeyPairManager.getInstance().decrypt(encrypted);	
			String publicKey = KeyPairManager.getInstance().getKeyPair().getPublic().toString();
			LOG.info("PublicKey String="+publicKey);
			
			String hashedPassword=ui.convertToSHA256(decrypted);
			LOG.info("Encrypted Password="+encrypted+"\n,decrypted password="+decrypted+"\n Hashed Password="+ hashedPassword);
			/**
			 *  Demo encryption , decryption and hashed in AgentLoginSuccess page
			 */
			agentTable.setEncryptedPassword(encrypted);
			agentTable.setDecryptedPassword(decrypted);	  
			agentTable.setHashedPassword(hashedPassword);	
			agentTable.setPublicKey(publicKey);
		    
		    request.getSession().setAttribute("agentTable", agentTable);		 
			
		    LOG.info("AgentLoginController processSubmit() end");
	 		
		    status.setComplete();
		    /**
		     *  Redirect to protect retry submitted form
		     */
		    return "redirect:agentLoginSuccess.html"; 
		}
		 
	}
    @RequestMapping(value={"/"},method = RequestMethod.GET)
    public String InitForm(Model model, HttpServletRequest request)
		throws Exception {
	    LOG.info("InitForm() begin");
	
	    AgentTable agentTable = (AgentTable) request.getSession().getAttribute("agentTable");
	    if (null == agentTable) {
		    agentTable = new AgentTable();
	    }
	    model.addAttribute("agentTable",agentTable);
	
	    LOG.info("InitForm end");
	    /**
	     *  Return to tile definition name: AgentLogin defined in tiles-spring.xml
	     */
	    return "AgentLogin";
    } 
	
	
	 
}
