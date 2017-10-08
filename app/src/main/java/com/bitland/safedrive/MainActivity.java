package com.bitland.safedrive;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CODE = 11;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.timeLabel) TextView mTimeLabel;
    @BindView(R.id.trafficVolumeLabel) TextView mTrafficVolumeLabel;
    @BindView(R.id.roadblocksLabel) TextView mRoadblocksLabel;
    @BindView(R.id.robotsLabel) TextView mRobotsLabel;
    @BindView(R.id.carnageLabel) TextView mCarnageLabel;
    @BindView(R.id.timeTextView) TextView mTimeTextView;
    @BindView(R.id.trafficTextView) TextView mTrafficTextView;
    @BindView(R.id.roadblocksTextView) TextView mRoadBlockTextView;
    @BindView(R.id.robotsTextView) TextView mRobotsTextView;
    @BindView(R.id.carnageTextView) TextView mCarnageTextView;
    @BindView(R.id.driveModeButton) Button mDriveModeButton;
    @BindView(R.id.locationButton) Button mLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.driveModeButton) void startDriveModeActivity(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},PERMISSIONS_REQUEST_CODE);
            } else {
                Intent intent = new Intent(this, DriveModeActivity.class);
                startActivity(intent);
            }

        } else {
            Intent intent = new Intent(this, DriveModeActivity.class);
            startActivity(intent);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSIONS_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(this, DriveModeActivity.class);
                startActivity(intent);
            }
        }
    }
}
