package com.loan.agent.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.basic.hibernate.dao.DmarcDetailReports;
import com.basic.hibernate.dao.DmarcMasterReports;
import com.basic.hibernate.pure.pojo.DetailPojo;
import com.basic.hibernate.pure.pojo.MasterPojo;
import com.basic.hibernate.service.DmarcReportService;
 


@RestController
public class DmarcRestfulController {
	Logger Log = Logger.getLogger(DmarcRestfulController.class);
	
	@Autowired
	DmarcReportService dmarcReportService;
	
	//-------------------Retrieve default Range of Master Reports Data--------------------------------------------------------
    
    @RequestMapping(value = "/masters.html/", method = RequestMethod.GET)
    public ResponseEntity<List<MasterPojo>> findRangeMasterReports() {
    	int masterId1 = 10357;
    	int masterId2 = 10400;
        List<DmarcMasterReports> results = dmarcReportService.getMasterReportsByMasterIdRange(masterId1, masterId2);
        Log.info("Fetching DmarcMasterReports with masterId1= " + masterId1+", masterId2="+masterId2);
         
        if(results.isEmpty()){
        	Log.info("DmarcMasterReports were not found");
            return new ResponseEntity<List<MasterPojo>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<MasterPojo> list =dmarcReportService.getMasterPojoList(results);
        return new ResponseEntity<List<MasterPojo>>(list, HttpStatus.OK);
    }
	//-------------------Retrieve specific Range of Master Reports Data--------------------------------------------------------
    
    @RequestMapping(value = "/masters.html/{id1}/{id2}", method = RequestMethod.GET)
    public ResponseEntity<List<MasterPojo>> findSpecifiedRangeMasterReports(@PathVariable("id1") int masterId1, @PathVariable("id2") int masterId2) {
    	 
        List<DmarcMasterReports> results = dmarcReportService.getMasterReportsByMasterIdRange(masterId1, masterId2);
       // Log.info("Fetching DmarcMasterReports with masterId1= " + masterId1+", masterId2="+masterId2);
         
        if(results.isEmpty()){
        	Log.info("DmarcMasterReports were not found");
            return new ResponseEntity<List<MasterPojo>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<MasterPojo> list =dmarcReportService.getMasterPojoList(results);
        return new ResponseEntity<List<MasterPojo>>(list, HttpStatus.OK);
    } 
//-------------------Retrieve specific Range of Master Reports Data Lazy--------------------------------------------------------
    
    @RequestMapping(value = "/masterslazy.html/{id1}/{id2}", method = RequestMethod.GET)
    public ResponseEntity<List<MasterPojo>> findSpecifiedRangeMasterReportsLazy(@PathVariable("id1") int masterId1, @PathVariable("id2") int masterId2) {
    	 
        List<DmarcMasterReports> results = dmarcReportService.getMasterReportsByMasterIdRange(masterId1, masterId2);
       // Log.info("Fetching DmarcMasterReports with masterId1= " + masterId1+", masterId2="+masterId2);
         
        if(results.isEmpty()){
        	Log.info("DmarcMasterReports were not found");
            return new ResponseEntity<List<MasterPojo>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<MasterPojo> list =dmarcReportService.getMasterPojoListLazy(results);
        return new ResponseEntity<List<MasterPojo>>(list, HttpStatus.OK);
    } 
	//-------------------Retrieve one Master Reports Data--------------------------------------------------------
    
    @RequestMapping(value = "/masters.html/{id}", method = RequestMethod.GET)
    public ResponseEntity<MasterPojo> findSingleMasterReports(@PathVariable("id") int id) {
    	 
        DmarcMasterReports results = dmarcReportService.getMasterReportsDAO().findById(id);
      //  Log.info("Fetching DmarcMasterReports with masterId1= " + id);
         
        if(null==results){
        	Log.info("DmarcMasterReports were not found");
            return new ResponseEntity<MasterPojo>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        
        return new ResponseEntity<MasterPojo>(new MasterPojo(results), HttpStatus.OK);
    } 
     
//-------------------Retrieve one Master Reports Data lazy (no detail data)--------------------------------------------------------
    
    @RequestMapping(value = "/masterslazy.html/{id}", method = RequestMethod.GET)
    public ResponseEntity<MasterPojo> findSingleMasterReportsLazy(@PathVariable("id") int id) {
   	 
        DmarcMasterReports results = dmarcReportService.getMasterReportsDAO().findById(id);
      //  Log.info("Fetching DmarcMasterReports with masterId1= " + id);
         
        if(null==results){
        	Log.info("DmarcMasterReports were not found");
            return new ResponseEntity<MasterPojo>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        
        return new ResponseEntity<MasterPojo>(new MasterPojo(results,true), HttpStatus.OK);
    } 
  //-------------------Retrieve Detail Reports Data by master_id--------------------------------------------------------
    
    @RequestMapping(value = "/details.html/{masterid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DetailPojo>> listDetailReports(@PathVariable("masterid") int masterId) {
    	 Log.info("Fetching DmarcDetailReports with masterId " + masterId);
        List<DmarcDetailReports> results = dmarcReportService.getDetailReportsByMasterId(masterId);
        if(results==null){
        	Log.info("DmarcDetailReports with masterId " + masterId + " not found");
            return new ResponseEntity<List<DetailPojo>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<DetailPojo>>(dmarcReportService.getDetailPojoList(results), HttpStatus.OK);
    }
  
//-------------------Retrieve Detail Reports Data by master_id range--------------------------------------------------------
    
    @RequestMapping(value = "/detailsMasterIdRange.html/{masterid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DetailPojo>> listDetailReportsByMasterRange(@PathVariable("masterid") int masterId) {
    	if (masterId<10357) {
          	Log.info("DmarcDetailReports with masterId " + masterId + " less than 10357");
            return new ResponseEntity<List<DetailPojo>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
 
    	}
    	Log.info("Fetching DmarcDetailReports with masterId " + masterId);
        List<DmarcMasterReports> results = dmarcReportService.getMasterReportsByMasterIdRange(10357, masterId);
        if(results==null){
        	Log.info("DmarcDetailReports with masterId " + masterId + " not found");
            return new ResponseEntity<List<DetailPojo>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<DetailPojo> res = new ArrayList<DetailPojo>();
        for (DmarcMasterReports d: results) {
        	res.addAll(dmarcReportService.getDetailPojoList(d.getDmarcDetailReportses()));
        }
        return new ResponseEntity<List<DetailPojo>>(res, HttpStatus.OK);
    }
}
