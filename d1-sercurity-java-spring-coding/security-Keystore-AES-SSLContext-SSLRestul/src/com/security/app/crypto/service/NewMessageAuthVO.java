package com.security.app.crypto.service;

import java.io.Serializable;

import org.apache.logging.log4j.Logger;

 
import com.mailfrontier.msgcenter.app.log.Logger;
 

public class NewMessageAuthVO implements Serializable {
	public static Logger Log =Logger.getLogger(NewMessageAuthVO.class.getName());

	static final long serialVersionUID = 6603384152749567614L;
  
	private String encryptedMessage;   // original message is random number
	private String hashedMessage;
	private String seedStr4IV;
	private String commandType=null;
	/**
	 *  Authentication of RA access
	 */
	private String ccHostname;     // RA check hostlist.xml to find RA's CC	
	private String timestamp;
	
	
	private String testCommandType=null;
	private String testSubdir;
	
	
	/**
	 *  Additional Data besides User Authentication Related
	 */
	private String vmtaname=null;
	private String hostName=null;
	private String pathDescription = null;
	private String pathId=null;
	
	public NewMessageAuthVO() {}
	/**
	 * mostlt mta_statux.xml data fetch
	 * @param commandType
	 * @param hostName
	 * @param pathId
	 */
	public NewMessageAuthVO (String commandType, String hostName) {
			super();		 
			this.commandType = commandType;		 
			this.hostName = hostName;
	 }
	
	/**
	 * Mostly usage for mta_queuebydetailvmta.xml data fetch 
	 * @param commandType
	 * @param hostName
	 * @param pathId
	 */
	
    public NewMessageAuthVO (String commandType, String hostName, String pathId) {
		super();		 
		this.commandType = commandType;		 
		this.hostName = hostName;
		this.pathId = pathId;
	}
	
 

	public NewMessageAuthVO(String encryptedMessage, String hashedMessage,
			String seedStr4IV, String commandType, String hostName) {
		super();
		this.encryptedMessage = encryptedMessage;
		this.hashedMessage = hashedMessage;
		this.seedStr4IV = seedStr4IV;
		this.commandType = commandType;
		this.hostName = hostName;
	}
	
    public NewMessageAuthVO(String encryptedMessage, String hashedMessage,
			String seedStr4IV, String commandType, String testCommandType,
			String testSubdir, String hostName, String pathId) {
		super();
		this.encryptedMessage = encryptedMessage;
		this.hashedMessage = hashedMessage;
		this.seedStr4IV = seedStr4IV;
		this.commandType = commandType;
		this.testCommandType = testCommandType;
		this.testSubdir = testSubdir;
		this.hostName = hostName;
		this.pathId = pathId;
	}

	public NewMessageAuthVO(String encryptedMessage, String hashedMessage,
			String seedStr4IV, String commandType,  
			  String hostName, String pathDescription,
			String pathId) {
		super();
		this.encryptedMessage = encryptedMessage;
		this.hashedMessage = hashedMessage;
		this.seedStr4IV = seedStr4IV;
		this.commandType = commandType;
		 
		this.hostName = hostName;
		this.pathDescription = pathDescription;
		this.pathId = pathId;
	}
	 
	public String getVmtaname() {
		return vmtaname;
	}
	public void setVmtaname(String vmtaname) {
		this.vmtaname = vmtaname;
	}
	public String getPathDescription() {
		return pathDescription;
	}
	public void setPathDescription(String pathDescription) {
		this.pathDescription = pathDescription;
	}
	public String getPathId() {
		return pathId;
	}

	public void setPathId(String pathId) {
		this.pathId = pathId;
	}

	public String getEncryptedMessage() {
		return encryptedMessage;
	}
	public void setEncryptedMessage(String encryptedMessage) {
		this.encryptedMessage = encryptedMessage;
	}
	public String getHashedMessage() {
		return hashedMessage;
	}
	public void setHashedMessage(String hashedMessage) {
		this.hashedMessage = hashedMessage;
	}
	public String getSeedStr4IV() {
		return seedStr4IV;
	}
	public void setSeedStr4IV(String seedStr4IV) {
		this.seedStr4IV = seedStr4IV;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getCommandType() {
		return commandType;
	}
	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTestCommandType() {
		return testCommandType;
	}

	public void setTestCommandType(String testCommandType) {
		this.testCommandType = testCommandType;
	}

	public String getTestSubdir() {
		return testSubdir;
	}

	public void setTestSubdir(String testSubdir) {
		this.testSubdir = testSubdir;
	}
	public String getCcHostname() {
		return ccHostname;
	}
	public void setCcHostname(String ccHostname) {
		this.ccHostname = ccHostname;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	 
	
}
