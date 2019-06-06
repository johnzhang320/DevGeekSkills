package com.keystore.x509.certificate.model;

import java.io.File;

import org.apache.log4j.Logger;

import com.keystore.x509.certificate.utils.Constants;
import com.keystore.x509.certificate.utils.Utils;
import com.keystore.x509.certificate.utils.WorkHome;

/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 12/2017
 *
 * Admin or Deployer of this code gather this data in ksconfigure.xml, W3CXMLManager read to this object
 */
public class KSConfigureVO {
	static Logger Log = Logger.getLogger(KSConfigureVO.class);
	public  StringBuffer message = new StringBuffer();
	private String environment;      // applepay_qa_environment , applepay_qa-nc_environment, applepay_prod1_environment
	private String jsonSubFolder;   // applepay, qa
	public  String confJsonFile;    // databags configure JSON file name
	private String ksSubFolder;     // qa-nc
	private String keyStoreName;    // keystore file name
	private String storePassword;   //  properties file should contains this store password accessed by admin
	private String keyPassword;     //  properties file should contains this private key password accessed by admin
	private String ksDirectory;     // Path of Keystore
	private String keyStoreType;    // jks, p12, pem, der
	
	private static final String GPG_DATA_HOME=WorkHome.DATA_GPGREPOS_HOME;
	 
	      
	
	public KSConfigureVO(String ksDirectory, String keyStoreName, String storePassword, String keyPassword) {
		super();
		if (!ksDirectory.endsWith("/")) {
			this.ksDirectory = ksDirectory+"/";
		} else {
			this.ksDirectory = ksDirectory;
		}
		this.keyStoreName = Utils.lrTrim(keyStoreName);
		this.storePassword = Utils.lrTrim(storePassword);
		this.keyPassword = Utils.lrTrim(keyPassword);
	}


	public StringBuffer getMessage() {
		return message;
	}

	public String getJsonSubFolder() {
		return jsonSubFolder;
	}


	public void setJsonSubFolder(String jsonSubFolder) {
		this.jsonSubFolder = jsonSubFolder;
	}


	public String getKsSubFolder() {
		return ksSubFolder;
	}


	public void setKsSubFolder(String ksSubFolder) {
		this.ksSubFolder = ksSubFolder;
	}


	public void setMessage(String msg) {
		this.message.append(msg+"  ");
	}


	public String getConfJsonFile() {
		return confJsonFile;
	}


	public void setConfJsonFile(String confJsonFile) {
		this.confJsonFile = confJsonFile;
	}


	public KSConfigureVO() {}
	
	 

	public String getKsDirectory() {
		String ksDir =GPG_DATA_HOME+environment+File.separator+Constants.KS_KEYSTORES+File.separator+ksSubFolder+File.separator;
		File file = new File(ksDir);
		/*if (!file.exists()) {
			Log.info("keystore folder does not exsit! "+ksDir);
		}*/
		return ksDir;
	}
	public void setKsDirectory(String ksDirectory) {
		if (!ksDirectory.endsWith("/")) {
			this.ksDirectory = ksDirectory+"/";
		} else {
			this.ksDirectory = ksDirectory;
		}
	}
	public String getKeyStoreName() {
		return keyStoreName;
	}
	public void setKeyStoreName(String keyStoreName) {
		this.keyStoreName = Utils.lrTrim(keyStoreName," ");
	}
	public String getKeyStoreType() {
		return keyStoreType;
	}
	public void setKeyStoreType(String keyStoreType) {
		this.keyStoreType = keyStoreType;
	}
	 
	public String getStorePassword() {
		return storePassword;
	}
	public void setStorePassword(String storePassword) {
		this.storePassword = Utils.lrTrim(storePassword);
	}
	public String getKeyPassword() {
		return keyPassword;
	}
	public void setKeyPassword(String keyPassword) {
		this.keyPassword = Utils.lrTrim(keyPassword);
	}


	 


	public String getEnvironment() {
		return environment;
	}


	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((environment == null) ? 0 : environment.hashCode());
		result = prime * result + ((keyStoreName == null) ? 0 : keyStoreName.hashCode());
		result = prime * result + ((ksSubFolder == null) ? 0 : ksSubFolder.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KSConfigureVO other = (KSConfigureVO) obj;
		if (environment == null) {
			if (other.environment != null)
				return false;
		} else if (!environment.equals(other.environment))
			return false;
		if (keyStoreName == null) {
			if (other.keyStoreName != null)
				return false;
		} else if (!keyStoreName.equals(other.keyStoreName))
			return false;
		if (ksSubFolder == null) {
			if (other.ksSubFolder != null)
				return false;
		} else if (!ksSubFolder.equals(other.ksSubFolder))
			return false;
		return true;
	}


	@Override
	public String toString() {
		String msg = "";
		if (message.length()>0) {
			msg = "message="+message+" ";
		}
		return "KSConfigureVO [ "+msg+" environment="+environment+",jsonSubFolder="+jsonSubFolder+", confJsonFile=" + confJsonFile +", ksSubfolder="+ksSubFolder+ ", keyStoreName=" + keyStoreName
				+ ", storePassword=" + storePassword + ", keyPassword=" + keyPassword + ", ksDirectory=" + getKsDirectory()
				+ ", keyStoreType=" + keyStoreType + "]";
	}


	 

}
