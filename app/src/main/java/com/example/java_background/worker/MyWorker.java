package com.example.java_background.worker;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.java_background.R;

public class MyWorker extends  Worker {
    private static final String CHANNEL_ID = "Work Channel" ;

    NotificationManager notificationManager;

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

    }

    @NonNull
    @Override
    public Result doWork() {
        notificationManager = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel();
        try {
            int num = 0;
            for (int i = 0; i < 100; i++) {
                num++;

                Data data = new Data.Builder()
                        .putInt("progress", num)
                        .build();

                setProgressAsync(data);
                showNotification(num);
                Thread.sleep(100);
            }
        }catch (Exception e) {
            Result.failure();
        }
        return Result.success();
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
        Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("WorkManager Long Task")
                //.setContentText(textContent)
                .setProgress(100, progress, false) //false : 프로그래스바로 표현
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(3, notification);
    }
}
