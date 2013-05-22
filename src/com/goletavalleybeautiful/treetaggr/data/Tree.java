package com.goletavalleybeautiful.treetaggr.data;

import java.util.Collection;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@JsonIgnoreProperties(ignoreUnknown = true)
@DatabaseTable(tableName = "trees")
public class Tree {
	@DatabaseField(generatedId = true)
	private int id;
	
	@DatabaseField
	private int tree_type_id;
	@DatabaseField
	private int agency_id;
	
	@DatabaseField
	private int status;
	@DatabaseField
	private float diameter_at_height;
	@DatabaseField
	private float height;
	@DatabaseField
	private float spread;
	
	@DatabaseField
	private float longitude;
	@DatabaseField
	private float latitude;
	
	private boolean sendstatus = false;
	
    // ============================================================================================
    // Setters & Getters
    // ============================================================================================  	
	
	// sent
	public boolean wasSent() {
		return sendstatus;
	}
	
	public void sent() {
		this.sendstatus = true;
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
	
	public Collection< Tree > getUnsentTrees(){
		return this.unsent;
	}
	
}
