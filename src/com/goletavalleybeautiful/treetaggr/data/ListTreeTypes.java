package com.goletavalleybeautiful.treetaggr.data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListTreeTypes {

    private List< TreeType > treetypes;
    
	public ArrayList<String> getTreeTypeNames() {
		ArrayList<String> TreeTypenames = new ArrayList<String>();
		for (int i = 0; i<treetypes.size(); i++){
			TreeTypenames.add(treetypes.get(i).getCommon_name() + " - " + treetypes.get(i).getGenus() + " " + treetypes.get(i).getSpecies());
		}
		
		
		return TreeTypenames;
	}

    public List< TreeType > getTreetypes() {
        return treetypes;
    }

    public void setResults( List< TreeType > treetypes ) {
        this.treetypes = treetypes;
    }
}