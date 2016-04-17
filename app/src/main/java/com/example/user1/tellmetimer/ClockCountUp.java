package com.example.user1.tellmetimer;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClockCountUp extends Activity {

  private Button startButton;
  private Button resetButton;
  private boolean isGoing;
  private StopWatch stopWatch; // TODO why does this work anyways?

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState)
    ;
    startButton = (Button) findViewById(R.id.start_button);
    resetButton = (Button) findViewById(R.id.reset_button);
    stopWatch = new StopWatch(this);
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
      // TODO NEXT-ISH if paused, don't make notification.
      backgroundNotification.show();
    } else {
      backgroundNotification.hide();
    }
  }
}