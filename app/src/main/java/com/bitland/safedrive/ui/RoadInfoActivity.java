package com.bitland.safedrive.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bitland.safedrive.R;
import com.bitland.safedrive.adapters.RoadAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoadInfoActivity extends AppCompatActivity {

    @BindView(R.id.roadNameTextView)TextView mRoadNameTextView;
    @BindView(R.id.trafficVolumeLabel)TextView mTrafficVolumeLabel;
    @BindView(R.id.roadBlockLabel)TextView mRoadBlockLabel;
    @BindView(R.id.trafficLightsLabel)TextView mTrafficLightsLabel;
    @BindView(R.id.carnageLabel)TextView mCarnageLabel;
    @BindView(R.id.trafficVolumeTextView)TextView mTrafficVolumeTextView;
    @BindView(R.id.roadBlockTextView)TextView mRoadBlockTextView;
    @BindView(R.id.trafficLightsTextView)TextView mTrafficLightsTextView;
    @BindView(R.id.carnageTextView)TextView mCarnageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_info);
        ButterKnife.bind(this);

        displayRoadInfo();

    }

    private void displayRoadInfo() {
        Intent intent = getIntent();

        String roadName = intent.getStringExtra(RoadAdapter.ROAD_NAME);
        String trafficVolume = intent.getStringExtra(RoadAdapter.TRAFFIC_VOLUME);
        String roadBlockCount  = intent.getStringExtra(RoadAdapter.ROAD_BLOCK);
        String trafficLights  = intent.getStringExtra(RoadAdapter.TRAFFIC_LIGHTS);
        String carnageCount  = intent.getStringExtra(RoadAdapter.CARNAGE_COUNT);

        mRoadNameTextView.setText(roadName);
        mTrafficVolumeTextView.setText(trafficVolume);
        mRoadBlockTextView.setText(roadBlockCount);
        mTrafficLightsTextView.setText(trafficLights);
        mCarnageTextView.setText(carnageCount);
    }
}
