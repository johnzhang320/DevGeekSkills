package com.post.data.to.splunk.model;

public class ResultVO {
	 private boolean _ansible_no_log;
	 private boolean changed;	
	 private String skip_reason;
	public boolean is_ansible_no_log() {
		return _ansible_no_log;
	}
	public void set_ansible_no_log(boolean _ansible_no_log) {
		this._ansible_no_log = _ansible_no_log;
	}
	public boolean isChanged() {
		return changed;
	}
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	public String getSkip_reason() {
		return skip_reason;
	}
	public void setSkip_reason(String skip_reason) {
		this.skip_reason = skip_reason;
	}	
	 
	 
}
