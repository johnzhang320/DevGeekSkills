package com.basic.hibernate.pure.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 * Country entity. @author MyEclipse Persistence Tools
 */
 
public class CountryPojo {
	// Fields

	private Integer countryId;
	private String countryName;
	private String location;
	private List<CityPojo> cities = new ArrayList<CityPojo>();
	public CountryPojo(Integer countryId, String countryName, String location) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.location = location;
	}
	
	public CountryPojo(Integer countryId, String countryName, String location, List<CityPojo> cities) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.location = location;
		this.cities = cities;
	}

	
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
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

	public List<CityPojo> getCities() {
		return cities;
	}

	public void setCities(List<CityPojo> cities) {
		this.cities = cities;
	}
}