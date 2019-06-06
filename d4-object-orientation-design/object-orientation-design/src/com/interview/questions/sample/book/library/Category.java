package com.interview.questions.sample.book.library;

public enum Category {
	LITERATURE("Literature",0),SCIENCE("Science",1),HISTORY("History",3),SPORTS("Sport",4),MAGZINE("Magzine",5);
	private String catName;
	private int catId;
	private Category(String catName, int catId) {
		this.catName = catName;
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	 
	
}
