package com.keystore.x509.certificate.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.keystore.x509.certificate.model.CAVO;
import com.keystore.x509.certificate.model.KSConfigureVO;
import com.keystore.x509.certificate.model.KeystoreVO;
import com.keystore.x509.certificate.service.KeystoreBuilder;
import com.keystore.x509.certificate.utils.Constants;
import com.keystore.x509.certificate.utils.DoKeytool;
import com.keystore.x509.certificate.utils.DoOpenSSL;
import com.keystore.x509.certificate.utils.Utils;
/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 12/2017
 *
 */
public class JKSP12KeystoreAnalyzer implements KeystoreBuilder {
	static Logger Log = Logger.getLogger(JKSP12KeystoreAnalyzer.class);
	 
	private String msg="";
	// -----------------Implemented method -----------------------
	public KeystoreVO getKSOwner(KSConfigureVO ksConfVO) {
		KeystoreVO vo = getKeystore(ksConfVO.getKsDirectory(), 
				                    ksConfVO.getKeyStoreName(), 
				                    ksConfVO.getStorePassword(),
				                    ksConfVO.getKeyPassword()
				                    );
		if (null!=vo) {
			vo.setConfDirFile(ksConfVO.getConfJsonFile());
		} 
		return vo;
	}
	public KeystoreVO getKSOwner(String ksDir,String keystoreName, String storePassword, String keypassword) {
		KeystoreVO vo = getKeystore(ksDir, 
									keystoreName, 
									storePassword,
									keypassword
									);
		String ksDirRetval = ksDir.replace("mapdictionary", "gpgrepos");
		vo.setKsDirectory(ksDirRetval);
		 
		return vo;

	}
	public KeystoreVO getTrustCAs(KeystoreVO ksvo) {
		KeystoreVO results = ksvo;
		return results;
	}
	//-------------public functionality methods------------------
	
	public List<String> listKeystore(KSConfigureVO kscvo) {
		if (!Utils.isValidKSConfigureVO(kscvo)) {
			Log.info("KSConfigureVO is invalid!");
			return null;
		}
		return listKeystore(kscvo.getKsDirectory(),kscvo.getKeyStoreName(),kscvo.getStorePassword(),kscvo.getKeyPassword());
	}
	public List<String> listKeystore(KeystoreVO vo) {
		if (!Utils.isValidKeystoreVO(vo)) {
			Log.info("KeystoreVO is invalid!");
			return null;
		}
		return listKeystore(vo.getKsDirectory(),vo.getKeyStoreName(),vo.getStorePassword(),vo.getKeyPassword());
	}
	
	/**
	 * 
	 * Parse line by line of command: keytool -list -v -Keystore xxxx -storepass xxxx 
	 * Obtain all information about keystore, which will be used to create new keystore and CSR
	 * 
	 * @param ksDirectory
	 * @param keyStoreName
	 * @param storePassword
	 * @param keyPassword
	 * @return
	 */
	
