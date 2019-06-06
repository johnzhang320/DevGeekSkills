package com.interview.questions.sample.shorten.URL;

public class URLRecord implements Comparable<URLRecord> {
	private String URL;
	private String shortenURL;
	private Long seqID;
	public URLRecord(String uRL, String shortenURL, Long seqID) {
		super();
		URL = uRL;
		this.shortenURL = shortenURL;
		this.seqID = seqID;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getShortenURL() {
		return shortenURL;
	}
	public void setShortenURL(String shortenURL) {
		this.shortenURL = shortenURL;
	}
	public Long getSeqID() {
		return seqID;
	}
	public void setSeqID(Long seqID) {
		this.seqID = seqID;
	}
	public int compareTo(URLRecord o2) {
		URLRecord o1= this;
		return o1.getSeqID().intValue() - o2.getSeqID().intValue();
	}
}
