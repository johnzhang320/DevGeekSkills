package com.loan.agent.mvc.formbean;

import java.io.Serializable;

import com.loan.agent.dao.AppCheckList;
 

public class AppCheckListForm implements Serializable {
	 private static final long serialVersionUID = 1L;
	private String checkId;
	private String title;
	private String note;
	private String choose;
	private String actionType=null; 
	private String value;
	private String selected;
	
	public void render(AppCheckList a) {
		this.checkId=a.getCheckId().toString();
		this.title=a.getTitle();
		this.note=a.getNote();
		this.choose=a.getChoose();
	}
	public AppCheckList model() {
		AppCheckList n = new AppCheckList(); 
		n.setNote(this.note);
		n.setTitle(this.title);
		n.setChoose(this.choose);	 
		return n;
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
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getChoose() {
		return choose;
	}
	public void setChoose(String choose) {
		this.choose = choose;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	
}
