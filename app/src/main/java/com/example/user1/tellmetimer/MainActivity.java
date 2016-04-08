package com.example.user1.tellmetimer;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  private Button startButton;
  private boolean isGoing = false; // TODO should this be initialized somewhere else?

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // TODO add countdown alarm option Five minutes remaining, three minutes remaining...
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    startButton = (Button) findViewById(R.id.start_button);

    startButton.setOnClickListener(new View.OnClickListener() {
      StopWatch stopWatch = new StopWatch(MainActivity.this);

      public void onClick(View view) {
        isGoing = swapState(isGoing);
        if (isGoing) {
          stopWatch.resume();
          startButton.setText("Pause");
        } else {
          stopWatch.pause();
          startButton.setText("Start");
        }
      }

      private boolean swapState(boolean state) {
        if (state) {
          return false;
        }
        return true;
      }
    });
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    StillRunningBackgroundNotification backgroundNotification = new StillRunningBackgroundNotification(this, notificationManager);
    super.onWindowFocusChanged(hasFocus);
    if (hasFocus == false) {
      // TODO if app is open, and we open the notification drawer, it shouldn't push a notification.
      backgroundNotification.show();
    } else {
      backgroundNotification.hide();
    }
  }
  // TODO pick a start time or start now.
}