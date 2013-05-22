package com.goletavalleybeautiful.treetaggr;


import java.util.ArrayList;
import java.util.List;

import com.goletavalleybeautiful.treetaggr.locate.TreeLocation;
import com.goletavalleybeautiful.treetaggr.locate.TreeLocation.LocationResult;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.goletavalleybeautiful.treetaggr.data.Agency;
import com.goletavalleybeautiful.treetaggr.data.AgencyJsonRequest;
import com.goletavalleybeautiful.treetaggr.data.JsonSpiceService;
import com.goletavalleybeautiful.treetaggr.data.ListAgencies;
import com.goletavalleybeautiful.treetaggr.data.ListTreeTypes;
import com.goletavalleybeautiful.treetaggr.data.Tree;
import com.goletavalleybeautiful.treetaggr.data.TreeJsonPost;
import com.goletavalleybeautiful.treetaggr.data.TreeType;
import com.goletavalleybeautiful.treetaggr.data.TreeTypeJsonRequest;

import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class TreeTagMain extends Activity {
	private static final String JSON_CACHE_KEY = "json";
	private SpiceManager spiceManager = new SpiceManager( JsonSpiceService.class );
	
	private ArrayAdapter< Agency > agenciesAdapter;
	private ArrayAdapter< TreeType > treeTypesAdapter;
	
	private TextView latitudeField;
	private TextView longitudeField;
	private TextView accuracyField;
	private EditText heightField;
	private EditText spreadField;
	private EditText dbhField;
	private Spinner agencyField;
	private Spinner treeTypeField;

	private Button gps_button;
	private Button submit_button;
	private TreeLocation myLocation;
	
	private Tree tree;
	
	
    // ============================================================================================
    // ACTIVITY LIFE CYCLE
    // ============================================================================================

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tree_tag_main);
	    initUIComponents();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tree_tag_main, menu);
		return true;
	}
	
    @Override
    protected void onStart() {
        super.onStart();
		spiceManager.start( this );

        // Check if the GPS setting is currently enabled on the device.
        // This verification should be done during onStart() because the system calls this method
        // when the user returns to the activity, which ensures the desired location provider is
        // enabled each time the activity resumes from the stopped state.
		myLocation = new TreeLocation();
		locationClick();
		getAgencies();
		getTreeTypes();
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	myLocation.cancelTimer();
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	myLocation.cancelTimer();
    	spiceManager.shouldStop();
    }

    // ============================================================================================
    // INNER CLASSES
    // ============================================================================================   
    
	private void initUIComponents() {
		
		//Assign variables to UI elements
		heightField = (EditText) findViewById(R.id.editHeight1);
		spreadField = (EditText) findViewById(R.id.editSpread1);
		dbhField = (EditText) findViewById(R.id.editDBH1);
		
	    latitudeField = (TextView) findViewById(R.id.TextView02);
	    longitudeField = (TextView) findViewById(R.id.TextView04);
	    accuracyField = (TextView) findViewById(R.id.TextView06);
	    
	    gps_button = (Button) findViewById(R.id.button1);
	    submit_button = (Button) findViewById(R.id.button2);
	    
	    agencyField = (Spinner) findViewById(R.id.agencyList01);
	    treeTypeField = (Spinner) findViewById(R.id.treeTypeList01);
	    
	    //Setup button listeners
	    gps_button.setOnClickListener(new View.OnClickListener(){
	    	public void onClick(View v) {
	    		locationClick();
	    		}
	    	});
	    
	    submit_button.setOnClickListener(new View.OnClickListener(){
	    	public void onClick(View v) {
	    		tree = new Tree();
	    		TreeType ttype = (TreeType)treeTypeField.getSelectedItem();
	    		Agency agen = (Agency)agencyField.getSelectedItem();
	    		
	    		//Build tree to send
	    		tree.setTree_type_id(ttype.getId());
	    		tree.setAgency_id(agen.getId());
	    		
	    		tree.setDiameter_at_height(Float.parseFloat(dbhField.getText().toString()));
	    		tree.setHeight(Float.parseFloat(heightField.getText().toString()));
	    		tree.setSpread(Float.parseFloat(spreadField.getText().toString()));
	    		tree.setLatitude(Float.parseFloat(latitudeField.getText().toString()));
	    		tree.setLongitude(Float.parseFloat(longitudeField.getText().toString()));
	    		
	    		//Toast to submit
	    		Toast.makeText(getBaseContext(),
	    				"Name: " + ttype.toString() +
	    				"\nLat, Lon: " + latitudeField.getText() + " , " + longitudeField.getText() +
	    				"\n Submitted",
	    				Toast.LENGTH_LONG).show();
	    		postTree( tree );
	    		}	    		
    	});
	}

	// ============================================================================================   
    //Get Agencies
    private void getAgencies() {
    	TreeTagMain.this.setProgressBarIndeterminateVisibility( true );
    	spiceManager.execute( new AgencyJsonRequest(), JSON_CACHE_KEY, DurationInMillis.NEVER, new AgencyRequestListener() );
    }
    //Listener waits for service to return data
    private class AgencyRequestListener implements RequestListener< ListAgencies > {

		@Override
		public void onRequestFailure(SpiceException arg0) {
			Toast.makeText(getBaseContext(), "Query failed: " + arg0, Toast.LENGTH_LONG)
	        .show();
			
		}

		@Override
		public void onRequestSuccess(ListAgencies listAgencies) {
			List<Agency> agencies = new ArrayList<Agency>();
			agencies = listAgencies.getAgencies();
			agenciesAdapter = new ArrayAdapter<Agency>(TreeTagMain.this, android.R.layout.simple_spinner_item, agencies);
			agenciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			agencyField.setAdapter(agenciesAdapter);
		}
    	
    }
	
    // ============================================================================================   
    //Get TreeTypes
    private void getTreeTypes() {
    	TreeTagMain.this.setProgressBarIndeterminateVisibility( true );
    	spiceManager.execute( new TreeTypeJsonRequest(), JSON_CACHE_KEY, DurationInMillis.NEVER, new TreeTypeRequestListener() );
    }
    
    private class TreeTypeRequestListener implements RequestListener< ListTreeTypes > {

		@Override
		public void onRequestFailure(SpiceException arg0) {
			Toast.makeText(getBaseContext(), "Query failed :(", Toast.LENGTH_LONG)
	        .show();
			
		}

		@Override
		public void onRequestSuccess(ListTreeTypes listTrees) {
			List<TreeType> treetypes = new ArrayList<TreeType>();
			treetypes = listTrees.getTreetypes();
			treeTypesAdapter = new ArrayAdapter<TreeType>(TreeTagMain.this, android.R.layout.simple_spinner_item, treetypes);
			treeTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			treeTypeField.setAdapter(treeTypesAdapter);
		}
    	
    }
    
	// ============================================================================================   
    //Post Tree
    private void postTree( Tree tree ) {
    	TreeTagMain.this.setProgressBarIndeterminateVisibility( true );
    	TreeJsonPost request = new TreeJsonPost();
    	request.setTree(tree);
    	spiceManager.execute( request, "agencies_json", DurationInMillis.NEVER, new TreeRequestListener() );
    }
    
    private class TreeRequestListener implements RequestListener< Tree > {

		@Override
		public void onRequestFailure(SpiceException arg0) {
			Toast.makeText(getBaseContext(), "Query failed :(", Toast.LENGTH_LONG)
	        .show();
			
		}

		@Override
		public void onRequestSuccess(Tree tree ) {

		}
    	
    }
    
	// ============================================================================================   
    //Read Location
	private void locationClick() {

		myLocation.getLocation(this, locationResult);
		Toast.makeText(getBaseContext(), "Querying Location", Toast.LENGTH_SHORT)
        .show();
	}
	
	public LocationResult locationResult = new LocationResult() {
        public void gotLocation(final Location location) {
            try {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            if (lat != 0.0 && lng != 0.0) {
                String sLat;
                String sLng;
                String sAcc;
                
                if (location.hasAccuracy()) { 
                	sAcc = Float.toString(location.getAccuracy()); 
                	accuracyField.setText(sAcc);
                	};
                	
                sLat = Double.toString(lat);
                sLng = Double.toString(lng);
                
                latitudeField.setText(sLat);
                longitudeField.setText(sLng);
            } 
            }catch (Exception e) {

            }
        }
        
    };
	
}
