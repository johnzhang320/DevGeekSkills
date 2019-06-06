package com.keystore.x509.certificate.model;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.keystore.x509.certificate.utils.Constants;
import com.keystore.x509.certificate.utils.Utils;


 
/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 12/2017
 *
 * Read ksDirectory, keystoreName, truststore, keystoreType, storepassword, keypassword from KSConfigureVO object
 * rest fields are collected by code such as JKSP12Keystore
 * 
 */

public class KeystoreVO {
	static Logger LOG = Logger.getLogger(KeystoreVO.class);
	private StringBuffer message=new StringBuffer();         // message: keystore expired, 
	                                                         //keystore not exists, wrong password, data corrupt,   
	private String confDirFile;     // Configuration file sub directory and file name
	private String keyStoreName;    // myKeystore.jks or myKeystore.p12
	private String expireDate;      // Certificate expired date (not private key expired date)
	private String storePassword;   // properties file should contains this store password accessed by admin
	private String keyPassword;     // properties file should contains this private key password accessed by admin
	private String ksDirectory;     // keystore file path
	private String ManagementGroupId;    // Management Group Id for O=Apple Inc.
	private String IdentityGroupId;      // Identity Group Id for O=Apple Inc.
	private String keyStoreType;    // default jks or pickup  pkcs12
	private String truststore;      // keep trust CA certificates 
	private String certChainLength; // Total number of certificates
	private String alias;           // keystore owner's alias
	private String keyAlg;          // RSA
	private String keySize;         // 1024 or 2048 or 4096
	private String CN;              // private key CN
	private String KSOwnerCN;       // keystore owner's CN
	private String KSIssureCN;      // keystore Issuer's CN
	private String signAlg;         // SHA1withRSA or SHA256withRSA for certificate
	private String CertOwnerCN;     // final Cert owner CN,if Cert chain length is 3, certificate[3] CN
	private String CertIssuerCN;    // final Cert issuer CN,if Cert chain length is 3, certificate[3] CN
	private String san;             // multiple FQDNs dns:sample.apple.com,dns:sample1.apple.com          
	private int    keystoreEntries;    // If owner and Root CA only,  means 2 entries
	private String certFingerPrint; // before import cert, comparing the fingerprint
	private String environment;      // Environment name
	private String certAlg;         // certificate algorithm ex. RSA
	private String certSignAlg;     // certificate sign algorithm SHA1withRSA or SHA256withRSA
	private String certkeySize;     // public bits 1024 or 2048 or 4096
	private long   expireDateMS=0L;      
	private String EntryType;
 	   
	
	public KeystoreVO() {
		super();
		// TODO Auto-generated constructor stub
	}

    
   public KeystoreVO(String ksDirectory, String alias, String keyStoreName, String storePassword,String keyPassword) {
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
   
   public KeystoreVO(KSConfigureVO vo) {
		super();
		this.ksDirectory = vo.getKsDirectory();
		this.keyStoreName = vo.getKeyStoreName();
		this.storePassword =vo.getStorePassword();
		this.keyPassword = vo.getKeyPassword();
		//this.alias =vo.getAlias();
	}
 
   public boolean isExpired() {
	   long currentMS = System.currentTimeMillis(); 

	   if (expireDateMS!=0L) {
		  	 long diff = currentMS - expireDateMS;
		  	 if (diff<=0) {
		  		 return false;
		  	 } else {
		  		 long days = (diff / (60*60*24*1000));
		  		
		  		 LOG.info(this.message);
		  		
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
		KeystoreVO other = (KeystoreVO) obj;
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
			if (!file.exists()) {
				 
				LOG.info("Keystore file "+fullname+" does not exist! ");
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
 

	public String getCN() {
		return CN;
	}


	public void setCN(String cN) {
		CN = cN;
	}


	public StringBuffer getMessage() {
		return message;
	}


	public void setMessage(String msg) {
		this.message.append(msg+" ");
	}


	public String getManagementGroupId() {
		return ManagementGroupId;
	}


	public void setManagementGroupId(String managementGroupId) {
		ManagementGroupId = managementGroupId;
	}


	public String getIdentityGroupId() {
		return IdentityGroupId;
	}


	public void setIdentityGroupId(String identityGroupId) {
		IdentityGroupId = identityGroupId;
	}


	public String getConfDirFile() {
		return confDirFile;
	}


	public void setConfDirFile(String confDirFile) {
		this.confDirFile = confDirFile;
	}


	public String getCertChainLength() {
		return certChainLength;
	}


	public void setCertChainLength(String certChainLength) {
		this.certChainLength = certChainLength;
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
		
		this.keyStoreName = Utils.lrTrim(keyStoreName, " ");
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
		
		this.storePassword = Utils.lrTrim(storePassword, " ");;
	}


	public String getKeyPassword() {
		return keyPassword;
	}


	public void setKeyPassword(String keyPassword) {
		this.keyPassword = Utils.lrTrim(keyPassword, " ");
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
    
	 
	public String getKSOwnerCN() {
		return KSOwnerCN;
	}


	public void setKSOwnerCN(String kSOwnerCN) {
		KSOwnerCN = kSOwnerCN;
	}


	public String getKSIssureCN() {
		return KSIssureCN;
	}


	public void setKSIssureCN(String kSIssureCN) {
		KSIssureCN = kSIssureCN;
	}


	public String getSignAlg() {
		return signAlg;
	}
	public void setSignAlg(String signAlg) {
		this.signAlg = signAlg;
	}
	

	public String getCertOwnerCN() {
		return CertOwnerCN;
	}


	public void setCertOwnerCN(String certOwnerCN) {
		CertOwnerCN = certOwnerCN;
	}


	public String getCertIssuerCN() {
		return CertIssuerCN;
	}


	public void setCertIssuerCN(String certIssuerCN) {
		CertIssuerCN = certIssuerCN;
	}


	public String getEnvironment() {
		return environment;
	}


	public void setEnvironment(String environment) {
		this.environment = environment;
	}


	@Override
	public String toString() {
		StringBuffer retVal = new StringBuffer();
		String tab="";
		if (message.length()>1) {
			if (null==expireDate || expireDate.trim().length()==0) {
				retVal.append(message.toString()+"\n");
			} else {
				retVal.append(message.toString()+", Expire Date:"+expireDate+"\n");
			}
			tab="    ";
		}
		retVal.append(tab+"KeystoreVO [ confDirFile=" + confDirFile + ", keyStoreName="
				+ keyStoreName + ", ksDirectory=" + ksDirectory+ ", expireDate=" + expireDate + ", ManagementGroupId=" + ManagementGroupId
				+ ", IdentityGroupId=" + IdentityGroupId + ", keyStoreType=" + keyStoreType + ", truststore="
				+ truststore + ", certChainLength=" + certChainLength + ", alias=" + alias +",Environment="+environment+ ", keyAlg=" + keyAlg
				+ ", keySize=" + keySize + ", KSOwnerCN=" + KSOwnerCN + ", KSIssureCN=" + KSIssureCN + ", signAlg="
				+ signAlg + ", CertOwnerCN=" + CertOwnerCN + ", CertIssuerCN=" + CertIssuerCN + ", san=" + san
				+ ", keystoreEntries=" + keystoreEntries + ", certFingerPrint=" + certFingerPrint + ", certAlg="
				+ certAlg + ", certSignAlg=" + certSignAlg + ", certkeySize=" + certkeySize + ", expireDateMS="
				+ expireDateMS + ", EntryType=" + EntryType + "]"
				);
		return retVal.toString();
	}
 
}