    public KeystoreVO getKeystore(String ksDirectory,String keyStoreName, String storePassword,String keyPassword) {
    	
    	KeystoreVO vo = new KeystoreVO(ksDirectory, null, keyStoreName, storePassword,keyPassword);
        
    	// check if keystore file exists, if not return empty vo with error message
    	String fullpath = Utils.concateDirToFilename(ksDirectory, keyStoreName);
    	File file = new File(fullpath);
    	if (!file.exists()) {
    		msg ="Keystore file: "+fullpath+" does not exist! ";
    		vo.setMessage(msg);
    		//Log.info(msg);
    		return vo;
    	}
    	if (null==vo.getStorePassword() || vo.getStorePassword().trim().length()==0) {
    		msg = "No password for keystore "+fullpath;
    		vo.setMessage(msg);
    		Log.info(msg);
    		return vo;
    	}
        List<String> resultList = listKeystore(ksDirectory,keyStoreName, storePassword,keyPassword);
       // List<String> aliasList = this.getAliasList(resultList, alias);
        List<List<String>> certLenList= getAliasListByCertificateLength(resultList,null);
        // process common data
        int keystoreEntry = 0;
        String res = null;
        for (String line: resultList) {
        	 if (null==line) {
        		 vo.setMessage(Constants.WRONG_KEYTOOL_COMMAND);
        		 return vo;
        	 } else if (line.contains(Constants.WRONG_KEYSTORE_PASSWORD)) {
        		 vo.setMessage(Constants.WRONG_KEYSTORE_PASSWORD);
        		 return vo;
        	 } else if (line.contains(Constants.TEMPERED_KEYSTORE)) {
        		 vo.setMessage(Constants.TEMPERED_KEYSTORE);
        		 return vo;
        	 }
        	if ((res=getValue(Constants.JKS_ALIAS_NAME,line))!=null) {
    			vo.setAlias(res);
    		} 
        	if ((res=getValue(Constants.JKS_CERTIFICATE_CHAIN_LENGTH,line))!=null) {
    			vo.setCertChainLength(res);
    		} 
        	String s = "Your keystore contains";
			if (line.indexOf(s)!=-1) {
				String entry = line.substring(s.length()+1).replace(" entry", "").replace("entries","").trim();
				keystoreEntry = Integer.valueOf(entry);
				 
				vo.setKeystoreEntries(keystoreEntry);
			}
			
    		if ((res=getValue(Constants.JKS_KEYSTORE_TYPE,line))!=null) {
    			vo.setKeyStoreType(res);
    		}
        }
        
        // first list must be certificate[1], which is private key information
       if (null==certLenList || certLenList.size()==0) {
        	msg = "certificate length is null";
        	vo.setMessage(msg);
        	Log.error(msg);
        	return null;
        }
        List<String> aliasList = certLenList.get(0); 
        
       // processing owner of keystore
       for (String line:aliasList) {
         
        	if (line.trim().length()>0) {
        		if (line.contains(Constants.JKS_APPLE_INC)) {
        			int len = line.indexOf(Constants.JKS_MANAGEMENT_GROUP);
        			if (len!=-1) {
        				len+=Constants.JKS_MANAGEMENT_GROUP.length();
        				int endlen = line.indexOf(",",len);
        				if (endlen>len) {
	        				String mgrGrpId = line.substring(len,endlen);
	        				vo.setManagementGroupId(mgrGrpId);
	        				}
        			}	 
        			len = line.indexOf(Constants.JKS_IDENTITY_GROUP);
        			 
        			if (len!=-1) {
        				len+=Constants.JKS_IDENTITY_GROUP.length();
        				//int endlen = line.indexOf(",",len-2);
        				 
        				int endlen=len+6;
        				if (endlen>len) {
	        				String identityGrpId = line.substring(len,endlen);
	        				vo.setIdentityGroupId(Utils.lrTrim(identityGrpId,",")); 
        				}
        			}
        		}
        		if ( line.indexOf(Constants.JKS_UNTIL_COLON)!=-1) {     // certificate expired date
            		int start = line.indexOf(Constants.JKS_UNTIL_COLON)+11;
                    int end = start+24;
                    vo.setExpireDate(line.substring(start, end));
                    long expDateSM[] = new long[1];
                    isCertExpired(vo,expDateSM);
                    vo.setExpireDateMS(expDateSM[0]);
         		}
        		if ((res=getValue(Constants.JKS_KEYSTORE_TYPE,line))!=null) {
        			vo.setKeyStoreType(res);
        		}
        		if ((res=getValue(Constants.JKS_ENTRY_TYPE,line))!=null) {
        			vo.setEntryType(res);
        		}
          		
        		if ((res=getValue(Constants.JKS_OWNER,line))!=null) {
        			 
        			vo.setKSOwnerCN(res);
        		} 
        		if ((res=getValue(Constants.JKS_ISSUER,line))!=null) {
       			 
        			vo.setKSIssureCN(res);
        		} 
                
        		if ((res=getValue(Constants.JKS_SIGAGUTURE_ALGORITHM_NAME,line))!=null) {
        			vo.setSignAlg(res);
        		} 
        		if ((res=getValue(Constants.JKS_SUBJECT_PUBLIC_KEY_ALGORITHM,line))!=null) {
        			vo.setKeySize(res.substring(0, 4));
        			vo.setKeyAlg(res.substring(9).replace(Constants.JKS_KEY, ""));
        		} 
        		
         	}
       	
        }   
        
        // second list must be last elements of certLenList.get(certLenList.size()-1); 
        int last = certLenList.size()-1;
         aliasList = certLenList.get(0);   // fetch Certificate[1]
        // processing certificate of keystore
       for (String line:aliasList) {	
        	
        	
        	if ((res=getValue(Constants.JKS_OWNER,line))!=null) {
    			vo.setCertOwnerCN(res); 
    		} 
        	if ((res=getValue(Constants.JKS_ISSUER,line))!=null) {
    			vo.setCertIssuerCN(res); 
    		} 
    		
        	if ((res=getValue(Constants.JKS_SIGAGUTURE_ALGORITHM_NAME,line))!=null) {
    			vo.setCertSignAlg(res);
    		} 
    		if ((res=getValue(Constants.JKS_SUBJECT_PUBLIC_KEY_ALGORITHM,line))!=null) {
    			vo.setCertkeySize(res.substring(0, 5));
    			vo.setCertAlg(res.substring(9).replace(Constants.JKS_KEY, ""));
    		} 
    		if ((res=getValue(Constants.JKS_SHA256,line))!=null) {
    			//res = res.replace(":", "");   // cert manager return fingerprint , no ":"
    			vo.setCertFingerPrint(res);
    		} 
        }
       int len =0;
       // Create CN from OwnerCN field
        String OwnerCN  = vo.getKSOwnerCN();
        if (null!=OwnerCN) {
	          len = OwnerCN.indexOf("CN=");
	        
	        if (len>0 ) {
	        		int end = OwnerCN.indexOf(",",len);
	        		if (end>len) {
	        			vo.setCN(OwnerCN.substring(len+3,end));
	        		}
	        }
        }
        // Create Enviroment from ksDirectory
        len = ksDirectory.indexOf("/keystores");
        Log.debug("len="+len+",ksDirectory="+ksDirectory);
        if (len!=-1) {
			String envPath = ksDirectory.substring(0,len);
			File f= new File(envPath);
			vo.setEnvironment(f.getName());
        }
    	return vo;
    }
	
    
    //--------------------------Low level Utilities--------------------------------
	
