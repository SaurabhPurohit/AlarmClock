package com.example.ln_20.alarmclock;

import java.util.Calendar;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    private TimePicker timePicker;
    private Switch aSwitch;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        aSwitch = (Switch) findViewById(R.id.aSwitch);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.d("Activity", "In checked");
                    Calendar calendar = Calendar.getInstance();

                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                    calendar.set(Calendar.MINUTE, timePicker.getMinute());
                    Intent intent = new Intent(MainActivity.this, MyReceiver.class);
                    pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0); //request code and flag and PendingIntent
                    alarmManager.set(AlarmManager.RTC,calendar.getTimeInMillis(),pendingIntent); //AlarmManager.RTC

                }else{
                    Log.d("Activity", "In unchecked");
                    alarmManager.cancel(pendingIntent);
                }
            }
        });
    }

}
