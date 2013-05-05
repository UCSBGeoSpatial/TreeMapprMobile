package com.goletavalleybeautiful.treetaggr.data;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class AgencyJsonRequest extends SpringAndroidSpiceRequest< ListAgencies > {

    public AgencyJsonRequest() {
        super( ListAgencies.class );
    }

    @Override
    public ListAgencies loadDataFromNetwork() throws Exception {
        return getRestTemplate().getForObject( "http://192.168.1.5:3000/agencies.json", ListAgencies.class );
    }
    
    
}