    public String getValue(String key,String line) {
    	int len =line.indexOf(key);
    	
    	if (line.indexOf(key)!=-1) {
    		String s = line.substring(len);
    		String arr[] = s.split(":");
    		
    		if (arr.length>0) {
    			int l = line.indexOf(":")+2;
    			return line.substring(l);
    			 
    		}
    	}
    	
    	return null;
    }
    
    // Get Expire Date
    public  boolean isCertExpired(KeystoreVO vo,long expDateSM[]) {
         
       
        final String DATE_FORMAT ="MMM dd hh:mm:ss z yyyy";
        try {
        	 SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
        	 Date date = fmt.parse(vo.getExpireDate());
        	
        	 long expiredMS = date.getTime();   // get expired date millis seconds from 1970,01,01
        	 expDateSM[0] = expiredMS; 
        	 long currentMS = System.currentTimeMillis(); //+6290724806L;
        	 long diff = currentMS - expiredMS;
        	// Log.debug("Expire datetime:"+date+" has "+expiredMS+" millis seconds,  different from current time: "+diff); 
        	 if (diff<=0) {
        		 return false;
        	 } else {
        		 long days = (diff / (60*60*24*1000));
        		 String msg ="The Certificate already expired in " +days+ " days ago, keystore: "+ vo.getKeyStoreName();
        		// Log.info(msg);
        		 vo.setMessage(msg);
        		 if (Constants.DAYS_TO_EXPIRED>days) {
        			 return true;
        		 }
        	 }
        	 
        	 
        } catch (Exception e) {
        	Log.info(e.getMessage());
        }
        return false;
    } 
    
	 
    
