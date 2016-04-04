package com.example.user1.tellmetimer;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;

/**
 * Created by User1 on 3/29/16.
 */
public class StillRunningBackgroundNotification {

    // TODO
    // app is running in the background,
    // close it via task manager,
    // cancel notificaion.

    private int id;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;

    public StillRunningBackgroundNotification(int id, Context context, NotificationManager nm) {
        this.id = id;
        this.notificationManager = nm;
        this.builder = getBuilder(context);
    }

    private NotificationCompat.Builder getBuilder(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(false);
        builder.setCategory("service");
        builder.setContentTitle("Time is still ticking!");
        builder.setContentText("Time to learn about notifications!");
        builder.setSubText("Tap to view documentation about notifications.");
        builder.setPriority(0);
        return  builder;
    }

    public void show() {
        this.notificationManager.notify(this.id, this.builder.build());
    }

    public void hide() {
        this.notificationManager.cancel(this.id);
    }
}
