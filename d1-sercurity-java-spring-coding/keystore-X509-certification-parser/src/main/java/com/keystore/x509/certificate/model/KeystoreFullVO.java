package com.keystore.x509.certificate.model;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.keystore.x509.certificate.utils.Constants;
import com.keystore.x509.certificate.utils.Utils;


 
/**
*  Created by John Zhang (john_zhang3@apple.com)
*  Created Date: 01/28/2018
 
 * Read ksDirectory, keystoreName, truststore, keystoreType, storepassword, keypassword from KSConfigureVO object
 * rest fields are collected by code such as JKSP12Keystore
 * 
 */

public class KeystoreFullVO {
	static Logger LOG = Logger.getLogger(KeystoreFullVO.class);
	private String ksDirectory;
	private String keyStoreName;    // myKeystore.jks or myKeystore.p12
	private String keyStoreType;    // default jks or pickup  pkcs12
	private String truststore;      // keep trust CA certificates 
	private String storePassword;   // properties file should contains this store password accessed by admin
	private String keyPassword;     // properties file should contains this private key password accessed by admin
	private String alias;           // keystore owner's alias
	private String keyAlg;          // RSA
	private String keySize;         // 1024 or 2048 or 4096
	private String CN;              // keystore owner's CN
	private String signAlg;         // SHA1withRSA or SHA256withRSA for certificate
	private List<CAVO> CAChains;    // Record 0 is root CA and others could be intermediate CA 
	private String san;             // multiple FQDNs dns:sample.apple.com,dns:sample1.apple.com          
	private int keystoreEntries;    // If owner and Root CA only,  means 2 entries
	private String certFingerPrint; // before import cert, comparing the fingerprint
	private String certAlg;         // certificate algorithm ex. RSA
	private String certSignAlg;     // certificate sign algorithm SHA1withRSA or SHA256withRSA
	private String certkeySize;     // public bits 1024 or 2048 or 4096
	private String expireDate;      // Certificate expired date (not private key expired date)
	private long expireDateMS=0L;      
	private String EntryType;
	   
	
	public KeystoreFullVO() {
		super();
		// TODO Auto-generated constructor stub
	}

    
   public KeystoreFullVO(String ksDirectory, String alias, String keyStoreName, String storePassword,String keyPassword) {
		super();
		if (!ksDirectory.endsWith("/")) {
			this.ksDirectory = ksDirectory+"/";
		} else {
			this.ksDirectory = ksDirectory;
		}
		this.keyStoreName = keyStoreName;
		 
		this.storePassword = storePassword;
		this.keyPassword = keyPassword;
		this.alias = alias;
	}
   
   public KeystoreFullVO(KSConfigureVO vo) {
		super();
		this.ksDirectory = vo.getKsDirectory();
		this.keyStoreName = vo.getKeyStoreName();
		this.storePassword =vo.getStorePassword();
		this.keyPassword = vo.getKeyPassword();
	//	this.alias =vo.getAlias();
	}
 
   public boolean isExpired() {
	   long currentMS = System.currentTimeMillis(); 

	   if (expireDateMS!=0L) {
		  	 long diff = currentMS - expireDateMS;
		  	 if (diff<=0) {
		  		 return false;
		  	 } else {
		  		 long days = (diff / (60*60*24*1000));
		  		 LOG.info("Certificate got " +days+ " days to be expired!");
		  		 if (Constants.DAYS_TO_EXPIRED>days) {
		  			 return true;
		  		 }
		  	 }
	   } else {
		   LOG.info("KeystoreVO Object is not fully instantiated, please run getKeystore() function first!");
	   }
	   return false;
	   
   }
    