    // Based Scott Ties, Certificate[1] should be finally CA and Certificate[3] or [n] is root CA
    // Certificate[1] should be CA that our CSR send to
    public List<List<String>> getAliasListByCertificateLength(List<String> origList,String alias) {
    	List<List<String>> results = new ArrayList<List<String>>(); 
    	int start=0;
    	int end=0;
    	boolean first = true;
    	if (null==alias) {
    		alias = "Alias name:";
    	}
    	for (int i=0; i<origList.size();i++) {
    		 String s = origList.get(i);
    		 if (s.indexOf(alias)!=-1 && first) {
    			 start=i;
    			 first=false;
    		 }
    		 if (start>0) {
    			 end =i;
    			 if (s.indexOf("**************")!=-1) {
    				 break;
    			 }
      		 }
    	}
    	List<String> aliasList= origList.subList(start, end+1);
    	
    	String res = null;
    	int certLength =1;
    	for (String line : aliasList) {
    		if ((res=getValue(Constants.JKS_CERTIFICATE_CHAIN_LENGTH,line))!=null) {
    			//Log.info("Certificate chain length="+res);
    			if (res.indexOf("1")!=-1) {
    				results.add(aliasList);
    				return results;
    			}
    		} 
    	}
    	certLength =1;
    	
    	for (int i=0; i <aliasList.size();i++) {
    		String line = aliasList.get(i);
    		//System.out.println(i+" "+line);
 			if (line.indexOf("Certificate["+String.valueOf(certLength)+"]:")!=-1) {
				start=i;
				
			}	
 			if (start>0) {
	   			 
	   			 if (line.indexOf("**************")!=-1 || line.indexOf("Certificate["+String.valueOf(certLength+1)+"]:")!=-1 ) {
	   				end =i;
	   				//Log.debug("Certificate chain lines start with "+start+",end at "+end);
	   				results.add(aliasList.subList(start, end));
	   				start=i;
	   				certLength++;
	   				
	   			 }
     		 }
    	}
    	return results;
    }
	// List jks or p12 keystore to List lines
    public List<String> listKeystore(String ksDirectory,String keyStoreName, String storePassword,String keyPassword) {
    	List<String> list = new ArrayList<String>();
    	String command = " -list "+
                " -v "+
                " -keystore "+ksDirectory+keyStoreName+
                " -storepass "+storePassword+
                " -keypass "+keyPassword;
    	 
    	 String result=DoKeytool.execute(command,false);
    	 if (null==result) {
    		 list.add(Constants.WRONG_KEYTOOL_COMMAND);
    		 return list;
    	 } else if (result.contains(Constants.WRONG_KEYSTORE_PASSWORD)) {
    		 list.add(Constants.WRONG_KEYSTORE_PASSWORD);
    		 return list;
    	 } else if (result.contains(Constants.TEMPERED_KEYSTORE)) {
    		 list.add(Constants.TEMPERED_KEYSTORE);
    		 return list;
    	 }
    	 
    	 
    	 String lines[] = result.split("\\n");
    	 for (int i=0;i<lines.length;i++) {
    		 String line=lines[i];
         	 if (line.trim().length()>0) {
         		list.add(line);
         		//System.out.println(line);
           	 }
    	 }
    	 return list;
     }
	// List jks or p12 keystore to List lines
    public String listKeystoreImage(String ksDirectory,String keyStoreName, String storePassword,String keyPassword) {
   // 	List<String> list = new ArrayList<String>();
    	String command = " -list "+
                " -v "+
                " -keystore "+ksDirectory+keyStoreName+
                " -storepass "+storePassword+
                " -keypass "+keyPassword;
    	 
    	 String result=DoKeytool.execute(command,false);
    	 
    	 if (null==result) {
    		 Log.info(Constants.WRONG_KEYTOOL_COMMAND);
    		 return Constants.WRONG_KEYTOOL_COMMAND;
    		 
    	 } else if (result.contains(Constants.WRONG_KEYSTORE_PASSWORD)) {
    		 Log.info(Constants.WRONG_KEYSTORE_PASSWORD+" for "+keyStoreName);
    		 return Constants.WRONG_KEYSTORE_PASSWORD;
    		 
    	 } else if (result.contains(Constants.TEMPERED_KEYSTORE)) {
    		 Log.info(Constants.TEMPERED_KEYSTORE+" for "+keyStoreName);
    		 return Constants.TEMPERED_KEYSTORE;
    		 
    	 }
       
    	 return result;
  }
    // retrieve all lines belongs to given alias
    public List<String> getAliasList(List<String> origList,String alias) {
    	List<String> results = new ArrayList<String>(); 
    	int start=0;
    	int end=0;
    	boolean first = true;
    	for (int i=0; i<origList.size();i++) {
    		 String s = origList.get(i);
    		 if (s.indexOf(alias)!=-1 && first) {
    			 start=i;
    			 first=false;
    		 }
    		 if (start>0) {
    			 end =i;
    			 if (s.indexOf("**************")!=-1) {
    				 break;
    			 }
      		 }
    	}
    	List<String> res=origList.subList(start, end);
    	//for (String l:res) System.out.println(l); 
    	return res;
    }
    
}
