package com.bitland.safedrive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class IncomingCallMonitor extends BroadcastReceiver {

    private DriveModeActivity mActivity;
    @Override
    public void onReceive(Context context, Intent intent) {

        if(mActivity.mDriveModeSwitch.isChecked()){

            try {
                String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

                if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                    Toast.makeText(context, "Phone is Ringing", Toast.LENGTH_SHORT).show();
                }

                if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                    Toast.makeText(context, "Call Received", Toast.LENGTH_SHORT).show();
                }

                if(state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                    Toast.makeText(context, "Phone is idle", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

    }
}