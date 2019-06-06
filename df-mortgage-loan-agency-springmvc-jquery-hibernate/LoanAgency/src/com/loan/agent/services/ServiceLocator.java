package com.loan.agent.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.naming.*;
import javax.sql.DataSource;

import org.apache.log4j.*;

/**
 * ServiceLocator is a singleton providing methods for retrieving Objects
 * from JNDI.
 * It does <b>not</b> implement a cache. For cache considerations see:
 * <ul>
 * <li><a href="http://www-106.ibm.com/developerworks/websphere/techjournal/0410_woolf/0410_woolf.html">Eliminate caching in service locator implementations in J2EE 1.3</a></li>
 * <li><a href="http://publib.boulder.ibm.com/infocenter/ws51help/index.jsp?topic=/com.ibm.websphere.base.doc/info/aes/ae/cnam_naming_caching.html">Websphere App Server 5.1.x JNDI Caching</a></li>
 * </ul>
 * 
 */
public class ServiceLocator
{
	private static Logger logger = Logger.getLogger(ServiceLocator.class);
	private static final String defaultDataSource="jdbc/dataSource";
	private static ServiceLocator singleton;
	private InitialContext context;
	 	
	private static Map <String,InitialContext> cacheMap = 
		          Collections.synchronizedMap(new HashMap<String,InitialContext>()); 

	
	private ServiceLocator() throws NamingException
	{
		context = new InitialContext();
	}

	/**
	 * returns the singleton ServiceLocator instance.
	 */
	public static ServiceLocator getInstance() throws NamingException
	{
		if( singleton == null )
		{
			//Note: It is possible for multiple threads to enter this initializer.
			//That's OK (and unlikely). It just means that extra
			//ServiceLocators might get created (and garbage collected) initially.
			//This is better than synchronizing this method.
			singleton = new ServiceLocator();
			logger.debug( "Initialized singleton: " + singleton.getClass().getName() );
		}
		
		return singleton;
	}


	/**
	 * Returns the Object with the specified jndiName from CacheMap
	 * If not found, throws a NamingException.
	 * For Example jndiName = jdbc/myDataSource
	 * 
	 */
	public Object getService(String jndiName) throws NamingException
	{
		Object obj = null;
		String jndi_name = jndiName; //"java:/comp/env/"+jndiName;
		
		if (cacheMap.containsKey(jndi_name)) {
			obj = cacheMap.get(jndi_name);
		} else {
			
		    obj = context.lookup( jndi_name );
         
		    if( obj == null ) //yes, you can bind nulls to JNDI
		    {
		    	logger.debug( "Retrieved JNDI Object. JNDI name=" + jndiName + "   Object=null" );
		    }
		    else
		    {
		    	logger.debug( "Retrieved JNDI Object. JNDI name=" + jndiName + "   Classname=" + obj.getClass().getName() );
		    }
		}
		 
		return obj;
	}
	
	public static Connection getConnection( String jndiName ) throws NamingException, SQLException {
		
		DataSource ds =(DataSource) ServiceLocator.getInstance().getService( jndiName );
		Connection connection = ds.getConnection();
		//connection.setAutoCommit(false);
		return connection;
	}
	public static Connection getConnection( ) throws NamingException, SQLException {
		
		DataSource ds =(DataSource) ServiceLocator.getInstance().getService( defaultDataSource);
		Connection connection = ds.getConnection();
		//connection.setAutoCommit(false);
		return connection;
	}
	
	
}
