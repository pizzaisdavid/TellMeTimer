package com.example.user1.tellmetimer;

// TODO make a toast when volume is muted to notify.
// TODO app is unresponsive for a few seconds after start. Started after adding tabs.
// TODO pick a start time or start now.

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  private Button startButton;
  private Button resetButton;
  private boolean isGoing;
  private StopWatch stopWatch;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    startButton = (Button) findViewById(R.id.start_button);
    resetButton = (Button) findViewById(R.id.reset_button);
    stopWatch = new StopWatch(MainActivity.this);
    isGoing = false;
    resetButton.setVisibility(View.INVISIBLE);
    startButton.setOnClickListener(new View.OnClickListener() {

      public void onClick(View view) {
        isGoing = swapState(isGoing);
        if (isGoing) {
          stopWatch.resume();
          startButton.setText("Pause");
          resetButton.setVisibility(View.INVISIBLE);
        } else {
          stopWatch.pause();
          startButton.setText("Start");
          resetButton.setVisibility(View.VISIBLE);
        }
      }

      private boolean swapState(boolean state) {
        if (state) {
          return false;
        }
        return true;
      }
    });

    resetButton.setOnClickListener(new View.OnClickListener() {

      public void onClick(View view) {
        if (isGoing == false) {
          stopWatch.reset();
          resetButton.setVisibility(View.INVISIBLE);
        }
      }
    });
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    StillRunningBackgroundNotification backgroundNotification = new StillRunningBackgroundNotification(this, notificationManager);
    super.onWindowFocusChanged(hasFocus);
    if (hasFocus == false && isGoing == true) {
      backgroundNotification.show();
    } else {
      backgroundNotification.hide();
    }
  }
}
