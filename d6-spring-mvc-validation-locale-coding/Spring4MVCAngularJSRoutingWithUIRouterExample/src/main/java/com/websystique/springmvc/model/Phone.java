package com.websystique.springmvc.model;

public class Phone{
	
	private long id;
	
	private String brand;
	
	private String model;
	
	private State state;
	
	private double price;

	private double size;
	
	private boolean sdCard;
	
	private boolean replacableBattery;
	
	private int camera;
	
	public Phone(long id,String brand, String model, State state, double price, 
			double size, boolean sdCard, boolean replacableBattery,int camera){
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.state = state;
		this.price = price;
		this.size = size;
		this.sdCard =sdCard;
		this.replacableBattery = replacableBattery;
		this.camera = camera;
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

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public boolean isSdCard() {
		return sdCard;
	}

	public void setSdCard(boolean sdCard) {
		this.sdCard = sdCard;
	}

	public boolean isReplacableBattery() {
		return replacableBattery;
	}

	public void setReplacableBattery(boolean replacableBattery) {
		this.replacableBattery = replacableBattery;
	}

	public int getCamera() {
		return camera;
	}

	public void setCamera(int camera) {
		this.camera = camera;
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
		Phone other = (Phone) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", brand=" + brand + ", model=" + model + ", state=" + state + ", price=" + price
				+ ", size=" + size + ", sdCard=" + sdCard + ", replacableBattery=" + replacableBattery + ", camera="
				+ camera + "]";
	}

	
	
}
