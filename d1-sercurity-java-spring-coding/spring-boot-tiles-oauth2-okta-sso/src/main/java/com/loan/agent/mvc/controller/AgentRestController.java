package com.loan.agent.mvc.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.basic.hibernate.dao.AgentTable;
import com.basic.hibernate.service.AgentTableService;
 

@RestController
@RequestMapping("/")
public class AgentRestController {
	static Logger Log = Logger.getLogger(AgentRestController.class);
	@Autowired
	AgentTableService agentTableService;
	
	// ---------------Retrieve All Agents ----------------------------------
	@RequestMapping(value="/agents.html", method = RequestMethod.GET)
	public ResponseEntity<List<AgentTable>> findAllAgents() {
		List<AgentTable> list = agentTableService.getAgentTableDAO().findAll();
		if (list.isEmpty()) {
			Log.info("Data Not Found!");
			return new ResponseEntity<List<AgentTable>> (HttpStatus.NO_CONTENT); // 
		}
		Log.info("Data Found list size()="+list.size());
		return new ResponseEntity<List<AgentTable>> (list,HttpStatus.OK);
	}
	// ---------------Retrieve Single Agent -------------------------
	@RequestMapping(value="/agents.html/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgentTable> findAgentById(@PathVariable("id") int id) {
    	AgentTable list = agentTableService.getAgentTableDAO().findById(id);
		if (null==list) {
			Log.info("Data Not Found!");
			return new ResponseEntity<AgentTable> (HttpStatus.NO_CONTENT); // 
		}
		Log.info("Data Found Email="+list.getEmailAddress());
		return new ResponseEntity<AgentTable> (list,HttpStatus.OK);
    }
	 //-------------------Create a AgentTable--------------------------------------------------------
    
    @RequestMapping(value = "/agentcreate.html", method = RequestMethod.POST)
    public ResponseEntity<Void> createAgentTable(@RequestBody AgentTable agentTable,    UriComponentsBuilder ucBuilder) {
        Log.info("Creating AgentTable " + agentTable.getFullName()+", "+agentTable.getEmailAddress());
        boolean result=agentTableService.saveOrUpdate(agentTable);
        
        if (!result) {
            Log.info("A AgentTable with name " + agentTable.getFullName()+","+agentTable.getEmailAddress() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
                 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/agentTable/{id}").buildAndExpand(agentTable.getAgentId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a AgentTable --------------------------------------------------------
     
    @RequestMapping(value = "/agentupdate.html/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AgentTable> updateAgentTable(@PathVariable("id") int id, @RequestBody AgentTable agentTable) {
        Log.info("Updating AgentTable " + id);
         
        AgentTable currentAgentTable = agentTableService.getAgentTableDAO().findById(id);
         
        if (currentAgentTable==null) {
            Log.info("AgentTable with id " + id + " not found");
            return new ResponseEntity<AgentTable>(HttpStatus.NOT_FOUND);
        }
 
        currentAgentTable.setEmailAddress(agentTable.getEmailAddress());
        currentAgentTable.setFullName(agentTable.getFullName());
        currentAgentTable.setCompanyName(agentTable.getCompanyName());
         
        agentTableService.getAgentTableDAO().attachDirty(currentAgentTable);
        return new ResponseEntity<AgentTable>(currentAgentTable, HttpStatus.OK);
    }
 
    //------------------- Delete a AgentTable --------------------------------------------------------
     
    @RequestMapping(value = "/agentdelete.html/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AgentTable> deleteAgentTable(@PathVariable("id") int id) {
        Log.info("Fetching & Deleting AgentTable with id " + id);
 
        AgentTable agentTable = agentTableService.getAgentTableDAO().findById(id);
        if (agentTable == null) {
            Log.info("Unable to delete. AgentTable with id " + id + " not found");
            return new ResponseEntity<AgentTable>(HttpStatus.NOT_FOUND);
        }
 
        agentTableService.getAgentTableDAO().delete(agentTable);
        return new ResponseEntity<AgentTable>(HttpStatus.NO_CONTENT);
    }
  
}
