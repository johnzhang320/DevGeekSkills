package com.keystore.x509.certificate.model;
/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 2/2018
 */
import java.util.ArrayList;
import java.util.List;

public class EnvironmentArrayVO {
	private List<EnvironmentVO> keystoreEnvironments = new ArrayList<EnvironmentVO>();

	 
	public EnvironmentArrayVO() {
		 
	}
	public EnvironmentArrayVO(List<EnvironmentVO> keystoreEnvironments) {
		super();
		this.keystoreEnvironments = keystoreEnvironments;
	}

	public List<EnvironmentVO> getKeystoreEnvironments() {
		return keystoreEnvironments;
	}

	public void setKeystoreEnvironments(List<EnvironmentVO> keystoreEnvironments) {
		this.keystoreEnvironments = keystoreEnvironments;
	}

}
