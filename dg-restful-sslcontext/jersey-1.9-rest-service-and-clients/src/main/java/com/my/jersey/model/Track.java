package com.my.jersey.model;

public class Track {
	private String title;
	private String singer;
	private String style;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public Track(String title, String singer, String style) {
		super();
		this.title = title;
		this.singer = singer;
		this.style = style;
	}
	public Track() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Track [title=" + title + ", singer=" + singer + ", style=" + style + "]";
	}
	
	
}
