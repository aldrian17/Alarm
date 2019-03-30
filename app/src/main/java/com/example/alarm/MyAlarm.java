package com.example.alarm;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;

import static android.support.v4.content.WakefulBroadcastReceiver.startWakefulService;

public class MyAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Mode berdering", Toast.LENGTH_SHORT).show();

        //Akan mengirim notif pesan dan menampilkan notif
        ComponentName comp = new ComponentName(context.getPackageName(),
                NotificationService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));

        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);

    }
}
