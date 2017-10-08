package com.bitland.safedrive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;

public class DriveModeActivity extends AppCompatActivity {

    public Switch mDriveModeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_mode);

        mDriveModeSwitch = (Switch) findViewById(R.id.driveModeSwitch);
    }

//    public boolean isSwitchOn(){
//        return mDriveModeSwitch.isChecked();
//    }
}
