package com.loan.agent.mvc.controller;


import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
 
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

 
import com.basic.hibernate.dao.City;
import com.basic.hibernate.dao.CityDAO;
import com.basic.hibernate.dao.Country;
import com.basic.hibernate.dao.CountryDAO;
import com.basic.hibernate.pure.pojo.CityPojo;
import com.basic.hibernate.pure.pojo.CountryPojo;
import com.basic.hibernate.service.CountryCityService;
 
 
@RestController
@RequestMapping("/")
public class CountryCityRestController {
	static Logger Log = Logger.getLogger(CountryCityRestController.class);
	@Autowired
	CountryCityService countryCityService;
	
	/*------------------------Fetch city by city id ----------------*/
	@RequestMapping(value="/citybyid.html/{Id}", method=RequestMethod.GET)
	public ResponseEntity<CityPojo> findCityById (@PathVariable("Id") int Id ) {
		CityDAO cDao = countryCityService.getCityDAO();
		City city = cDao.findById(Id);
		CityPojo pojo =countryCityService.getCityPojo(city);
		if (null==city) {
			Log.error("no city found, wrong country id "+Id);
			return new ResponseEntity<CityPojo> (HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<CityPojo>(pojo,HttpStatus.OK);
		}
 	} 
	
	/*------------------------Fetch cities by country Id -----------------*/ 
	@RequestMapping(value="/allcities.html/", method=RequestMethod.GET)
	public ResponseEntity<List<CityPojo>> findAllCities () {
		CityDAO cDao = countryCityService.getCityDAO();
		List<City> clist = cDao.findAll();
		
		if (null==clist) {
			Log.error("no country found ");
			return new ResponseEntity<List<CityPojo>> (HttpStatus.NO_CONTENT);
		} else {
			List<CityPojo> list = countryCityService.getCityPojoListByCityList(clist);			
			return new ResponseEntity<List<CityPojo>> (list,HttpStatus.OK);
		}
 	}
	
	/*------------------------Fetch cities by country Id -----------------*/ 
	@RequestMapping(value="/citiesbycountryId.html/{countryId}", method=RequestMethod.GET)
	public ResponseEntity<List<CityPojo>> findCityByCountryId (@PathVariable("countryId") int countryId ) {
		CountryDAO cDao = countryCityService.getCountryDAO();
		Country country = cDao.findById(countryId);
		
		if (null==country) {
			Log.error("no country found, wrong country id "+countryId);
			return new ResponseEntity<List<CityPojo>> (HttpStatus.NO_CONTENT);
		} else {
			List<CityPojo> list = countryCityService.getCityPojoListByCountry(country);			
			return new ResponseEntity<List<CityPojo>> (list,HttpStatus.OK);
		}
 	}
	
	/*---------------------fetch cities by state name ------------------------*/ 
	@RequestMapping(value="/citiesbystate.html/{state}", method=RequestMethod.GET)
	public ResponseEntity<List<CityPojo>> findCityByState(@PathVariable("state") String state ) {
	 
		CityDAO cDao = countryCityService.getCityDAO(); 
		List<City> cities = cDao.findByState(state);
		if (null==cities || cities.size()==0) {
			Log.error("no country found, wrong state:"+state);
			return new ResponseEntity<List<CityPojo>> (HttpStatus.NO_CONTENT);
		} else {
			List<CityPojo> list = countryCityService.getCityPojoListByCityList(cities);
			return new ResponseEntity<List<CityPojo>> (list,HttpStatus.OK);
		}
 	}
	 
	/*-------------------------Fetch Country by Country Id---------------------------*/
	@RequestMapping(value="/country.html/{id}", method=RequestMethod.GET)
	public ResponseEntity<CountryPojo> findCountryById(@PathVariable("id") int id ) {
	 
		CountryDAO cDao = countryCityService.getCountryDAO();
		Country country = cDao.findById(id);
		 
		if (null==country) {
			Log.error("no country found, wrong country id "+id);
			return new ResponseEntity<CountryPojo>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<CountryPojo> (countryCityService.getCountryPojo(country),HttpStatus.OK);
		}
 	}
	/*-------------------------Fetch Country by Country Id Lazy(no child data)---------------------------*/
	@RequestMapping(value="/countrylazy.html/{id}", method=RequestMethod.GET)
	public ResponseEntity<CountryPojo> findCountryByIdLazy(@PathVariable("id") int id ) {
	 
		CountryDAO cDao = countryCityService.getCountryDAO();
		Country country = cDao.findById(id);
		 
		if (null==country) {
			Log.error("no country found, wrong country id "+id);
			return new ResponseEntity<CountryPojo>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<CountryPojo> (countryCityService.getCountryPojoLazy(country),HttpStatus.OK);
		}
 	}
	/*-----------------------Fetch all countries included child data---------------------*/ 
	@RequestMapping(value="/countries.html", method=RequestMethod.GET)
	public ResponseEntity<List<CountryPojo>> findAllCountriesAndCities() {
	 
		CountryDAO cDao = countryCityService.getCountryDAO();
		List<Country> countries = cDao.findAll();
		if (null==countries || countries.size()==0) {
			Log.error("no country found");
			return new ResponseEntity<List<CountryPojo>> (HttpStatus.NO_CONTENT);
		} else {
			List<CountryPojo> list = new ArrayList<CountryPojo> ();
			for (Country ctr: countries ) {
				list.add(countryCityService.getCountryPojo(ctr));
			}
			return new ResponseEntity<List<CountryPojo>> (list,HttpStatus.OK);
		}
 	}
	/*-----------------------Fetch all countries lazy (without child data)---------------------*/ 
	@RequestMapping(value="/countrieslazy.html", method=RequestMethod.GET)
	public ResponseEntity<List<CountryPojo>> findAllCountriesLazy() {
	 
		CountryDAO cDao = countryCityService.getCountryDAO();
		List<Country> countries = cDao.findAll();
		if (null==countries || countries.size()==0) {
			Log.error("no country found");
			return new ResponseEntity<List<CountryPojo>> (HttpStatus.NO_CONTENT);
		} else {
			List<CountryPojo> list = new ArrayList<CountryPojo> ();
			for (Country ctr: countries ) {
				list.add(countryCityService.getCountryPojoLazy(ctr));
			}
			return new ResponseEntity<List<CountryPojo>> (list,HttpStatus.OK);
		}
 	}
 /* -----------------------Fetch all countries and the cities as following -----------------
  [
  {
    "countryId": 1,
    "countryName": "United States",
    "location": "North America",
    "cities": [
      {
        "cityId": 1,
        "cityName": "San Jose",
        "state": "CA",
        "countryName": "United States",
        "location": "North America"
      },
      {
        "cityId": 2,
        "cityName": "Santa Clara",
        "state": "CA",
        "countryName": "United States",
        "location": "North America"
      },
      {
        "cityId": 3,
        "cityName": "Sunnyvale",
        "state": "CA",
        "countryName": "United States",
        "location": "North America"
      },
      {
        "cityId": 4,
        "cityName": "Cuppertino",
        "state": "CA",
        "countryName": "United States",
        "location": "North America"
      }
    ]
  },
  {
    "countryId": 2,
    "countryName": "China",
    "location": "Asia",
    "cities": [
      {
        "cityId": 5,
        "cityName": "Hung Zhou",
        "state": "East China",
        "countryName": "China",
        "location": "Asia"
      },
      {
        "cityId": 6,
        "cityName": "Ji Ning",
        "state": "Northeast China",
        "countryName": "China",
        "location": "Asia"
      },
      {
        "cityId": 7,
        "cityName": "Gui Zhou",
        "state": "SouthWest China",
        "countryName": "China",
        "location": "Asia"
      },
      {
        "cityId": 8,
        "cityName": "Chang Sha",
        "state": "Hunan",
        "countryName": "China",
        "location": "Asia"
      }
    ]
  },
  {
    "countryId": 3,
    "countryName": "United Kingdom",
    "location": "Eroupe",
    "cities": [
      {
        "cityId": 9,
        "cityName": "Bristol",
        "state": "UK",
        "countryName": "United Kingdom",
        "location": "Eroupe"
      },
      {
        "cityId": 10,
        "cityName": "York",
        "state": "UK",
        "countryName": "United Kingdom",
        "location": "Eroupe"
      },
      {
        "cityId": 11,
        "cityName": "Shefield",
        "state": "Ireland",
        "countryName": "United Kingdom",
        "location": "Eroupe"
      },
      {
        "cityId": 12,
        "cityName": "NottingHam",
        "state": "England",
        "countryName": "United Kingdom",
        "location": "Eroupe"
      }
    ]
  }
]
  */
}
