package com.boot.security.auth.model;

public class PairVO {
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	
	public PairVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PairVO(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "PairVO [key=" + key + ", value=" + value + "]";
	}
	
}
