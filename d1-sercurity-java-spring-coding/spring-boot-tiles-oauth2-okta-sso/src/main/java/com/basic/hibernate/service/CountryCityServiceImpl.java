package com.basic.hibernate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import com.basic.hibernate.dao.City;
import com.basic.hibernate.dao.CityDAO;
import com.basic.hibernate.dao.Country;
import com.basic.hibernate.dao.CountryDAO;
import com.basic.hibernate.pure.pojo.CityPojo;
import com.basic.hibernate.pure.pojo.CountryPojo;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CountryCityServiceImpl implements CountryCityService {
	 
	public CountryDAO getCountryDAO() {
		return new CountryDAO();
	}
	public CityDAO getCityDAO() {
		return new CityDAO();
	}
	public CityPojo getCityPojo(City c) {
		
		return new CityPojo(c.getCityId(),c.getCityName(),c.getState(),c.getCountry().getCountryName(),c.getCountry().getLocation());
	}
	 
	
	public CountryPojo getCountryPojo(Country c) {
		List<CityPojo> clist = this.getCityPojoListByCountry(c);
		return new CountryPojo(c.getCountryId(),c.getCountryName(),c.getLocation(),clist);
	}
	public CountryPojo getCountryPojoLazy(Country c) {		 
		return new CountryPojo(c.getCountryId(),c.getCountryName(),c.getLocation());
	} 
	public List<CityPojo> getCityPojoListByCountry(Country c) {		 
		return getCityPojoListByCityList(c.getCities());
	}
	
	public List<CityPojo> getCityPojoListByCityList(List<City> clist) {
		List<CityPojo> list = new ArrayList<CityPojo>();
		for (City v:clist) {
			list.add(getCityPojo(v));
		}
		return list;
	}
	public JSONObject getCountryJSON(Country c) {
		CountryPojo pojo = getCountryPojo(c);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonInString = mapper.writeValueAsString(pojo );
			JSONObject cjson = new JSONObject(jsonInString);
			
			
			JSONArray arr = new JSONArray();
			for (City t:c.getCities()) {
				ObjectMapper mapper2 = new ObjectMapper();
				JSONObject tjson = new JSONObject(mapper2.writeValueAsString(t));
				arr.put(tjson);
			}
			
			cjson.accumulate(pojo.getCountryName(), arr);
			return cjson;
		} catch (Exception e) {
			 
		}
		
		return null;
	}
}
