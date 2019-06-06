package com.basic.hibernate.dao;

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
@Entity
@Table(name = "city", catalog = "rua_receiverdb")
public class City implements java.io.Serializable {
	private static final long serialVersionUID=01L;
	// Fields

	private Integer cityId;
	private Country country;
	private String cityName;
	private String state;

	// Constructors

	/** default constructor */
	public City() {
	}

	/** full constructor */
	public City(Country country, String cityName, String state) {
		this.country = country;
		this.cityName = cityName;
		this.state = state;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "cityId", unique = true, nullable = false)
	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTRY_ID")
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name = "city_name")
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "state")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}