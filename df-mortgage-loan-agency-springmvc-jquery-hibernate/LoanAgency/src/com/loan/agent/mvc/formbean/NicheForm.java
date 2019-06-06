package com.loan.agent.mvc.formbean;

import java.io.Serializable;

import com.loan.agent.dao.Agents;
import com.loan.agent.dao.Niches;

public class NicheForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nicheId=null;	
	private Integer agentId=null;
	private String nicheTitle=null;
	private String nicheNote=null;
	private String nicheChoose=null;
	private String actionType=null; 
	
	public void renderNiche(Niches n) {
        nicheId=n.getNicheId().toString();	
        agentId=n.getAgentId();
        nicheTitle=n.getNicheTitle();
        nicheNote=n.getNicheNote();
        nicheChoose=n.getNicheChoose();
	}
	public Niches modelNiches() {
		Niches n = new Niches();
	//	n.setNicheId(new Integer(nicheId));
		n.setAgentId(agentId);
		n.setNicheTitle(nicheTitle);
		n.setNicheNote(nicheNote);
		n.setNicheChoose(nicheChoose);
		return n;
	}
	public String getNicheId() {
		return nicheId;
	}
	public void setNicheId(String nicheId) {
		this.nicheId = nicheId;
	}
	public Integer getAgentId() {
		return agentId;
	}
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
	public String getNicheTitle() {
		return nicheTitle;
	}
	public void setNicheTitle(String nicheTitle) {
		this.nicheTitle = nicheTitle;
	}
	public String getNicheNote() {
		return nicheNote;
	}
	public void setNicheNote(String nicheNote) {
		this.nicheNote = nicheNote;
	}
	public String getNicheChoose() {
		return nicheChoose;
	}
	public void setNicheChoose(String nicheChoose) {
		this.nicheChoose = nicheChoose;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
}
