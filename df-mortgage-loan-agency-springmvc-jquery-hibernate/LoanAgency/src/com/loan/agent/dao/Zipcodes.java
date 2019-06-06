package com.loan.agent.dao;

/**
 * Zipcodes entity. @author MyEclipse Persistence Tools
 */

public class Zipcodes implements java.io.Serializable {

	// Fields

	private Integer id;
	private String zip;
	private String latitude;
	private String longitude;
	private String city;
	private String state;
	private String county;
	private String type;
	private String stateName;

	// Constructors

	/** default constructor */
	public Zipcodes() {
	}

	/** full constructor */
	public Zipcodes(String zip, String latitude, String longitude, String city,
			String state, String county, String type, String stateName) {
		this.zip = zip;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.state = state;
		this.county = county;
		this.type = type;
		this.stateName = stateName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}