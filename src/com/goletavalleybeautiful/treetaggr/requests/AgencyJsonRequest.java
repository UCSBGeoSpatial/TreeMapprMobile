package com.goletavalleybeautiful.treetaggr.requests;

import com.goletavalleybeautiful.treetaggr.data.ListAgencies;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class AgencyJsonRequest extends SpringAndroidSpiceRequest< ListAgencies > {

    public AgencyJsonRequest() {
        super( ListAgencies.class );
    }

    @Override
    public ListAgencies loadDataFromNetwork() throws Exception {
        return getRestTemplate().getForObject( "http://ec2-184-169-238-244.us-west-1.compute.amazonaws.com:3000/agencies.json", ListAgencies.class );
    //    return getRestTemplate().getForObject( "http://192.168.1.5:3000/agencies.json", ListAgencies.class );
    }
    
    
}