	public String getTruststore() {
	return truststore;
}


 


@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keyStoreName == null) ? 0 : keyStoreName.hashCode());
		result = prime * result + ((ksDirectory == null) ? 0 : ksDirectory.hashCode());
		result = prime * result + ((truststore == null) ? 0 : truststore.hashCode());
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
		KeystoreFullVO other = (KeystoreFullVO) obj;
		if (keyStoreName == null) {
			if (other.keyStoreName != null)
				return false;
		} else if (!keyStoreName.equals(other.keyStoreName))
			return false;
		if (ksDirectory == null) {
			if (other.ksDirectory != null)
				return false;
		} else if (!ksDirectory.equals(other.ksDirectory))
			return false;
		if (truststore == null) {
			if (other.truststore != null)
				return false;
		} else if (!truststore.equals(other.truststore))
			return false;
		return true;
	}

	public boolean isValid() {  
		if (null==this.keyStoreName || this.getKeyStoreName().length()==0) {
			LOG.info("Keystore name is not initialized!");
	 		return false;
		}  
		
		if (null==this.ksDirectory || this.getKsDirectory().length()==0) {
			LOG.info("Key directory is not initialized!");
			return false;
		}
		
		// check if keystore file physically exists in OS 
		
	 
		
		if (this.getKeyStoreName().contains("/")) {
		
		} else {
			String fullname = null;
			if (!ksDirectory.endsWith("/")) {
				fullname = ksDirectory+"/"+this.getKeyStoreName();
			} else {
				fullname = ksDirectory+this.getKeyStoreName();
			}
			File file =new File(fullname);
			if (file.exists()) {
				LOG.info("Keystore file "+fullname+" does not exist physically!");
				return false;
			}
		}
		
	    if (null==this.storePassword || this.getStorePassword().length()==0) {
			LOG.info("Keystore store password is not initialized!");
			return false;
		}
	    if (null==this.keyPassword || this.getKeyPassword().length()==0) {
			LOG.info("Keystore key password is not initialized!");
			return false;
		}
		return true;
	}
    
	public void setTruststore(String truststore) {
		this.truststore = truststore;
	}


	public String getEntryType() {
	return EntryType;
	}



    public String getCertFingerPrint() {
		return certFingerPrint;
	}



	public void setCertFingerPrint(String certFingerPrint) {
		this.certFingerPrint = certFingerPrint;
	}



	public String getCertAlg() {
		return certAlg;
	}



	public void setCertAlg(String certAlg) {
		this.certAlg = certAlg;
	}



	public String getCertSignAlg() {
		return certSignAlg;
	}



	public void setCertSignAlg(String certSignAlg) {
		this.certSignAlg = certSignAlg;
	}



	public String getCertkeySize() {
		return certkeySize;
	}



	public void setCertkeySize(String certkeySize) {
		this.certkeySize = certkeySize;
	}



public void setEntryType(String entryType) {
	EntryType = entryType;
}



	public String getExpireDate() {
		return expireDate;
	}



	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

    

	public long getExpireDateMS() {
		return expireDateMS;
	}



	public void setExpireDateMS(long expireDateMS) {
		this.expireDateMS = expireDateMS;
	}



	public int getKeystoreEntries() {
		return keystoreEntries;
	}



	public void setKeystoreEntries(int keystoreEntries) {
		this.keystoreEntries = keystoreEntries;
	}



	public String getKsDirectory() {
		return ksDirectory;
	}



	public void setKsDirectory(String ksDirectory) {
		if (!ksDirectory.endsWith("/")) {
			this.ksDirectory = ksDirectory+"/";
		} else {
			this.ksDirectory = ksDirectory;
		}
	}


	public String getSan() {
		return san;
	}


	public void setSan(String san) {
		this.san = san;
	}


	public String getKeyStoreName() {
		return keyStoreName;
	}
	public void setKeyStoreName(String keyStoreName) {
		this.keyStoreName = keyStoreName;
	}
	public String getKeyStoreType() {
		return keyStoreType;
	}
	public void setKeyStoreType(String keyStoreType) {
		this.keyStoreType = keyStoreType;
	}
	
	
	public String getKeyAlg() {
		return keyAlg;
	}
	public void setKeyAlg(String keyAlg) {
		this.keyAlg = keyAlg;
	}

	public String getStorePassword() {
		return storePassword;
	}


	public void setStorePassword(String storePassword) {
		this.storePassword = storePassword;
	}


	public String getKeyPassword() {
		return keyPassword;
	}


	public void setKeyPassword(String keyPassword) {
		this.keyPassword = keyPassword;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getKeySize() {
		return keySize;
	}


	public void setKeySize(String keySize) {
		this.keySize = keySize;
	}


	public List<CAVO> getCAChains() {
		return CAChains;
	}


	public void setCAChains(List<CAVO> cAChains) {
		CAChains = cAChains;
	}


	public String getCN() {
		return CN;
	}
	public void setCN(String cN) {
		CN = cN;
	}
	public String getSignAlg() {
		return signAlg;
	}
	public void setSignAlg(String signAlg) {
		this.signAlg = signAlg;
	}


	@Override
	public String toString() {
		return "KeystoreVO [ksDirectory=" + ksDirectory + ", keyStoreName=" + keyStoreName + ", keyStoreType="
				+ keyStoreType + ", truststore=" + truststore + ", storePassword=" + storePassword + ", keyPassword="
				+ keyPassword + ", alias=" + alias + ", keyAlg=" + keyAlg + ", keySize=" + keySize + ", CN=" + CN
				+ ", signAlg=" + signAlg + ", CAChains=" + CAChains + ", san=" + san + ", keystoreEntries="
				+ keystoreEntries + ", certFingerPrint=" + certFingerPrint + ", certAlg=" + certAlg + ", certSignAlg="
				+ certSignAlg + ", certkeySize=" + certkeySize + ", expireDate=" + expireDate + ", expireDateMS="
				+ expireDateMS + ", EntryType=" + EntryType + "]";
	}


	
}
