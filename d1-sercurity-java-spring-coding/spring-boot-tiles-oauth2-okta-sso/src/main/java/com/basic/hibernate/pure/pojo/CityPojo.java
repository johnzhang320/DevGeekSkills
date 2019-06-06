package com.basic.hibernate.pure.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * City entity. @author MyEclipse Persistence Tools
 */
 
public class CityPojo {
	// Fields

	private Integer cityId; 
	private String cityName;
	private String state;
	private String countryName;
	private String location;
	
	public CityPojo(Integer cityId, String cityName, String state) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.state = state;
	}
	
	public CityPojo(Integer cityId, String cityName, String state, String countryName, String location) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.state = state;
		this.countryName = countryName;
		this.location = location;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	 
	 

 

}