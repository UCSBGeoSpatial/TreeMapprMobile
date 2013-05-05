package com.goletavalleybeautiful.treetaggr.data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListAgencies {

    private List< Agency > agencies;
    
	public ArrayList<String> getAgencyNames() {
		ArrayList<String> agencynames = new ArrayList<String>();
		for (int i = 0; i<agencies.size(); i++){
			agencynames.add(agencies.get(i).getShortname() + " - " + agencies.get(i).getName());
		}
		
		
		return agencynames;
	}

    public List< Agency > getAgencies() {
        return agencies;
    }

    public void setResults( List< Agency > agencies ) {
        this.agencies = agencies;
    }
}