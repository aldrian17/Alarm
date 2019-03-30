package com.example.alarm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.os.Build.VERSION.SDK_INT;

public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting the timepicker object
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        findViewById(R.id.jadwal_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //We need a calendar object to get the specified time in millis
                //as the alarm manager method takes time in millis to setup the alarm
                Calendar calendar = Calendar.getInstance();
                if (SDK_INT >= 23) {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(), timePicker.getMinute(), 0);
                } else {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
                }


                setAlarm(calendar.getTimeInMillis());
            }
        });

        findViewById(R.id.batal_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                batalAlarm();
            }
        });
    }

    private void setAlarm(long time) {
        //getting the alarm manager
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //creating a new intent specifying the broadcast receiver
        Intent i = new Intent(this, MyAlarm.class);

        //creating a pending intent using the intent
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);

        //setting the repeating alarm that will be fired every day
        am.setRepeating(AlarmManager.RTC, time, AlarmManager.INTERVAL_DAY, pi);
        Toast.makeText(this, "Telah diaktifkan", Toast.LENGTH_SHORT).show();
    }
    private void batalAlarm (){
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent i = new Intent(this, MyAlarm.class);

        //creating a pending intent using the intent
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);

        NotificationManager notificationManager = (NotificationManager) this
        .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NotificationService.NOTIFICATION_ID);

        am.cancel(pi);
        Toast.makeText(this, "Telah dihentikan", Toast.LENGTH_SHORT).show();


    }
}
