package com.loan.agent.dao;

/**
 * Niches entity. @author MyEclipse Persistence Tools
 */

public class Niches implements java.io.Serializable {

	// Fields

	private Integer nicheId;	
	private String nicheIdStr;	
	private Integer agentId;
	private String nicheTitle;
	private String nicheNote;
	private String nicheChoose;
	 

	// Constructors

	/** default constructor */
	public Niches() {
	}

	/** minimal constructor */
	public Niches(Integer agentId, String nicheTitle) {
		this.agentId = agentId;
		this.nicheTitle = nicheTitle;
	}

	/** full constructor */
	public Niches(Integer agentId, String nicheTitle, String nicheNote,
			String nicheChoose) {
		this.agentId = agentId;
		this.nicheTitle = nicheTitle;
		this.nicheNote = nicheNote;
		this.nicheChoose = nicheChoose;
	}

	// Property accessors

	public Integer getNicheId() {
		return this.nicheId;
	}
	 
	public String getNicheIdStr() {
		return nicheId.toString();
	}

	public void setNicheIdStr(String nicheIdStr) {
		this.nicheIdStr = nicheIdStr;
	}

	public void setNicheId(Integer nicheId) {
		this.nicheId = nicheId;
	}

	public Integer getAgentId() {
		return this.agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getNicheTitle() {
		return this.nicheTitle;
	}

	public void setNicheTitle(String nicheTitle) {
		this.nicheTitle = nicheTitle;
	}

	public String getNicheNote() {
		return this.nicheNote;
	}

	public void setNicheNote(String nicheNote) {
		this.nicheNote = nicheNote;
	}

	public String getNicheChoose() {
		return this.nicheChoose;
	}

	public void setNicheChoose(String nicheChoose) {
		this.nicheChoose = nicheChoose;
	}

}