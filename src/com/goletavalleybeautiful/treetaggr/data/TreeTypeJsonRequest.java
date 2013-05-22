package com.goletavalleybeautiful.treetaggr.data;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class TreeTypeJsonRequest extends SpringAndroidSpiceRequest< ListTreeTypes > {

    public TreeTypeJsonRequest() {
        super( ListTreeTypes.class );
    }

    @Override
    public ListTreeTypes loadDataFromNetwork() throws Exception {
        return getRestTemplate().getForObject( "http://ec2-184-169-238-244.us-west-1.compute.amazonaws.com:3000/tree_types.json", ListTreeTypes.class );
    }
    
    
}