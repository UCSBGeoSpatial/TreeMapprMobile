package com.goletavalleybeautiful.treetaggr.data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Agency {
	private String shortname;
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName( String name ) {
		this.name = name;
	}
	
	public String getShortname() {
		return shortname;
	}
	
	public void setShortname( String shortname ) {
		this.shortname = shortname;
	}
	
}
