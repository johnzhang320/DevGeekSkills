package com.basic.hibernate.pure.pojo;

public class ZipcodePojo {
	 int id;
	 private String city;
	 private String county;
	 private String latitude;
	 private String longitude;
	 private String state;
	 private String type;
	 private String zip;
	 public ZipcodePojo() {}
	 public ZipcodePojo(int id, String city, String county, String latitude, String longitude, String state,
			String type, String zip) {
		super();
		this.id = id;
		this.city = city;
		this.county = county;
		this.latitude = latitude;
		this.longitude = longitude;
		this.state = state;
		this.type = type;
		this.zip = zip;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	@Override
	public String toString() {
		return "ZipcodePojo [id=" + id + ", city=" + city + ", county=" + county + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", state=" + state + ", type=" + type + ", zip=" + zip + "]";
	}
	 

}
