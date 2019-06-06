package com.keystore.x509.certificate.model;
/**
 *  Created by John Zhang (john_zhang3@apple.com)
 *  Created Date: 12/2017
 */
public class PGPRepoPropertiesVO {
	private String  ksconfigure_fullname;
	private String  CA_server_host;
	private String CA_server_ip;
	private String CA_server_port;
	public String getKsconfigure_fullname() {
		return ksconfigure_fullname;
	}
	public void setKsconfigure_fullname(String ksconfigure_fullname) {
		this.ksconfigure_fullname = ksconfigure_fullname;
	}
	public String getCA_server_host() {
		return CA_server_host;
	}
	public void setCA_server_host(String cA_server_host) {
		CA_server_host = cA_server_host;
	}
	public String getCA_server_ip() {
		return CA_server_ip;
	}
	public void setCA_server_ip(String cA_server_ip) {
		CA_server_ip = cA_server_ip;
	}
	public String getCA_server_port() {
		return CA_server_port;
	}
	public void setCA_server_port(String cA_server_port) {
		CA_server_port = cA_server_port;
	}
	@Override
	public String toString() {
		return "InitialPropertyVO [ksconfigure_fullname=" + ksconfigure_fullname + ", CA_server_host=" + CA_server_host
				+ ", CA_server_ip=" + CA_server_ip + ", CA_server_port=" + CA_server_port + "]";
	}
	
	

}
