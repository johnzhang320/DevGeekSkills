package com.loan.agent.mvc.validator; 

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors; 
import org.springframework.validation.Validator;

import com.basic.hibernate.dao.AgentTable;
import com.basic.hibernate.dao.AgentTableDAO;
import com.locale.message.utils.LocaleMsg;
import com.locale.message.utils.ValidateHelp;
@Service
public class AgentSignupValidator extends ValidateHelp implements Validator {
    
	@Override 
	public boolean supports(Class givenClass)
	{
	      return givenClass.equals(AgentTable.class);
	}
    
	/**
	 * Called by submit form method
	 * public abstract void initializeValidator(HttpServletRequest request) 
	 * in ValidateHelp;
	 */
	 
	public void initializeValidator(HttpServletRequest request) {
		initilize(request);
	}
	
	public void validate(Object obj, Errors errors) {
		 	
	      addField("emailAddress","label.email.address");
	      addField("ipAddress","label.ip.address");
	      addField("ipAddressRange","label.ip.address");
	      addField("password","label.password");
	      addField("companyName","label.company.name");
	      addField("workPhone","label.work.phone");
	      addField("state","label.state"); 	
	      errors =isAnyFieldsEmpty(errors);
	      
	      isValidEmail("emailAddress","label.email.address", errors);
	      isValidPhone("workPhone","label.work.phone",errors);
	      isValidState("state","label.state",errors);
	        
	      
	      isXSSAttack("address","label.address.street",errors);
	      isXSSAttack("city","label.city",errors);
	      isXSSAttack("cellPhone","label.cell.phone",errors);
	      isXSSAttack("zipCode", "label.zip.code",errors);
	        
	      isValidIP("ipAddressRangeSample","label.ip.address.range.sample", errors);
	      isValidIP("ipAddressRange","label.ip.address.range", errors);
	      isValidIP("ipAddress","label.ip.address", errors);
	      
	      String cidr =(String) errors.getFieldValue("ipAddressRangeSample");	
	      
	      if (cidr==null || cidr.length()==0) {
	    	  cidr = "172.12.32.75/24";
	      }
	      isValidIPRange("ipAddressRange","label.ip.address", cidr, errors);
	      
	      this.isValidPhone("cellPhone","label.cell.phone",errors);
	      
	      this.isValidPostalCode("zipCode", "label.zip.code", errors);      
	    
	      
	 }
	 
}
