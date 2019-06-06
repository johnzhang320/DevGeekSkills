package com.loan.agent.mvc.utils;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.loan.agent.services.Constant;

 
public class WebApplicationListener
	implements ServletContextListener
{
	private static final Logger LOGGER = Logger.getLogger (WebApplicationListener.class);
     
	public static Date appStartupTime = null;
	private static ServletContext appServletContext;

	/* Application Startup Event */
	public void contextInitialized (ServletContextEvent ce)
	{
		appStartupTime = new Date ();
		LOGGER.info ("*************************************");
		LOGGER.info ("App started on " + appStartupTime);
		LOGGER.info ("*************************************");
		
		/**
		 *  System initialization
		 */
		ServiceManager.start ();
		
		/*
		 *  Create System data directory
		 */
        SysPath.getInstance();
		
		LOGGER.info ("webapp context created webapp info- servlet context name "
			+ ce.getServletContext ().getServletContextName () + " server info="
			+ ce.getServletContext ().getServerInfo ());
		
	/*	String realPath = ce.getServletContext ().getRealPath("/");
		String pdfFiles = realPath.replace("\\", "/")+Constant.PDF_BASE_DIR;
		File file = new File(pdfFiles);
		
		if (file.isDirectory()) {
			File [] pdfs= file.listFiles();
			for (int i = 0; i<pdfs.length;i++) {
				pdfs[i].delete();
			}
		}*/
	}
	public void contextDestroyed (ServletContextEvent ce)
	{
		ServiceManager.stop ();
		LOGGER.info ("webapp context destroyed webapp info- servlet context name "
			+ ce.getServletContext ().getServletContextName () + " server info="
			+ ce.getServletContext ().getServerInfo ());
	}
	 
}
