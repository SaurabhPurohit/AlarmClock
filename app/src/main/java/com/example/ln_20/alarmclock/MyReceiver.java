package com.example.ln_20.alarmclock;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ln-20 on 18/1/17.
 */

public class MyReceiver extends WakefulBroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("RECEIVER", "in OnReceive");
        Log.d("RECEIVER", "ALARM ON");

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(uri == null){
            uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        Ringtone ringtone = RingtoneManager.getRingtone(context, uri);
        ringtone.play();

        //do research

        ComponentName componentName = new ComponentName(context.getPackageName(),MyService.class.getName());
        startWakefulService(context, (intent.setComponent(componentName)));
        setResultCode(Activity.RESULT_OK);
        //Toast.makeText(context,"Alarm",Toast.LENGTH_LONG).show();
    }
}
