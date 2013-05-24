package com.goletavalleybeautiful.treetaggr.data;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Tree {
	private int id;
	
	
	private int tree_type_id;
	
	private int agency_id;
	
	
	private int status;
	
	private float diameter_at_height;
	
	private float height;
	
	private float spread;
	
	
	private float longitude;
	
	private float latitude;
	
	
    // ============================================================================================
    // Setters & Getters
    // ============================================================================================  	
	
	// id
	public int showId() {
		return this.id;
	}
	
	public void setId( int i ){
		this.id = i;
	}
	
	// common name
	public int getTree_type_id(){
		return tree_type_id;
	}
	
	public void setTree_type_id( int ttype ){
		this.tree_type_id = ttype;
	}
	
	// agency
	public int getAgency_id(){
		return this.agency_id;
	}
	
	public void setAgency_id(int agency_id){
		this.agency_id = agency_id;
	}
	
	// DBH
	public Float getDiameter_at_height() {
		return this.diameter_at_height;
	}
	
	public void setDiameter_at_height(Float diameter_at_height) {
		this.diameter_at_height = diameter_at_height;
	}
	
	// height
	public Float getHeight() {
		return this.height;
	}
	
	public void setHeight(Float height) {
		this.height = height;
	}
	
	
	// spread
	public Float getSpread() {
		return this.spread;
	}
	
	public void setSpread(Float spread) {
		this.spread = spread;
	}
	
	// status
	public int getStatus() {
		return this.status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	// longitude
	public Float getLongitude() {
		return this.longitude;
	}
	
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	
	
	// latitude
	public Float getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	
    // ============================================================================================
    // Database Stuff
    // ============================================================================================   
	

	
}
