package com.codenvy.testtask.qname;

public class QName 
{
	private String prefix;
	private String localName;
	private String fullName;
	
	public QName(String prefix, String localName, String fullName) {
		this.prefix = 		prefix;
		this.localName = 	localName;
		this.fullName = 	fullName;
	}
	
	public QName(String localName) {
		this.prefix = 		"";
		this.localName = 	localName;
		this.fullName = 	localName;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getLocalName() {
		return localName;
	}

	public String getAsString() {
		return fullName;
	}
}
