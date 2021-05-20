package com.example.appmenu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(intent.ACTION_POWER_CONNECTED)){
            Toast.makeText(context, "Hello BroadcastReceiver", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "GoodBye BroadcastReceiver", Toast.LENGTH_SHORT).show();
        }
    }
}
