package com.example.usegpslocation;

import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {

	String longitude = "0.00", latitude = "0.00";

	LocationManager locationManager;
	LocationListener locationListener;

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final TextView message = (TextView)this.findViewById(R.id.locationLongLat);

		locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		locationListener = new LocationListener() {

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				if (location != null){
					longitude = location.getLongitude()+"";
					latitude = location.getLatitude()+"";

					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							message.setText(constructMessage(longitude, latitude));
						}
					});
				}
			}
		};
		
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 4000, 0, locationListener);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, locationListener);

	}

	private String constructMessage(String longitude, String latitude){
		return "Location Acquired!! : Longitude -> "+longitude+ "\n " + " Latitude -> "+ latitude;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		locationManager.removeUpdates(locationListener);
	}

}
