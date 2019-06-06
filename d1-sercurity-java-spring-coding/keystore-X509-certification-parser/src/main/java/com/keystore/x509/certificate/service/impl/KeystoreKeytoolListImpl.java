package com.keystore.x509.certificate.service.impl;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.keystore.x509.certificate.model.KeystoreTextContentVO;
import com.keystore.x509.certificate.model.KeystoreVO;
import com.keystore.x509.certificate.utils.Utils;
import com.keystore.x509.certificate.utils.WorkHome;

public class KeystoreKeytoolListImpl {
	
	static Logger Log = Logger.getLogger(KeystoreKeytoolListImpl.class);
	
	static JKSP12KeystoreAnalyzer analyzer = new JKSP12KeystoreAnalyzer();
	
	public static String writeKeystoreVO(KeystoreVO vo,String contentStatus) {
		Log.info("begin");
		 
		 
		StringBuffer buf = new StringBuffer();
		String targetPathFile = WorkHome.DATA_GPGARCHIVE_CERTCONTENTS_HOME+vo.getKeyStoreName()+"_keytoolcontent_"+contentStatus+".txt";
		File file = new File(targetPathFile);
		
		if (file.exists()) {
			Log.info(targetPathFile+" exists duplicated file ");
 		}
		StringBuffer retVal = new StringBuffer();
		String tab="";
		 
		
		String output = analyzer.listKeystoreImage(vo.getKsDirectory(), vo.getKeyStoreName(), vo.getStorePassword(), vo.getKeyPassword());
 		
		if (null==output || output.contains("password was incorrect")) {
			Log.info("password was incorrect for "+vo.getKsDirectory()+vo.getKeyStoreName());
			Log.info("Do not write keytool content!");
			return "password was incorrect";
		}
		
		Utils.WriteStringToFile(output, targetPathFile);
		
		Log.info("End");
		return targetPathFile;
	}	
	// keystore text content return to byte returnByte[] and file name to return as String
	public static KeystoreTextContentVO readKeystoreTextContent(KeystoreVO vo,String contentStatus) {
		Log.info("begin");
 		KeystoreTextContentVO retVal = new KeystoreTextContentVO();
		StringBuffer buf = new StringBuffer();
		// we create mapdictionary first then we change data/gpgrepos/ tp data/mapdictionary if read old keystore
		String ksDir =vo.getKsDirectory();
		if (contentStatus.contains("old")) {
		   ksDir = ksDir.replace("gpgrepos", "mapdictionary");
		}
  		String output=analyzer.listKeystoreImage(ksDir, vo.getKeyStoreName(), vo.getStorePassword(), vo.getKeyPassword());
		retVal.setKeystoreContent(output.getBytes());
		retVal.setFilename(vo.getKeyStoreName()+"_keytoolcontent_"+contentStatus+".txt");
		return retVal;
	}	
	
	public static void main(String args[]) {
		
		KeystoreVO vo = analyzer.getKeystore("/Users/intelliswift/certRenewMgr/data/gpgrepos/applepay_qa-nc_environment/keystores/qa_nc/", 
											"SPTSPNonProdClient.keystore", "FtQsQiGzjoozGiiwTv94mayiP", "FtQsQiGzjoozGiiwTv94mayiP");
		KeystoreKeytoolListImpl.writeKeystoreVO(vo, "old");
	}
}
