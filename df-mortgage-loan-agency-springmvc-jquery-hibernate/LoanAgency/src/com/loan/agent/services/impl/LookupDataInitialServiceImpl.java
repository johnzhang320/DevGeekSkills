package com.loan.agent.services.impl;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.annotation.Resource;
 

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AgentsDAO;
import com.loan.agent.dao.County;
import com.loan.agent.dao.CountyDAO;
 
import com.loan.agent.dao.State;
import com.loan.agent.dao.StateDAO;
import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.utils.AgentOnDutyTask;
import com.loan.agent.mvc.utils.ProcessUploadFile;
import com.loan.agent.mvc.utils.SpringFramework;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.services.AgentAdService;
import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;
import com.loan.agent.services.LookupDataInitialService;
import com.loan.agent.services.QuoteDBService;
import java.util.Timer; 

public class LookupDataInitialServiceImpl implements LookupDataInitialService{

	@Autowired
	@Resource(name="QuoteDBService")   // Resource does not support static
	private  QuoteDBService quoteDBService;
	
	@Autowired
	@Resource(name="AgentReviewService")	 
	private AgentReviewService agentReviewService;
	private Long timerPeriod=3000L;  // inject from spring bean AgentReviewService
	private static byte[] blobAsBytes;
	private static List <State> stateList;
	private static Map<String,String> stateMap = new LinkedHashMap<String,String>();
	private static Map <String,String>priceMap;
	private static Agents agent;
	private static final Logger LOG  = Logger.getLogger (LookupDataInitialServiceImpl.class);
	private static List<String> stateNames= new ArrayList<String> ();
	private static AgentForm agentForm=null;
	
	
	public void initialize()  {
	   
		LOG.info("Initialize Constant Data Such as State ! ");
	   
		StateDAO stateDao = (StateDAO)  SpringFramework.getBean("StateDAO");
	 
	 	stateList = stateDao.findAll();
	    
	 	 
	 	
	 	LOG.info(" State Row number = "+stateList.size());
	 	stateMap.put(" "," ");
		for(State s:stateList) {
			//LOG.info(s.getStateSymbol()+","+s.getStateName());
			stateMap.put(s.getStateSymbol(),s.getStateName());
			stateNames.add(s.getStateName());
		}
	    /**
	     *  Obtain next agent id following last on duty agent;
	     */
		agentReviewService = new AgentReviewServiceImpl();
		Integer agentId = agentReviewService.getAgentOnDuty();
		
		LOG.info("LookupDataInitialServiceImpl.initialize() agentID = "+agentId);
		/**
		 *   Refresh system holding on duty agent information
		 */
		fetchAgentOnDuty(agentId);
		
	//	Timer t = new Timer();
	//	t.schedule(new AgentOnDutyTask(), 360000000, 360000000); 
	}
	
	public void fetchAgentOnDuty(Integer agentId) {
		AgentsDAO agentsDao = (AgentsDAO)  SpringFramework.getBean("AgentsDAO");	 
		
		LookupDataInitialServiceImpl.agent =  agentsDao.findById(agentId);
		
		AgentAdService adHandler = new AgentAdServiceImpl();
		
		String agentPath = SysPath.getInstance().getAgentPicturePath();
		
		blobAsBytes = ProcessUploadFile.getFileByteArrayByAgent(agentId,LookupDataInitialServiceImpl.agent.getPictureFilename(), Constant.PREFIX_AGENT_PICTURE_FILE, agentPath);

		
		 
		 
	//Session session =quoteDBService.getAgentsDAO().getSessionFactory().getCurrentSession(); 
		//detach agent
	//session.evict(agent);
		
		 
	}
	public void cleanUp() {
		// when shotdown web container  , clean up all static data objects
		stateList =null;
		priceMap = null; 
		stateMap =null;
		blobAsBytes=null;
		agent=null;
		
	}
	
	public  Long getTimerPeriod() {
		return timerPeriod;
	}

	public static void setTimerPeriod(Long timerPeriod) {
		timerPeriod = timerPeriod;
	}

	public static void main(String args[]) {
		LookupDataInitialServiceImpl p = new LookupDataInitialServiceImpl();
				p.initialize();
	}

	public static List<State> getStateList() {
		return stateList;
	}

	public static void setStateList(List<State> stateList) {
		LookupDataInitialServiceImpl.stateList = stateList;
	}

	public static Map<String, String> getStateMap() {
		return stateMap;
	}

	public static void setStateMap(Map<String, String> stateMap) {
		LookupDataInitialServiceImpl.stateMap = stateMap;
	}

	public static Map<String, String> getPriceMap() {
		return priceMap;
	}

	public static void setPriceMap(Map<String, String> priceMap) {
		LookupDataInitialServiceImpl.priceMap = priceMap;
	}

	public QuoteDBService getQuoteDBService() {
		return quoteDBService;
	}

	public void setQuoteDBService(QuoteDBService quoteDBService) {
		this.quoteDBService = quoteDBService;
	}

	public static byte[] getBlobAsBytes() {
		return blobAsBytes;
	}

	public void setBlobAsBytes(byte[] blobAsBytes) {
		LookupDataInitialServiceImpl.blobAsBytes = blobAsBytes;
	}

	public static Agents getAgent() {
		return LookupDataInitialServiceImpl.agent;
	}

	public void setAgent(Agents agent) {
		LookupDataInitialServiceImpl.agent =  agent;
	}

	public static AgentForm getAgentForm() {
		return agentForm;
	}

	public static void setAgentForm(AgentForm agentForm) {
		LookupDataInitialServiceImpl.agentForm = agentForm;
	}

	public static List<String> getStateNames() {
		return stateNames;
	}

	public static void setStateNames(List<String> stateNames) {
		LookupDataInitialServiceImpl.stateNames = stateNames;
	}

	 

	 
	
	 

	 
 
	
}
