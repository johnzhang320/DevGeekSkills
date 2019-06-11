package com.sslcontext.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
 
public class FindResourceFilePath {  
	static final Logger Log = Logger.getLogger(FindResourceFilePath.class); 	 
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	private static String resourcePath=null;
	private static FindResourceFilePath findResourceFilePath=null;
	public static FindResourceFilePath getInstance() {
		if (findResourceFilePath==null) {
			findResourceFilePath = new  FindResourceFilePath() ;
		}
		return findResourceFilePath;
	}
	private FindResourceFilePath() {
		
	}
	public String getResourceFile(String resourceFileName) {
	 
		if ( resourcePath!=null) {
			return  resourcePath+ resourceFileName;
		}
		String classPath =  null; 
	 
		 if (isWindows()) { // for windows , excluding leading '/'
			 classPath = this.getClass().getClassLoader().getResource("").getPath().substring(1);
				 
		 } else if (isUnix() || isSolaris()){ // for Linux/Solaris, including leading '/'
			 classPath= this.getClass().getClassLoader().getResource("").getPath(); 
		     
		 } else {
			 classPath= this.getClass().getClassLoader().getResource("").getPath();
		 }
		 resourcePath= classPath.replace("%20", " ");
		 classPath = classPath.replace("%20", " ")+resourceFileName;
		// Log.info("ClassPath="+classPath);
	 
		return classPath;
	 
	  }
	public static boolean isWindows() {
		 
		return (OS.indexOf("win") >= 0);
	      
	}
	      
	public static boolean isMac() {
	      
	 	return (OS.indexOf("mac") >= 0);
	   
   	}
	      
	public static boolean isUnix() {
	      
	     return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	      
	}
	      
	public static boolean isSolaris() {
	      
	     return (OS.indexOf("sunos") >= 0);
	      
   }
	
	public static String getResourcePath() {
		return resourcePath;
	}
	public static void setResourcePath(String resourcePath) {
		FindResourceFilePath.resourcePath = resourcePath;
	}
	public static void main (String [] args) {
		String file=FindResourceFilePath.getInstance().getResourceFile("mykeystore.jks");
		Log.info("my file="+file); 
		 
	}
	
}