package com.loan.agent.services;

import java.io.Serializable;

public class SelectedValueVo implements Serializable {
	private static final long serialVersionUID = 1L;
	String label;
	String value;
	String selected;
	public SelectedValueVo() {}
	public SelectedValueVo(String label, String value, String selected) {
		super();
		this.label = label;
		this.value = value;
		this.selected = selected;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
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
