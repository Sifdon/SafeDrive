/**This is the activity which comes up when the Drive Mode Switch in MainActivity is clicked. The class makes use of the switch
 widget to allow the user to toggle drive mode on or off in order to listen for incoming calls.*/
package com.bitland.safedrive.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;

import com.bitland.safedrive.R;

public class DriveModeActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "com.bitland.safedrive.ui.preferences";
    private static final String KEY_SWITCH = "key_switch";
    public static Switch mDriveModeSwitch;

    //Shared preferences to hold the state of the switch when the app is closed and restore it when app is restarted
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_mode);

        mDriveModeSwitch =  findViewById(R.id.driveModeSwitch);

        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        //retrieving the saved state of mDriveModeSwitch and restoring it
        boolean switchState = mSharedPreferences.getBoolean(KEY_SWITCH, false);
        mDriveModeSwitch.setChecked(switchState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //saving the state of the mDriveModeSwitch
        mEditor.putBoolean(KEY_SWITCH, mDriveModeSwitch.isChecked());
        mEditor.apply();
    }
}
