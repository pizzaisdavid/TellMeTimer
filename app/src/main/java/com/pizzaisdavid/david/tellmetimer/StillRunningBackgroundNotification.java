package com.pizzaisdavid.david.tellmetimer;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

public class StillRunningBackgroundNotification {

  // TODO delete notification on program end.
  // app is running in the background,
  // close it via task manager,
  // cancel notification.

  private int id;
  private NotificationManager notificationManager;
  private NotificationCompat.Builder builder;

  public StillRunningBackgroundNotification(Context context, NotificationManager nm) {
    final int UNIQUE_IDENTIFIER = 1;
    this.id = UNIQUE_IDENTIFIER;
    this.notificationManager = nm;
    this.builder = getBuilder(context);
  }

  private NotificationCompat.Builder getBuilder(Context context) {
    Intent intent = new Intent(context, SplashActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
    NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

    builder.setSmallIcon(R.mipmap.ic_launcher).setContentIntent(pendingIntent).setAutoCancel(false).setCategory("service").setContentTitle("TellMeTimer is still ticking.").setContentText("Touch to check.")
            //.setSubText("Tap to view documentation about notifications.")
            .setPriority(0).setOngoing(true);
    //TODO add pause and quit option?
    return builder;
  }

  public void show() {
    this.notificationManager.notify(this.id, this.builder.build());
  }

  public void hide() {
    this.notificationManager.cancel(this.id);
  }
}
