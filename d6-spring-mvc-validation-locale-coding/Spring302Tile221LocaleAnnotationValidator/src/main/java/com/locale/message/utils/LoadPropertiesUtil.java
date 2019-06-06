package com.locale.message.utils;
/**
 * Load the Properties file
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

 
public class LoadPropertiesUtil {  
	static final Logger LOG = Logger.getLogger(LoadPropertiesUtil.class); 	 
	private static String OS = System.getProperty("os.name").toLowerCase();

	public Properties getProperties(String propertiesFile) {
		Properties prop =null;
		InputStream input = null;
		String classPath =  null; 
		try {
			
			 if (isWindows()) { // for windows , excluding leading '/'
				 classPath = this.getClass().getClassLoader().getResource("")
						     .getPath().substring(1);
					 
			 } else if (isUnix() || isSolaris()){ 
				 // for Linux/Solaris, including leading '/'
				 classPath= this.getClass().getClassLoader().
						    getResource("").getPath(); 
			     
			 }
			 classPath = classPath.replace("%20", " ")+propertiesFile;
			 LOG.info("ClassPath="+classPath);
			 
			input = new FileInputStream(classPath);
			if (null!=input) {
				prop = new Properties();
				// load a properties file
				prop.load(input);
			}
	  
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return prop;	 
	}
	public static boolean isWindows() {		 
		return (OS.indexOf("win") >= 0);	      
	}	      
	public static boolean isMac() {	      
	 	return (OS.indexOf("mac") >= 0);	   
   	}
	      
	public static boolean isUnix() {	      
	     return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 
	    		 || OS.indexOf("aix") > 0 );	      
	}	      
	public static boolean isSolaris() {	      
	     return (OS.indexOf("sunos") >= 0);	      
   }
	
}