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
public class DmarcReportsController {
	Logger Log = Logger.getLogger(DmarcReportsController.class);
	@Autowired
	DmarcReportService dmarcReportService;
	
	//-------------------Retrieve default Range of Master Reports Data--------------------------------------------------------
    
    @RequestMapping(value = "/masterDetailRow.html", method = RequestMethod.GET)
    public String listRangeMasterReports() {
    	
        return "MasterDetailRow";
    }
    
	 
     
    
  //-------------------Retrieve Detail Reports Data by master_id--------------------------------------------------------
    
     
	public DmarcReportService getDmarcReportService() {
		return dmarcReportService;
	}

	public void setDmarcReportService(DmarcReportService dmarcReportService) {
		this.dmarcReportService = dmarcReportService;
	}
    
}
