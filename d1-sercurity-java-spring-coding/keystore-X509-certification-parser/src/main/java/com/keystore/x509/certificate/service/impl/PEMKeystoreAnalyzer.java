package com.keystore.x509.certificate.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.keystore.x509.certificate.model.CAVO;
import com.keystore.x509.certificate.model.KSConfigureVO;
import com.keystore.x509.certificate.model.KeystoreVO;
import com.keystore.x509.certificate.service.KeystoreBuilder;
/**
 * 
 * @author John Zhang at intelliswift 12/2017
 *
 */
public class PEMKeystoreAnalyzer implements KeystoreBuilder {
	public KeystoreVO getKSOwner(KSConfigureVO ksConfVO) {
		KeystoreVO vo = new KeystoreVO();
		return vo;
	}
	public KeystoreVO getKSOwner(String ksDir,String keystoreName, String storePassword, String keypassword) {
		KeystoreVO vo = new KeystoreVO();
		return vo;
	}
	public KeystoreVO getTrustCAs(KeystoreVO ksvo) {
		KeystoreVO results = ksvo;
		return results;
	}
}
