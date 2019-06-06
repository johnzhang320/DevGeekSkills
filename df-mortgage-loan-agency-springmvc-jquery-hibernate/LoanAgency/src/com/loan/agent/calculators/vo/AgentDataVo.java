package com.loan.agent.calculators.vo;

import java.io.Serializable;
import java.util.Comparator;

public class AgentDataVo  implements Serializable {
	 private static final long serialVersionUID = 1L;
	String agentDataId;
	String value;
	String selected;
	
	public AgentDataVo() {
		
	}
	public AgentDataVo(String agentDataId, String value, String selected) {
		super();
		this.agentDataId = agentDataId;
		this.value = value;
		this.selected = selected;
	}
	public String getAgentDataId() {
		return agentDataId;
	}
	public void setAgentDataId(String agentDataId) {
		this.agentDataId = agentDataId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	
 
}
