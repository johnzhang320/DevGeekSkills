package com.spring.rest.oauth.ssl.server.model3;


public class Product {

    private String name;
    private String description;
    private float  price;
    private String partNumber;
   
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String name, String description, float price, String partNumber) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.partNumber = partNumber;
	 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + ", price=" + price + ", partNumber="
				+ partNumber + "]";
	}
 
    
}
