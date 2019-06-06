package com.keystore.x509.certificate.utils;
/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 12/2017
 */
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.keystore.x509.certificate.model.PGPRepoPropertiesVO;
public class FetchPropertyData {
	static Logger LOG = Logger.getLogger( FetchPropertyData.class);	 
	
	public PGPRepoPropertiesVO getPropertyData() {
		InputStream in =null;
		PGPRepoPropertiesVO vo = new PGPRepoPropertiesVO();
		try {
			// create and load default properties
			Properties pr = new Properties();		
			
			in = getClass().getClassLoader().getResourceAsStream("restclient.properties");
			pr.load(in);
			vo.setKsconfigure_fullname(pr.getProperty(Constants.KSCONFIGURE_FULLNAME));
			vo.setCA_server_host(pr.getProperty(Constants.CA_SERVER_HOST)); 
			vo.setCA_server_ip(pr.getProperty(Constants.CA_SERVER_IP)); 
			vo.setCA_server_port(pr.getProperty(Constants.CA_SERVER_PORT)); 
			 
			LOG.info(vo.toString());
		} catch (Exception e) {
			LOG.error("Failure: "+e.getMessage());
		} finally {
			if (in!=null) {
				try {
					in.close();
				} catch(Exception e){}
			}
		}
		return vo;
	}
	
}
