package com.loan.agent.mvc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.basic.hibernate.dao.DmarcDetailReports;
import com.basic.hibernate.dao.DmarcMasterReports;
import com.basic.hibernate.service.DmarcReportService;
 


@Controller
public class OAuth2Controller {
	Logger Log = Logger.getLogger(OAuth2Controller.class);
	 
	
	//-------------------Retrieve default Range of Master Reports Data--------------------------------------------------------
    
    @RequestMapping(value = "/oauth2.html", method = RequestMethod.GET)
    public String listRangeMasterReports() {
    	
        return "OAuth2";
    }
 
    
}
