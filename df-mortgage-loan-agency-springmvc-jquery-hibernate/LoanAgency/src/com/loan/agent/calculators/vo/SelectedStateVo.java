package com.loan.agent.calculators.vo;

import java.io.Serializable;

public class SelectedStateVo implements Serializable {
	 private static final long serialVersionUID = 1L;
	String stateKey;
	String stateName;
	String selected="false";
	public String getStateKey() {
		return stateKey;
	}
	public void setStateKey(String stateKey) {
		this.stateKey = stateKey;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	
}
