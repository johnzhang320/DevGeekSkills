package com.loan.agent.mvc.utils;

import org.apache.log4j.Logger;

import com.loan.agent.services.LookupDataInitialService;

 
public class ServiceManager
{
	private static Logger LOGGER = Logger.getLogger (ServiceManager.class.getName ());
	private static LookupDataInitialService lookupData;
	/**
	 * In the final implementation of this class, all the services implementing
	 * interface <code>com.apprion.service.DaemonService </code> will be
	 * started from this method. List of the services will be passed through lookupData
	 * beans from configuration.
	 */
	public static void start ()
	{

		try {
			 lookupData =(LookupDataInitialService) SpringFramework.getBean("LookupDataInitialService");
			 lookupData.initialize();
			 
		} catch (Exception e) {
			LOGGER.info ("Fail Initialize Lookup Data!");
			LOGGER.error (e);
		}
	}
	public static void stop ()
	{

		try {
			 lookupData =(LookupDataInitialService) SpringFramework.getBean("LookupDataInitialService");
			 lookupData.cleanUp();
			
		} catch (Exception e) {
			LOGGER.info ("Fail Initialize Lookup Data!");
			LOGGER.error (e);
		}
	}
}