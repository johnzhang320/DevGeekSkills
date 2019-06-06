package com.websystique.springmvc.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * Above object will be returned from controllers and converted by Jackson into JSON format. 
 * If the jackson-dataformat-xml is present, then it can also be converted into XML.
 *
 */
//@XmlRootElement(name = "player")  // if this command is available , even no jackson-dataformat-xml, XML display
public class Message {

	String name;
	String text;

	public Message(){
		
	}
	
	public Message(String name, String text) {
		this.name = name;
		this.text = text;
	}

	@XmlElement
	public String getName() {
		return name;
	}
	
	@XmlElement
	public String getText() {
		return text;
	}

}
