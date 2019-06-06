package com.loan.agent.mvc.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

 
public class SpringFramework
{
	private static final Logger LOGGER = Logger.getLogger (SpringFramework.class);
	
	public static final String APPLICATION_CONTEXT = "com.loan.agent.application";
	
	private static Map<String, BeanFactoryReference> factoryReferences = Collections.synchronizedMap (new HashMap<String, BeanFactoryReference> ());
	private static BeanFactoryLocator bfLocator = null;
	private static Boolean initialized = false;

	public static Object getBean (Class beanClass)
	{
		return getBean (APPLICATION_CONTEXT, beanClass.getName ());
	}
	
	/**
	 *  
	 * 
	 *
	 * @param beanRef
	 * @return
	 */
	public static Object getBean (String beanRef)
	{
		return getBean (APPLICATION_CONTEXT, beanRef);
	}

	public static Object getBean (String factoryName, String beanRef)
	{
		BeanFactoryReference bf = getBeanFactoryReference (factoryName);
		return bf.getFactory ().getBean (beanRef);
	}
	
	public static String[] getBeanNamesForType (Class type)
	{
		return getBeanNamesForType (APPLICATION_CONTEXT, type);
	}

	public static String[] getBeanNamesForType (String factoryName, Class type)
	{
		String[] beanNames = null;
		BeanFactoryReference bfr = getBeanFactoryReference (factoryName);
		BeanFactory bf = bfr.getFactory ();
		if (bf instanceof ApplicationContext) {
			ApplicationContext appCtx = (ApplicationContext) bf;
			beanNames = appCtx.getBeanNamesForType (type);
		}
		return beanNames;
	}

	public static BeanFactoryReference getBeanFactoryReference (String factoryName)
	{
		BeanFactoryReference bf = (BeanFactoryReference) factoryReferences.get (factoryName);
		if (bf == null) {
			bf = getBeanFactoryLocator ().useBeanFactory (factoryName);
			if (bf != null) {
				BeanFactoryReference bfr = (BeanFactoryReference) factoryReferences.put (
					factoryName, bf);
				if (bfr != null && bfr != bf) {
					bfr.release ();
				}
			}
		}
		return bf;
	}

	public static void destroyReferences ()
	{
		Iterator i = factoryReferences.values ().iterator ();
		while (i.hasNext ()) {
			BeanFactoryReference bfr = (BeanFactoryReference) i.next ();
			bfr.release ();
			i.remove ();
		}
	}

	public static BeanFactoryLocator getBeanFactoryLocator ()
	{
		if (bfLocator == null) {
			bfLocator = ContextSingletonBeanFactoryLocator.getInstance ();
		}
		return bfLocator;
	}

	public static void setBeanFactoryLocator (String beanRefAppContextFilename)
	{
		if (bfLocator == null) {
			bfLocator = ContextSingletonBeanFactoryLocator.getInstance (beanRefAppContextFilename);
		}
	}

	/**
	 * Propogate the given to all loaded applcationContext's
	 */
	public static void publishEvent (ApplicationEvent event)
	{
		if (!isInitialized ()) waitOnSpringInit ();
		// Iterate over references, and propogate event to each application
		// context found
		Iterator i = factoryReferences.values ().iterator ();
		while (i.hasNext ()) {
			BeanFactoryReference bfr = (BeanFactoryReference) i.next ();
			BeanFactory beanFactory = bfr.getFactory ();

			if (beanFactory instanceof ApplicationContext) {
				ApplicationContext appCtx = (ApplicationContext) beanFactory;
				appCtx.publishEvent (event);
				if (appCtx.getBeanDefinitionNames ()!=null)LOGGER.info ("stdm: appCtxName" + appCtx.getDisplayName () + Arrays.asList (appCtx.getBeanDefinitionNames ()));
			}
		}
	}
	
	public static Boolean isInitialized ()
	{
		return initialized;
	}
	
	public static void setInitialized (Boolean b)
	{
		initialized = b;
	}
	
	/**
	 *
	 */
	public static void waitOnSpringInit ()
	{
		while (!isInitialized ()) {
			LOGGER.info ("Spring initialization isn't finished.  waiting 10 seconds ...");
			// Wait 3 seconds and retry
			try { Thread.sleep (10000); } catch (InterruptedException e) {
				// Must've been called by cleanup so we stop.
				LOGGER.info ("waitOnSpringInit loop exiting ...");
				return;
			}
		}
		LOGGER.info ("Spring context initialization complete.  Resuming ...");
	}
	
}