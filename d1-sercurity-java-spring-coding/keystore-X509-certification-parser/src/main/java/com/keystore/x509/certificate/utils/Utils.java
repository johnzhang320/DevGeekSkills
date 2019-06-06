package com.keystore.x509.certificate.utils;
/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 12/2017
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.keystore.x509.certificate.model.KSConfigureVO;
import com.keystore.x509.certificate.model.KeystoreVO;
 

public class Utils  {
	static Logger Log = Logger.getLogger(Utils.class);
	private static String localhost=null;
	public static String getLocalHostName() {
		if (localhost==null) {
 			try {
		        InetAddress addr = java.net.InetAddress.getLocalHost();    
		        int len = addr.toString().indexOf("/");
		        localhost = addr.toString().substring(0, len);
		    } catch (UnknownHostException e) {
		        Log.info(e.getMessage());
	 	    } 
		}
		return localhost;
	}
	public static String getDateStr(long dateTimeInMs) {
		String retVal=null;
		Date toDate = new Date(dateTimeInMs);
		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy:HH:mm:ss");
			retVal=df.format(toDate);
		} catch (Exception e) {}
		return retVal;
	}
	public static boolean isFileExists(String fileDirectory,String filename) {
		String fullname=null;
		 
		if (!fileDirectory.endsWith("/")) {
			fullname = fileDirectory+"/"+filename;
		} else {
			fullname = fileDirectory+filename;
		}
		return isFileExists(fullname);
	}
	public static boolean isFileExists(String fullname) {
		File file =new File(fullname);
		if (!file.exists()) {
			
			return false;
		}
		return true;
	} 
	
	public static boolean isValidKSConfigureVO(KSConfigureVO vo) { 
		 
		KeystoreVO ksvo = new KeystoreVO(vo);
 		
		if (!isValidKeystoreVO(ksvo)) {
			Log.info("KSConfigureVO is not initialized probably!");
			return false;
		}
		return false;
	}
	public static boolean isValidKeystoreVO(KeystoreVO vo) { 
		String ksname = vo.getKeyStoreName();
		String ksdir = vo.getKsDirectory();
		String storepass = vo.getStorePassword();
		String keypass = vo.getKeyPassword();
		String alias = vo.getAlias();
		
		if (null==ksname || ksname.length()==0) {
			Log.info("Keystore name is not initialized!");
	 		return false;
		}  
		
		if (null==ksdir || ksdir.length()==0) {
			Log.info("Key directory is not initialized!");
			return false;
		}
		
		if (null==alias || alias.length()==0) {
			Log.info("Alias is not initialized!");
			return false;
		}
		
		// check if keystore file physically exists in OS 
		if (!Utils.isFileExists(ksdir, ksname)) {
			Log.info("Keystore file does not exists! " +ksdir+"/"+ksname);
			return false;
		}
		
	    if (null==storepass || storepass.length()==0) {
			Log.info("Keystore store password is not initialized!");
			return false;
		}
	    if (null==keypass || keypass.length()==0) {
			Log.info("Keystore key password is not initialized!");
			return false;
		}
		return true;
	}
	
	public static String concateDirToFilename(String dir, String filename) {
		if (null==dir || dir.length()==0 || null==filename || filename.length()==0) {
			return null;
		}
		if (dir.endsWith("/")) {
			return dir+filename;
		}
		return dir+"/"+filename;
	}
	//-----------------Utilities for File List<String> ------------------
	public static List<String> findFilesInParentDir(String parentDir) {
		List<String> fileList = new ArrayList<String>();
		findFilesInDirectory(parentDir,fileList);
		return fileList;
	}
	public static List<String> findFilesInParentDir(String parentDir,String suffix,String dirfilter) {
		List<String> fileList = new ArrayList<String>();
		if (null==dirfilter || dirfilter.equalsIgnoreCase("*")) {
			findFilesInDirectory(parentDir,fileList,suffix);
		} else {
			String filter[] = dirfilter.split(",");
			String currentDir = "";
			for (String f:filter) {
				if (!parentDir.endsWith("/")) {
					currentDir = parentDir+"/"+f;
				} else {
					currentDir = parentDir+f;
				}
 				File directory = new File(currentDir);
				 
				File[] fList = directory.listFiles();
				if (null==fList || fList.length==0) {
					return fileList;
				}
				Log.info("");
				 for (File file : fList) {
					 fileList.add(file.getAbsolutePath());   // put file to file list
					 
				 }
				 
			}
		}
		return fileList;
	}
	
	public static List<String> findFilesInParentDir(String parentDir,String suffix) {
		List<String> fileList = new ArrayList<String>();
		findFilesInDirectory(parentDir,fileList,suffix);
		return fileList;
	}
	public static void findFilesInDirectory(String parentDir,List<String> fileList) {
		 
		 File directory = new File(parentDir);
		// get all the files from a directory
		 File[] fList = directory.listFiles();
		 for (File file : fList) {
		        if (file.isFile()) {
		            //System.out.println(file.getAbsolutePath());
		            fileList.add(file.getAbsolutePath());
		        } else if (file.isDirectory()) {
		        	findFilesInDirectory(file.getAbsolutePath(),fileList);
		        }
		 }  
	}
	public static void findFilesInDirectory(String parentDir,List<String> fileList,String suffix) {
		 
		 File directory = new File(parentDir);
		// get all the files from a directory
		 File[] fList = directory.listFiles();
		 for (File file : fList) {
		        if (file.isFile()) {
		            //System.out.println(file.getAbsolutePath());
		        	String f =file.getAbsolutePath();
		        	if (f.endsWith(suffix)) {
		        		fileList.add(file.getAbsolutePath());
		        	}
		        } else if (file.isDirectory()) {
        			findFilesInDirectory(file.getAbsolutePath(),fileList,suffix);
		        }
		 }  
	}
	public static String getButtomdir(String parent) {
		String subdir=null;
		int len = parent.length()-1;
		if (parent.charAt(len)=='/') {
			len--;
		}
		for (int i=len; i>=0;i--) {
    		if (parent.charAt(i)=='/') {
    			subdir = parent.substring(i+1);
    			break;
    		}
    	}
		return subdir;
	}
	public static String getPathRemoveFirstSeparate(String path) {
		String formEnvPath = Utils.lrTrim(path);
        if (formEnvPath.subSequence(0, 1).equals("/") ) {
        		formEnvPath = formEnvPath.substring(1);
        }
        return formEnvPath;
	}
	
	public static String getValue(String key,String line) {
    	int len =line.indexOf(key);
    	
    	if (line.indexOf(key)!=-1) {
    		String s = line.substring(len);
    		String arr[] = s.split(":");
    		
    		if (arr.length>0) {
    			int l = line.indexOf(":")+1;
    			return line.substring(l);
    		}
    	}
    	return null;
    }
	// contain one ':' in line
	public static String getValueFromColon(String line) {
  	    if (line.indexOf(":")==-1) {
	    	return null;
	    }
		 
		String arr[] = line.split(":");
		
		if (arr.length>0) {
			int l = line.indexOf(":")+1;
			return line.substring(l);
		}
    	 
    	return null;
    }
	public static String clearPropValue(String str) {
		if (null==str || str.length()==0) {
			return null;
		}
		if (str.contains("#")) {
			int l = str.indexOf("#");
			str = str.substring(0,l-1);
		}
		if (str.contains("//")) {
			int l = str.indexOf("//");
			str = str.substring(0,l-1);
		}
		return str.trim();
	}
	public static String getFilename(String fullpath) {
		 
		File file = new File(fullpath);
		return file.getName();
		 
	}
	public static String getFileDirectory(String fullpath) {
		 
		File file = new File(fullpath);
		return file.getParent();
		 
	}
	public static String[] getFileParam(String fullpath) {
		String retVal[] = new String[2];
		File file = new File(fullpath);
		String name = file.getName();
		String path = file.getParent();
		retVal[0] = path;
		retVal[1] = name;
		return retVal;
	}
	// Remove any char from left and right side of string, pattern could be ' ', ',','%'..... 
	public static String lrTrim(String s) {
		return lrTrim(s, " ");
	}
	public static String lrTrim(String s, String pattern) {
		int i=0,j=s.length();
		for (i=0;i<s.length();i++) { // remove from left
			char c = s.charAt(i);
			if (pattern.indexOf(c)==-1) {
			    break;	
			}
			
		}
		if (i==s.length()) return " ";
		for (j=s.length()-1;j>=0;j--) {
			if (pattern.indexOf(s.charAt(j))==-1) {
			    break;	
			}
		}
		
		return s.substring(i,j+1);
	}
	public static String readFileRemoveEmptyLines(String fullpathOfFile) {
		StringBuffer buf = new StringBuffer();
		List<String> list = readFileLines(fullpathOfFile);
		for (String s: list) {
			buf.append(s);
		}
		return buf.toString();
	}
	
	public static String readFileExactly(String fullpathOfFile) {
		StringBuffer buf = new StringBuffer();
		List<String> list = readFileLinesExact(fullpathOfFile);
		for (String s: list) {
			buf.append(s);
		}
		return buf.toString();
	}
	public static boolean isEmptyStr(String s) {
		if (null==s || s.trim().length()==0) {
			return true;
		}
		return false;
	}
	
	
	public static List<String> readFileLines(String fullpathOfFile) {
	 
			File file = new File(fullpathOfFile);
			if (!file.exists()) {
				Log.error("file doesn't exist! "+fullpathOfFile); 
				return null;
			}
			List<String> result = new ArrayList<String>();
			BufferedInputStream bis = null;
			FileInputStream fi = null;
			BufferedReader br = null;
			try {
				fi = new FileInputStream(file);
				bis = new BufferedInputStream(fi);
				br = new BufferedReader(new InputStreamReader(bis));
				
				String line=null; 
				 
				while((line=br.readLine())!=null) {
					if (null==line && line.trim().length()==0) {
						continue;
					} else {
						result.add(line+"\n");
					}
				}				
			} catch (Exception e) {
				Log.error(e.getMessage());
				e.printStackTrace();
				return null;
			} finally {
				if (fi!=null) {
					try {
						fi.close();
						bis.close();
						br.close();
					} catch (Exception e) {}
				}
			}
		 
		return result;
	}
	
	public static List<String> readFileLinesExact(String fullpathOfFile) {
		 
		File file = new File(fullpathOfFile);
		if (!file.exists()) {
			Log.error("file doesn't exist! "+fullpathOfFile); 
			return null;
		}
		List<String> result = new ArrayList<String>();
		BufferedInputStream bis = null;
		FileInputStream fi = null;
		BufferedReader br = null;
		try {
			fi = new FileInputStream(file);
			bis = new BufferedInputStream(fi);
			br = new BufferedReader(new InputStreamReader(bis));
			
			String line=null; 
			 
			while((line=br.readLine())!=null) {
 				result.add(line+"\n");
 			}				
		} catch (Exception e) {
			Log.error(e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			if (fi!=null) {
				try {
					fi.close();
					bis.close();
					br.close();
				} catch (Exception e) {}
			}
		}
	 
	return result;
}	
	/* input /applepay_prod_environment/keystores/prod/myks.jks
		result[0]=applepay_prod_environment
		result[1]=keystores
		result[2]=prod
		result[3]=myks.jks
	    result[4] = /applepay_prod_environment/keystores/prod
    */
	
	public static String[] getEnvironmentVariable(String envPath) {
		if (Utils.isEmptyStr(envPath)) {
			Log.info("Environment path is empty!");
			return null;
		}
		String envp = envPath;
		if (envPath.substring(0, 1).equalsIgnoreCase(File.separator)) {
			envp=envPath.substring(1);
		}
		String result[] = envp.split(File.separator);
		String res[]= new String[5];
		for (int i=0;i<result.length;i++) {
			res[i] = result[i];
		}
		res[4]=result[0]+File.separator+result[1]+File.separator+result[2]+File.separator;
		return res;
 	}
	
	public static void WriteStringToFile(String str,String fullpathOfFile) {
		File file = new File(fullpathOfFile);
 		if (file.exists()) {
 			file.delete();
   		}
 		FileWriter fw = null;
 		try {
			file.createNewFile();
			fw= new FileWriter(file,true);
			fw.write(str);
 		} catch (Exception e) {
			Log.error(e.getMessage());
		} finally {
			if (fw!=null) {
				try {
					fw.close();
				} catch (Exception e) {
					 
				}
			}
		}
	}
	public static void copyFileUsingStream(String sourcePath, String destPath)   {
		File source = new File(sourcePath);
		File dest = new File(destPath);
		if (!source.exists()) {
			Log.error("file does not exist "+sourcePath);
			return;
		}
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	     
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
	    finally {
	    	if (is!=null) {
				try {
					 is.close();
				     os.close();
				     Log.info("Already copied file "+sourcePath+" to "+destPath );
				} catch (Exception e) {
					 
				}
			}
	       
	    }
	}
	public static void copyFileUsingJava7Files(String sourcePath, String destPath) {
		File source = new File(sourcePath);
		File dest = new File(destPath);
		if (!source.exists()) {
			Log.error("file does not exist "+sourcePath);
			return;
		}
		try {
			Files.copy(source.toPath(), dest.toPath());
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
	}
	public static String getCurrentDateTimeStr(Date date) {
	
		String retVal="";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); 
			retVal = sdf.format(date);
		} catch (Exception e) {}
		return retVal;
	}
	
	
	public static String getCurrentDateTimeStr() {
		Date date = new Date();
		return getCurrentDateTimeStr(date);
	}
  
	
	public static void main(String args[]) {
		String result[] = getEnvironmentVariable("/applepay_prod_environment/keystores/prod/myks.jks");
		for (int i=0; i<result.length;i++) {
			Log.info("result["+i+"]="+result[i]);
		}
		Log.info(getCurrentDateTimeStr());
		
	   
		 
		//KeystoreVO [ confDirFile=null, keyStoreName=SPTSPNonProdClient.keystore, ksDirectory=/Users/intelliswift/certRenewMgr/data/gpgrepos/applepay_qa-nc_environment/keystores/qa_nc/, expireDate=Jun 09 16:05:45 PDT 2020, storePassword=FtQsQiGzjoozGiiwTv94mayiP, keyPassword=FtQsQiGzjoozGiiwTv94mayiP, ManagementGroupId=726604, IdentityGroupId=726604, keyStoreType=JKS, truststore=null, certChainLength=3, alias=sptspnonprodclient,Environment=applepay_qa-nc_environment, keyAlg=null, keySize=null, KSOwnerCN=DC=Certificate Manager, O=Apple Inc., OU=management:idms.group.726604, CN=SPTSPNonProdClient.apple.com, UID=identity:idms.group.726604, KSIssureCN=caIssuers, signAlg=SHA256withRSA, CertOwnerCN=DC=Certificate Manager, O=Apple Inc., OU=management:idms.group.726604, CN=SPTSPNonProdClient.apple.com, UID=identity:idms.group.726604, CertIssuerCN=caIssuers, san=null, keystoreEntries=1, certFingerPrint=DB:34:22:75:89:70:07:2E:06:60:13:44:1A:53:49:C2:83:FE:30:16:58:25:98:D3:55:F6:FA:B5:76:6B:F0:3A, certAlg=null, certSignAlg=SHA256withRSA, certkeySize=null, expireDateMS=1591743945000, EntryType=null]
		//2018-06-18 12:39:47,016 INFO  (TestKeystoreVO.java:67).testKeystoreVO() : fingerPrint=06:12:5F:84:3B:ED:97:2B:4E:B1:B7:6D:F3:FA:39:D6:A0:6C:03:20:78:23:D4:17:7B:EE:8B:A8:06:AF:69:88
		
	}
	
	
	
}
