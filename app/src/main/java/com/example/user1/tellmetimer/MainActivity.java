package com.example.user1.tellmetimer;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  private int alarmFrequencyInMinutes;
  private Button startButton;
  private boolean isGoing = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // TODO add countdown alarm option Five minutes remaining, three minutes remaining...
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    startButton = (Button) findViewById(R.id.start_button);

    alarmFrequencyInMinutes = 2;
    SeekBar alarmFrequency = (SeekBar) findViewById(R.id.alarm_frequency);

    alarmFrequency.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        TextView alarmFrequencyText = (TextView) findViewById(R.id.alarm_frequency_text);
        alarmFrequencyInMinutes = progress + 1;
        alarmFrequencyText.setText(AlarmFrequencyFormat.format(alarmFrequencyInMinutes));
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });

    startButton.setOnClickListener(new View.OnClickListener() {
      StopWatch stopWatch = new StopWatch(MainActivity.this);

      public void onClick(View view) {
        // TODO change text and pause
        if (isGoing) {
          isGoing = false;
        } else {
          isGoing = true;
        }

        if (isGoing) {
          stopWatch.resume();
          startButton.setText("Pause");
        } else {
          stopWatch.pause();
          startButton.setText("Start");
        }
      }
    });
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    final int NOTIFICATION_ID = 1;
    StillRunningBackgroundNotification backgroundNotification = new StillRunningBackgroundNotification(
            NOTIFICATION_ID,
            this,
            (NotificationManager) getSystemService(NOTIFICATION_SERVICE)
    );
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