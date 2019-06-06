package com.jersey.restful.apps;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
 

import com.basic.hibernate.dao.City;
import com.basic.hibernate.dao.CityDAO;
import com.basic.hibernate.dao.Country;
import com.basic.hibernate.dao.CountryDAO;

 

 
public class CountryCityServer {
	static Logger Log = Logger.getLogger(CountryCityServer.class);
	@GET
	@Path("/citybyId/{id}") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCityById (@PathParam("id") int id ) {
		CityDAO cDao = new CityDAO();
		City city = cDao.findById(id);
		if (null==city) {
			Log.error("no country found, wrong city id "+id);
			return Response.status(406).entity("wrong city id:"+id).build();
		} else {
			return Response.status(200).entity(city).build();
		}
 	}
	@GET
	@Path("/citiesbycountryId/{countryId}") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCitiesByCountryId (@PathParam("countryId") int countryId ) {
		CountryDAO cDao = new CountryDAO();
		Country country = cDao.findById(countryId);
		if (null==country) {
			Log.error("no country found, wrong country id "+countryId);
			return Response.status(406).entity("wrong country id:"+countryId).build();
		} else {
			return Response.status(200).entity(country.getCities()).build();
		}
 	}
	
	@GET
	@Path("/citiesbystate/{state}") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCityByState (@PathParam("state") String state ) {
		CityDAO cDao = new CityDAO();
		List<City> city = cDao.findByState(state);
		if (null==city || city.size()==0) {
			Log.error("no country found, wrong state:"+state);
			return Response.status(406).entity("wrong state name"+state).build();
		} else {
			return Response.status(200).entity(city).build();
		}
 	}
	
	@GET
	@Path("/country/{id}") 
	@Produces (MediaType.APPLICATION_JSON)
	public Response findCountryById (@PathParam("id") int id ) {
		CountryDAO cDao = new CountryDAO();
		Country country = cDao.findById(id);
		if (null==country) {
			Log.error("no country found, wrong country id "+id);
			return Response.status(406).entity("wrong country id:"+id).build();
		} else {
			return Response.status(200).entity(country.getCities()).build();
		}
 	}
	@GET
	@Path("/countriesall") 
	@Produces (MediaType.APPLICATION_JSON)
	public Response findAllCountriesAndCities () {
		CountryDAO cDao = new CountryDAO();
		List<Country> countries = cDao.findAll();
		if (null==countries || countries.size()==0) {
			Log.error("no country found");
			return Response.status(406).entity("No country found").build();
		} else {
			// Lazy load initially,therefore load explicitly
			for (Country ctr: countries ) {
				ctr.setCities(ctr.getCities());
			}
			
			return Response.status(200).entity(countries).build();
		}
 	}
}
