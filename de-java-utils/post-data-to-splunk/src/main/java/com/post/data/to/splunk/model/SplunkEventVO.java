package com.post.data.to.splunk.model;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SplunkEventVO {
	String ansible_host;
	boolean failed;
	String playbook_file;
 	ResultVO result;
	String task;
	TaskFieldsVO task_fields;
	
	public SplunkEventVO() {
		try {
            InetAddress addr = java.net.InetAddress.getLocalHost();    
            int len = addr.toString().indexOf("/");
            ansible_host = addr.toString().substring(0, len);
        } catch (UnknownHostException e) {
            System.out.println(e);
        }
	}

	public String getAnsible_host() {
		return ansible_host;
	}

	public void setAnsible_host(String ansible_host) {
		this.ansible_host = ansible_host;
	}

	 

	public boolean isFailed() {
		return failed;
	}

	public void setFailed(boolean failed) {
		this.failed = failed;
	}

	public String getPlaybook_file() {
		return playbook_file;
	}

	public void setPlaybook_file(String playbook_file) {
		this.playbook_file = playbook_file;
	}

	public ResultVO getResult() {
		return result;
	}

	public void setResult(ResultVO result) {
		this.result = result;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public TaskFieldsVO getTask_fields() {
		return task_fields;
	}

	public void setTask_fields(TaskFieldsVO task_fields) {
		this.task_fields = task_fields;
	}
	 
   
}
