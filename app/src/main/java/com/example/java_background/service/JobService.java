package com.example.java_background.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.java_background.R;
import com.example.java_background.Result;

public class JobService extends JobIntentService {

    private static final String CHANNEL_ID = "Job Channel" ;
    NotificationManager notificationManager;
//    private NotificationManager notificationManager
//            = ContextCompat.getSystemService(this, NotificationManager.class);

    public static void enqueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, JobService.class, 1000, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        createNotificationChannel();

        try {
            int num = 0;
            for (int i = 0; i < 100; i++) {
                num++;
                showNotification(num);
                Thread.sleep(100);
            }
        }catch (Exception e) {

        }
    }

    //https://developer.android.com/training/notify-user/channels
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "job channel";
            String description = "job channel";
            int importance = NotificationManager.IMPORTANCE_LOW; //노티에서 소리 안남.
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this

            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification(int progress) {
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Job Service")
                    //.setContentText(textContent)
                    .setProgress(100, progress, false) //false : 프로그래스바로 표현
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build();

        notificationManager.notify(2, notification);
    }
}
