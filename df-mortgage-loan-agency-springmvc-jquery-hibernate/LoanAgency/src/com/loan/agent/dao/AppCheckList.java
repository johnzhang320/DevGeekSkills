package com.loan.agent.dao;

/**
 * AppCheckList entity. @author MyEclipse Persistence Tools
 */

public class AppCheckList implements java.io.Serializable {

	// Fields

	private Integer checkId;
	private String title;
	private String note;
	private String choose;

	// Constructors

	/** default constructor */
	public AppCheckList() {
	}

	/** minimal constructor */
	public AppCheckList(String title) {
		this.title = title;
	}

	/** full constructor */
	public AppCheckList(String title, String note, String choose) {
		this.title = title;
		this.note = note;
		this.choose = choose;
	}

	// Property accessors

	public Integer getCheckId() {
		return this.checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getChoose() {
		return this.choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}

}