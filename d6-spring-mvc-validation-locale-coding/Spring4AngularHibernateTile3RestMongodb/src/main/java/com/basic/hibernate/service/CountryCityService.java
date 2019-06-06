package com.basic.hibernate.service;

import java.util.List;

import org.json.JSONObject;

import com.basic.hibernate.dao.City;
import com.basic.hibernate.dao.CityDAO;
import com.basic.hibernate.dao.Country;
import com.basic.hibernate.dao.CountryDAO;
import com.basic.hibernate.pure.pojo.CityPojo;
import com.basic.hibernate.pure.pojo.CountryPojo;

public interface CountryCityService {
	public CountryDAO getCountryDAO();
	public CityDAO getCityDAO();
	public CityPojo getCityPojo(City c);
	public CountryPojo getCountryPojo(Country c);
	public List<CityPojo> getCityPojoListByCountry(Country c);
	public List<CityPojo> getCityPojoListByCityList(List<City> clist);
	public JSONObject getCountryJSON(Country c);
	public CountryPojo getCountryPojoLazy(Country c);
}
