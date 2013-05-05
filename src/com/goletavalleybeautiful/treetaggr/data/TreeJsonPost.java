package com.goletavalleybeautiful.treetaggr.data;

import org.springframework.web.client.RestTemplate;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class TreeJsonPost extends SpringAndroidSpiceRequest< Tree > {
	
	private Tree tree;
	private RestTemplate restTemplate;
	
    public TreeJsonPost() {
        super( Tree.class );
    }
    
    public void setTree( Tree tree) {
    	this.tree = tree;
    }

    @Override
    public Tree loadDataFromNetwork() throws Exception {
		
		restTemplate = getRestTemplate();
		
		Tree response = restTemplate.postForObject("http://192.168.1.5:3000/trees", tree, Tree.class);
		return response;
		}
    
}