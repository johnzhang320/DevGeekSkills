package com.loan.agent.mvc.utils;

import java.io.File;
import java.net.InetAddress;

import com.loan.agent.services.Constant;
import org.apache.log4j.Logger;
public class SysPath {
	private static final Logger LOG = Logger.getLogger (SysPath.class);
	private static SysPath handler=null;
	private static String classesPath = null;
	private static String OS = System.getProperty("os.name").toLowerCase();
	private static String sharedDataPath = null;
	private static String agentPicturePath = null;
	private static String agentEmailFilePath = null;
	private static String agentEmailAttachmentPath = null;
	private static String loanApplicationFormsPath = null;
	private static String tempPath = null;
	private static String commonlogPath = null;
	
	public static SysPath getInstance() {	
		
		if (handler ==null) {
		    handler = new SysPath();
		    if (isWindows()) { // for windows , excluding leading '/'
				 classesPath = handler.getClass().getClassLoader().getResource("").getPath().substring(1);
				 sharedDataPath = Constant.WINDOWS_DATA_PATH;
				 
				 
			 } else if (isUnix() || isSolaris()){ // for Linux/Solaris, including leading '/'
			     classesPath = handler.getClass().getClassLoader().getResource("").getPath(); 
			     sharedDataPath = Constant.LINUX_DATA_PATH; 
			 }
		    

		}	 
		/**
		 *  Just in case the directory is not created , everytime ,call mkdirs
		 */
		mkdirs();
		return handler;	 
			
   }
   public static void mkdirs() {
	     pathCreate(sharedDataPath);			 
	     agentPicturePath = sharedDataPath+Constant.AGENT_PICTURE_PATH;
	     pathCreate(agentPicturePath); 
		 agentEmailFilePath = sharedDataPath+Constant.AGNET_EMAIL_LIST_PATH;
		 pathCreate(agentEmailFilePath);
		 agentEmailAttachmentPath = sharedDataPath+Constant.AGNET_EMAIL_ATTACHMENT_PATH;
		 pathCreate(agentEmailAttachmentPath);
		 tempPath = sharedDataPath+Constant.TEMP_DIR;
		 pathCreate(tempPath );
		 commonlogPath = sharedDataPath+Constant.COMMON_LOG_PATH;
		 pathCreate(commonlogPath);
		 loanApplicationFormsPath = sharedDataPath+Constant.LOAN_APPLICTION_FORM_PATH;
		 pathCreate(loanApplicationFormsPath);
   }
	
   public static String getHostName () {
	   String currentHostName =null;
   
	   try {
	        currentHostName = "http://localhost"; //InetAddress.getLocalHost().getHostName();
	        
  		
  		    if (SysPath.isWindows()) {
  			    currentHostName=currentHostName+":8080";
  		    }
  		   if (SysPath.isUnix()) {
			    currentHostName="http://loans-agent.com";
		    }
  		    
  		} catch (Exception e) {
  			LOG.info("Failed to find localhost name");
  			return null;
  		}
	   return currentHostName;
   }
    
	public static void pathCreate(String path) {
		 File file = new File(path);
		if (!file.exists()) {
			LOG.info ("SysPath.pathCreate(): Creating Directory :"+file.getAbsolutePath());
			/**
			 *  create directory with subdirs must use file.mkdirs();
			 */
			file.mkdirs();
		 }
	}
	private SysPath() {}
	
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
	
	 
	public static String getLoanApplicationFormsPath() {
		return loanApplicationFormsPath;
	}
	public static void setLoanApplicationFormsPath(String loanApplicationFormsPath) {
		SysPath.loanApplicationFormsPath = loanApplicationFormsPath;
	}
	public static String getClassesPath() {
		return classesPath;
	}
	public static void setClassesPath(String classesPath) {
		SysPath.classesPath = classesPath;
	}
	
	public static String getSharedDataPath() {
		return sharedDataPath;
	}
	public static void setSharedDataPath(String sharedDataPath) {
		SysPath.sharedDataPath = sharedDataPath;
	}
	public static void main(String[] args) {
		 System.out.println("classesPath="+SysPath.getInstance().getClassesPath());
	 }
	public static String getAgentPicturePath() {
		return agentPicturePath;
	}
	public static void setAgentPicturePath(String agentPicturePath) {
		SysPath.agentPicturePath = agentPicturePath;
	}
	public static String getAgentEmailFilePath() {
		return agentEmailFilePath;
	}
	public static void setAgentEmailFilePath(String agentEmailFilePath) {
		SysPath.agentEmailFilePath = agentEmailFilePath;
	}
	public static String getTempPath() {
		return tempPath;
	}
	public static void setTempPath(String tempPath) {
		SysPath.tempPath = tempPath;
	}
	public static String getCommonlogPath() {
		return commonlogPath;
	}
	public static void setCommonlogPath(String commonlogPath) {
		SysPath.commonlogPath = commonlogPath;
	}
	public static String getAgentEmailAttachmentPath() {
		return agentEmailAttachmentPath;
	}
	public static void setAgentEmailAttachmentPath(String agentEmailAttachmentPath) {
		SysPath.agentEmailAttachmentPath = agentEmailAttachmentPath;
	}
	
}
