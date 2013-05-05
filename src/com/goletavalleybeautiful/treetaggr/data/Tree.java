package com.goletavalleybeautiful.treetaggr.data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Tree {
	private String common_name;
	private String genus;
	private String species;
	private int agency_id;
	
	private int status;
	private float diameter_at_height;
	private float height;
	private float spread;
	
	private float longitude;
	private float latitude;
	
	//get/set common name
	public String getCommon_name() {
		return this.common_name;
	}
	
	public void setCommon_name(String common_name) {
		this.common_name = common_name;
	}	
	
	
	//get/set genus
	public String getGenus() {
		return this.genus;
	}
	
	public void setGenus(String genus) {
		this.genus = genus;
	}
	
	//get/set species
	public String getSpecies() {
		return this.species;
	}
	
	public void setSpecies(String species) {
		this.species = species;
	}
	
	//get/set agency
	public int getAgency_id(){
		return this.agency_id;
	}
	
	public void setAgency_id(int agency_id){
		this.agency_id = agency_id;
	}
	
	//get/set DBH
	public Float getDiameter_at_height() {
		return this.diameter_at_height;
	}
	
	public void setDiameter_at_height(Float diameter_at_height) {
		this.diameter_at_height = diameter_at_height;
	}
	
	//get/set height
	public Float getHeight() {
		return this.height;
	}
	
	public void setHeight(Float height) {
		this.height = height;
	}
	
	
	//get/set spread
	public Float getSpread() {
		return this.spread;
	}
	
	public void setSpread(Float spread) {
		this.spread = spread;
	}
	
	//get/set status
	public int getStatus() {
		return this.status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	//get/set longitude
	public Float getLongitude() {
		return this.longitude;
	}
	
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	
	
	//get/set latitude
	public Float getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	
}