package com.bitland.safedrive.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;

import com.bitland.safedrive.R;

public class DriveModeActivity extends AppCompatActivity {

    public static Switch mDriveModeSwitch;
//    private IncomingCallMonitor mCallMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_mode);

        mDriveModeSwitch =  findViewById(R.id.driveModeSwitch);
    }

//    public boolean isSwitchOn(){
//        return mDriveModeSwitch.isChecked();
//    }
}
