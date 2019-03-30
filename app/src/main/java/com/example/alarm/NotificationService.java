package com.example.alarm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

public class NotificationService extends IntentService {

    //Notification ID for Alarm
    public static final int NOTIFICATION_ID = 1;

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        //Kirim Notif
        sendNotification("Ponsel anda dalam mode berdering");
    }

    //handle notification
    private void sendNotification(String msg) {
        NotificationManager alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        //get pending intent
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        //Membuat Notif
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder alarmNotificationBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("ThungThling").setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setSound(sound)
                .setContentText(msg).setAutoCancel(true)
                .setContentIntent(contentIntent);


        alarmNotificationManager.notify(NOTIFICATION_ID, alarmNotificationBuilder.build());
    }

}
