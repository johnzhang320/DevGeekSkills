package com.websystique.springmvc.model;

public class Computer{

	private long id;
	
	private String brand;
	
	private String model;
	
	private State state;
	
	private double price;

	private double processorSpeed;
	
	private int ram;
	
	private int diskCapacity;


	public Computer(long id,String brand, String model, State state, double price, 
			double processorSpeed, int ram, int diskCapacity){
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.state = state;
		this.price = price;
		this.processorSpeed = processorSpeed;
		this.ram =ram;
		this.diskCapacity = diskCapacity;
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

	
	public double getProcessorSpeed() {
		return processorSpeed;
	}

	public void setProcessorSpeed(double processorSpeed) {
		this.processorSpeed = processorSpeed;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public int getDiskCapacity() {
		return diskCapacity;
	}

	public void setDiskCapacity(int diskCapacity) {
		this.diskCapacity = diskCapacity;
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
		Computer other = (Computer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", brand=" + brand + ", model=" + model + ", state=" + state + ", price=" + price
				+ ", processorSpeed=" + processorSpeed + ", ram=" + ram + ", diskCapacity=" + diskCapacity + "]";
	}

	
	
}
