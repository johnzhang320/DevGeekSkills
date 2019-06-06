package com.loan.agent.services.impl;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.loan.agent.mvc.utils.SpringFramework;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.LookupDataInitialService;

public class AgentTurnSchedulerJob implements Job {
	private static final Logger LOG  = Logger.getLogger (AgentTurnSchedulerJob.class);

	//@Autowired
	//@Resource(name="AgentReviewService")	 
	private AgentReviewService agentReviewService;
	//@Autowired
	//@Resource(name="LookupDataInitialService")	 
	private LookupDataInitialService lookupDataInitialService;
	
	public void execute(JobExecutionContext context)
    {
		
		       try {
			    Calendar lCDateTime = Calendar.getInstance();
			    
				LOG.info("Trigger a Turn for Agent now! "+Utility.getCurrentTimeStamp());
				/**
				 *  get Next Agent Id of current Agent
				 */
				agentReviewService = (AgentReviewService) SpringFramework.getBean("AgentReviewService");
				
				Integer agentId = agentReviewService.getAgentOnDuty();
				/**
				 *  fetch this agent object for system 
				 */
				lookupDataInitialService = (LookupDataInitialService) SpringFramework.getBean("LookupDataInitialService");
				
				lookupDataInitialService.fetchAgentOnDuty(agentId);
				
				LOG.info("Trigger a Turn for Agent Finished.... "+Utility.getCurrentTimeStamp());
		        LOG.info("Now Agent Full Name="+LookupDataInitialServiceImpl.getAgent().getFirstName()+" "+
		        		LookupDataInitialServiceImpl.getAgent().getLastName());
		       } catch (Exception e) {
		    	   
		    	   e.printStackTrace();
		       }  
		        
		}
	 
	    public AgentReviewService getAgentReviewService() {
		return agentReviewService;
	}

	public void setAgentReviewService(AgentReviewService agentReviewService) {
		this.agentReviewService = agentReviewService;
	}

	public LookupDataInitialService getLookupDataInitialService() {
		return lookupDataInitialService;
	}

	public void setLookupDataInitialService(
			LookupDataInitialService lookupDataInitialService) {
		this.lookupDataInitialService = lookupDataInitialService;
	}

		public static void main( String[] args )  
	    {
	    	try {
	       	JobDetail job = new JobDetail();
	    	job.setName("AgentTurnJob");
	    	job.setJobClass(AgentTurnSchedulerJob.class);
	 
	    	//configure the scheduler time
	    	SimpleTrigger trigger = new SimpleTrigger();
	    	trigger.setName("AgentTurnSchedulerJobTrigger");
	    	trigger.setStartTime(new Date(System.currentTimeMillis() + 1000));
	    	trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
	    	trigger.setRepeatInterval(16000);
	 
	    	//schedule it
	    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
	    	scheduler.start();
	    	scheduler.scheduleJob(job, trigger);
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
}
