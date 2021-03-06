package com.goletavalleybeautiful.treetaggr.data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Agency {
	private int id;
	private String shortname;
	private String name;
	
	public String toString() {
		return shortname + " - " + name;
	}
	
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

	public int getId() {
		return id;
	}
	
	public void setId( int id ) {
		this.id = id;
	}
	
}
