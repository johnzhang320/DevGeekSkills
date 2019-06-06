package com.websystique.springmvc.model;

public class Printer{
	
	private long id;
	
	private String brand;
	
	private String model;
	
	private State state;
	
	private double price;

	private String paperFormat;
	
	private String technologie;
	
	private int pagesPerMinute;
	
	public Printer(long id,String brand, String model, State state, double price, 
			String paperFormat, String technologie, int pagesPerMinute){
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.state = state;
		this.price = price;
		this.paperFormat = paperFormat;
		this.technologie =technologie;
		this.pagesPerMinute = pagesPerMinute;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPaperFormat() {
		return paperFormat;
	}

	public void setPaperFormat(String paperFormat) {
		this.paperFormat = paperFormat;
	}

	public String getTechnologie() {
		return technologie;
	}

	public void setTechnologie(String technologie) {
		this.technologie = technologie;
	}

	public int getPagesPerMinute() {
		return pagesPerMinute;
	}

	public void setPagesPerMinute(int pagesPerMinute) {
		this.pagesPerMinute = pagesPerMinute;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Printer other = (Printer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Printer [id=" + id + ", brand=" + brand + ", model=" + model + ", state=" + state + ", price=" + price
				+ ", paperFormat=" + paperFormat + ", technologie=" + technologie + ", pagesPerMinute=" + pagesPerMinute
				+ "]";
	}
	
}
