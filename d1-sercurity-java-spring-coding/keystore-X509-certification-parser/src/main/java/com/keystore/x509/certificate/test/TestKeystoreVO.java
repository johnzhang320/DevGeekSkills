package com.keystore.x509.certificate.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.keystore.x509.certificate.model.KSConfigureVO;
import com.keystore.x509.certificate.model.KeystoreVO;
import com.keystore.x509.certificate.service.KeystoreBuilder;
import com.keystore.x509.certificate.service.KeystoreFactory;
import com.keystore.x509.certificate.service.impl.JKSP12KeystoreAnalyzer;

import junit.framework.TestCase;

public class TestKeystoreVO extends TestCase {
	static Logger Log = Logger.getLogger(TestKeystoreVO.class); 	 
	private static Map<String,KeystoreVO> keystoreMap = new LinkedHashMap<String,KeystoreVO>();
	private JKSP12KeystoreAnalyzer ref =null;
	public void setUp() {
		ref=new JKSP12KeystoreAnalyzer();
	}

	public void testKeystoreVO() {
		List<KSConfigureVO> results = new ArrayList<>();//KSConfigureAnalyzer.getKSConfigureFromJSONConfMap();
		Log.info("result.size()="+results.size());
		int count=1;
		for (KSConfigureVO vo:results) {
  			 
			//KeystoreBuilder ksb = KeystoreFactory.getKeystoreBuilder(vo);
			//KeystoreVO ksvo = ksb.getKSOwner(vo);
			
			KeystoreVO ksvo = ref.getKeystore(vo.getKsDirectory(),
					                         vo.getKeyStoreName(),
					                         vo.getStorePassword(),
					                         vo.getKeyPassword()); 
			 
			 
			if (null!=ksvo) {
		 
				String str = String.valueOf(count)+"  "+ksvo.toString()+"\n";
				 
				System.out.println(count+"  "+ksvo.toString());
	  			count++;
	  			if (!keystoreMap.containsKey(ksvo.getCertFingerPrint())) {
	  				keystoreMap.put(ksvo.getCertFingerPrint(), ksvo);
	  				//Log.info("fingerPrint added "+ksvo.getCertFingerPrint());
	  			} else {
	  				//Log.info("fingerPrint deplicated! "+ksvo.getCertFingerPrint());
	  				KeystoreVO preVO = keystoreMap.get(ksvo.getCertFingerPrint());
	  				//Log.info("Existing keystore name "+preVO.getKeyStoreName()+",ksDir="+preVO.getKsDirectory());
	  				//Log.info("Current keystore name "+ksvo.getKeyStoreName()+",ksDir="+ksvo.getKsDirectory());
	  			}
	  			
	  			System.out.println("keystoreMap.size()="+keystoreMap.size());
			}				
		}
		Log.info("keystoreMap.size()="+keystoreMap.size());
		Iterator<String> itr = keystoreMap.keySet().iterator(); 
		while (itr.hasNext()) {
			String fingerPrint = itr.next();
			KeystoreVO vo = keystoreMap.get(fingerPrint);
			Log.info("fingerPrint="+fingerPrint);
			Log.info(vo.toString());
		}  
		 
		
	}
}
