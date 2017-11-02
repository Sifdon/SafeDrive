/** App startup activity. Utilises butterknife library to bind views and generate on click listeners in
 * order to avoid boiler plate code. Also contains helper methods such as the checkPermission() method which
 * verifies android build version running on a device in order to handle permissions accordingly.*/

package com.bitland.safedrive.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bitland.safedrive.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //constants for verifying permissions
    private static final int READ_PHONE_STATE_RESULT = 1;
    private static final int COARSE_LOCATION_RESULT = 2;
    public static final String LOCATION_NAME = "location_name";

    //List of permissions
    List<String> permissions = new ArrayList<>();

    //Variables to hold user's current location
    private String cityName;
    List<Address> mAddresses;
    //For retrieving device's last known location
    private FusedLocationProviderClient mFusedLocationProviderClient;
    //For reverse geo coding to get location description from the latitude and longitude
    private Geocoder mGeocoder;

    //Variables for getting current time and formatting it for display
    Calendar mCalendar;
    SimpleDateFormat mSimpleDateFormat;
    String currentTimeString;

    //Bind view with butterknife
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.timeTextView) TextView mTimeTextView;
    @BindView(R.id.driveModeButton) Button mDriveModeButton;
    @BindView(R.id.locationButton) Button mLocationButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Add permissions to permissions list
        permissions.add(Manifest.permission.READ_PHONE_STATE);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        //Retrieve location
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        mGeocoder = new Geocoder(this, Locale.getDefault());

        //Get current time
        mCalendar = Calendar.getInstance();
        mSimpleDateFormat = new SimpleDateFormat("HH:mm");
        currentTimeString = mSimpleDateFormat.format(mCalendar.getTime());

        checkPermission(1);

    }

    //Use butterknife to generate an on click listener for mDriveModeButton
    @OnClick(R.id.driveModeButton) void startDriveModeActivity(){
        checkPermission(0);
    }

    //Use butterknife to generate an on click listener for mLocationButton
    @OnClick(R.id.locationButton) void startRoadListActivity(){
        Intent intent = new Intent(this, RoadListActivity.class);
        String locationName = mLocationTextView.getText().toString();
        intent.putExtra(LOCATION_NAME, locationName);
        startActivity(intent);

    }

    //method to start drive mode activity
    private void goToDriveModeActivity() {
        Intent intent = new Intent(this, DriveModeActivity.class);
        startActivity(intent);
    }

    //method to get device location
    private void getMyLocation() {

        try{
            mFusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            //Got last known location. In some rare situations this can be null
                            if(location != null){
                                //logic to handle location object
                                try {
                                    mAddresses = mGeocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                                    if(mAddresses.size()>0)

                                    {
                                        cityName = mAddresses.get(0).getLocality().toString();
                                        mLocationTextView.setText(cityName);
                                        mTimeTextView.setText(currentTimeString);
                                        mProgressBar.setVisibility(View.INVISIBLE);
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();

                                }

                            } else {
                                mLocationTextView.setText("Location not found");
                                mProgressBar.setVisibility(View.INVISIBLE);
                            }

                        }
                    });
        }catch (SecurityException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            switch (requestCode){
                case READ_PHONE_STATE_RESULT:
                    goToDriveModeActivity();
                    break;

                case COARSE_LOCATION_RESULT:
                    getMyLocation();
                    break;
            }
        }

    }

    //method to verify if permissions have been granted and take appropriate action based on results
    private void checkPermission(int position){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            switch(position){
                case 0:
                    if(checkSelfPermission(permissions.get(position)) != PackageManager.PERMISSION_GRANTED){
                        requestPermissions(new String[]{permissions.get(position)}, READ_PHONE_STATE_RESULT);
                    }else {
                        goToDriveModeActivity();
                    }
                    break;
                case 1:
                    if(checkSelfPermission(permissions.get(position)) != PackageManager.PERMISSION_GRANTED){
                        requestPermissions(new String[]{permissions.get(position)}, COARSE_LOCATION_RESULT);
                    }else {
                        getMyLocation();
                    }
                    break;
            }

        }else {

            if (position == 0){
                goToDriveModeActivity();
            }

        }

    }

}
