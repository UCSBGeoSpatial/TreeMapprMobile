package com.goletavalleybeautiful.treetaggr;

import com.goletavalleybeautiful.treetaggr.locate.TreeLocation;
import com.goletavalleybeautiful.treetaggr.locate.TreeLocation.LocationResult;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TreeTagMain extends Activity {
	private TextView latitudeField;
	private TextView longitudeField;

	private Button gps_button;
	private TreeLocation myLocation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tree_tag_main);

		// Point to the UI elements
	    latitudeField = (TextView) findViewById(R.id.TextView02);
	    longitudeField = (TextView) findViewById(R.id.TextView04);
	    gps_button = (Button) findViewById(R.id.button1);
	    
	    
	    gps_button.setOnClickListener(new View.OnClickListener(){
	    	public void onClick(View v) {
	    		locationClick();
	    		}
	    	});
	    
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

        // Check if the GPS setting is currently enabled on the device.
        // This verification should be done during onStart() because the system calls this method
        // when the user returns to the activity, which ensures the desired location provider is
        // enabled each time the activity resumes from the stopped state.
		myLocation = new TreeLocation();

    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	myLocation.cancelTimer();
    }

	private void locationClick() {

		Boolean status = myLocation.getLocation(this, locationResult);
		Toast.makeText(getBaseContext(), "Querying Location", Toast.LENGTH_LONG)
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
