package com.post.data.to.splunk.model;

public class TaskFieldsVO {
	 private String action;	
	 private String always_run;	
	 private boolean any_errors_fatal;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getAlways_run() {
		return always_run;
	}
	public void setAlways_run(String always_run) {
		this.always_run = always_run;
	}
	public boolean isAny_errors_fatal() {
		return any_errors_fatal;
	}
	public void setAny_errors_fatal(boolean any_errors_fatal) {
		this.any_errors_fatal = any_errors_fatal;
	}
	 
}
