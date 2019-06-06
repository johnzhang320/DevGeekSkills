package com.loan.agent.dao;

/**
 * SessionManager entity. @author MyEclipse Persistence Tools
 */

public class SessionManager implements java.io.Serializable {

	// Fields

	private Integer managerId;
	private String clientMachineName;
	private String loginAgentSessionId;
	private Integer loginAgentId;
	private Integer paramAgentId;
	private String loginAgentName;
	private String paramAgentName;

	// Constructors

	/** default constructor */
	public SessionManager() {
	}

	/** minimal constructor */
	public SessionManager(String clientMachineName) {
		this.clientMachineName = clientMachineName;
	}

	/** full constructor */
	public SessionManager(String clientMachineName, String loginAgentSessionId,
			Integer loginAgentId, Integer paramAgentId, String loginAgentName,
			String paramAgentName) {
		this.clientMachineName = clientMachineName;
		this.loginAgentSessionId = loginAgentSessionId;
		this.loginAgentId = loginAgentId;
		this.paramAgentId = paramAgentId;
		this.loginAgentName = loginAgentName;
		this.paramAgentName = paramAgentName;
	}

	// Property accessors

	public Integer getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getClientMachineName() {
		return this.clientMachineName;
	}

	public void setClientMachineName(String clientMachineName) {
		this.clientMachineName = clientMachineName;
	}

	public String getLoginAgentSessionId() {
		return this.loginAgentSessionId;
	}

	public void setLoginAgentSessionId(String loginAgentSessionId) {
		this.loginAgentSessionId = loginAgentSessionId;
	}

	public Integer getLoginAgentId() {
		return this.loginAgentId;
	}

	public void setLoginAgentId(Integer loginAgentId) {
		this.loginAgentId = loginAgentId;
	}

	public Integer getParamAgentId() {
		return this.paramAgentId;
	}

	public void setParamAgentId(Integer paramAgentId) {
		this.paramAgentId = paramAgentId;
	}

	public String getLoginAgentName() {
		return this.loginAgentName;
	}

	public void setLoginAgentName(String loginAgentName) {
		this.loginAgentName = loginAgentName;
	}

	public String getParamAgentName() {
		return this.paramAgentName;
	}

	public void setParamAgentName(String paramAgentName) {
		this.paramAgentName = paramAgentName;
	}

}