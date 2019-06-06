package com.keystore.x509.certificate.test;
/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 12/2017
 */
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.keystore.x509.certificate.model.KSConfigureVO;
import com.keystore.x509.certificate.model.KeystoreMap;
import com.keystore.x509.certificate.model.KeystoreVO;
import com.keystore.x509.certificate.service.KeystoreBuilder;
import com.keystore.x509.certificate.service.KeystoreFactory;
import com.keystore.x509.certificate.service.impl.JKSP12KeystoreAnalyzer;
import com.keystore.x509.certificate.utils.WorkHome;

import junit.framework.TestCase;

public class TestKeystoreVOResultInFile extends TestCase {
	static Logger Log = Logger.getLogger(TestKeystoreVOResultInFile.class);
	public void setUp() {
		
	}
	public void testKSConfigureVOKeystoreVOManager() {
		List<KSConfigureVO> results =  new ArrayList<>();//KSConfigureAnalyzer.getKSConfigureFromJSONConfMap();
	 	Set<String> groupIds = new TreeSet<String>();
		int count=1;
		  
		File file = new File(WorkHome.TESTS_GPG_RETRIEVE_HOME+"result.log");
 		if (file.exists()) {
 			file.delete();
   		}
		File fileErr = new File(WorkHome.TESTS_GPG_RETRIEVE_HOME+"error.log");

 		if (fileErr.exists()) {
 			fileErr.delete();
   		}
		FileWriter fw = null;
		FileWriter fwErr = null;
		try {
			file.createNewFile();
			fw= new FileWriter(file,true);
			fileErr.createNewFile();
			fwErr= new FileWriter(fileErr,true);
			Set<String> set = new HashSet<String>();
			fw.write("--------------------------------KSConfigureVO--------------------------------------------------\n");
			for (KSConfigureVO vo:results) {
				String filename=vo.getKeyStoreName();
				/*if (!set.contains(filename)) {
					set.add(filename);
				} else {
					System.out.println("duplicate keystore name="+filename+",folder="+vo.getKsDirectory());
				} */
				String str = String.valueOf(count)+"  "+vo.toString()+"\n";
				fw.write(str);
				System.out.println(count+"  "+vo.toString());
	  			count++;
			}
		 
			count=1;
			int ErrCnt = 1;
			fw.write("\n\n");
			fw.write("-------------------------------------KeystoreVO---------------------------------------------------\n");
			System.out.println("-------------------------------------KeystoreVO---------------------------------------------------\n");
			
			for (KSConfigureVO vo:results) {
				String s = vo.getMessage().toString();
				//Log.info(vo.toString());
	  			if (s.contains("repeated refer") || s.contains("does not contain any password")) {
	  				String enter="\n";
	  				String str =enter+String.valueOf(ErrCnt)+"  "+vo.getMessage().toString()+enter+"\n";
	  				fwErr.write(str);
					//System.out.println(str);
		  			ErrCnt++; 
		  			/*if (s.contains("repeated refer")) { 
		  				continue;
		  			}*/
	  			}
				KeystoreBuilder ksb = KeystoreFactory.getKeystoreBuilder(vo);
				KeystoreVO ksvo = ksb.getKSOwner(vo);
				String groupId = Optional.ofNullable(ksvo.getManagementGroupId()).orElse("0");
				if (!groupIds.contains(groupId)) {
					groupIds.add(groupId);
				}
				if (null!=ksvo) {
			        String enter="";
			      
			        if (ksvo.getMessage().length()>1) {
			        	enter="\n";
			        	
			        }
					String str = enter+String.valueOf(count)+"  "+ksvo.toString()+enter+"\n";
					fw.write(str);
					System.out.println(enter+count+"  "+ksvo.toString()+enter);
		  			count++;
					
				}				
			}
			Log.info("group Id as following:");
			count=1;
			for (String s:groupIds) {
				System.out.println(s);
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw!=null) {
				try {
					fw.close();
					fwErr.close();
				} catch (Exception e) {
					 
				}
			}
		}
	}
	 
	
}
