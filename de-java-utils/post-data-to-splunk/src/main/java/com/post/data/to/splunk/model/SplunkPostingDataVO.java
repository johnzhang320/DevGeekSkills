package com.post.data.to.splunk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "event",
    "host",
    "source",
    "sourcetype" 
})
public class SplunkPostingDataVO {
	  @JsonProperty("event")
	  SplunkEventVO event;
	  @JsonProperty("host")
	  String host;
	  @JsonProperty("source")
	  String source; //: CURRENT_PLAYBOOK_FILE,
	  @JsonProperty("sourcetype")
	  String sourcetype= "ansible";
	  
	public SplunkPostingDataVO() {
		super();
	}

	 
	public SplunkEventVO getEvent() {
		return event;
	}


	public void setEvent(SplunkEventVO event) {
		this.event = event;
	}


	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourcetype() {
		return sourcetype;
	}
	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
	}
       
}
