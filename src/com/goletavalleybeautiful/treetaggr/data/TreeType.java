package com.goletavalleybeautiful.treetaggr.data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import com.j256.ormlite.field.DatabaseField;


@JsonIgnoreProperties(ignoreUnknown = true)
public class TreeType {
	
	//Class Variables
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(index = true)
	private String common_name;
	@DatabaseField
	private String species;
	@DatabaseField
	private String genus;
	
	TreeType() {
		
	}
	
	public String toString() {
		return common_name + " - " + genus + " " + species;
	}
	
	//Setters & Getters
	public String getCommon_name() {
		return common_name;
	}
	public void setCommon_name(String common_name) {
		this.common_name = common_name;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getGenus() {
		return genus;
	}
	public void setGenus(String genus) {
		this.genus = genus;
	}
	

	
}
