package com.example.user1.tellmetimer;

import android.app.Activity;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.TimerTask;

public class UpdateTask extends TimerTask {

  private boolean isPaused;
  final private Activity activity;
  private TextView totalTime;
  private TextView countDown;
  private CheckBox sayCurrentTimeCheckBox;
  private CheckBox sayTotalTimeCheckBox; // TODO make a class that deals with all the graphics
  private SeekBar alarmFrequency;
  private AlarmFrequencyListener alarmFrequencyListener;


  private TimePeriod duration;
  private VoiceNotification voice;


  public UpdateTask(final Activity activity) {
    this.totalTime = (TextView) activity.findViewById(R.id.total_time);
    this.countDown = (TextView) activity.findViewById(R.id.count_down);
    this.sayCurrentTimeCheckBox = (CheckBox) activity.findViewById(R.id.check_box_current_time);
    this.sayTotalTimeCheckBox = (CheckBox) activity.findViewById(R.id.check_box_total_duration);
    this.alarmFrequency = (SeekBar) activity.findViewById(R.id.alarm_frequency);

    this.duration = new TimePeriod();
    this.voice = new VoiceNotification(activity);
    this.activity = activity;
    this.isPaused = false;
    this.alarmFrequencyListener = new AlarmFrequencyListener(activity);
    alarmFrequency.setOnSeekBarChangeListener(this.alarmFrequencyListener);
  }

  @Override
  public void run() {
    this.activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        update();
      }
    });
  }

  public void update() {
    if (isPaused == false) {
      TimePeriod untilNextAlarm = getTimeUntilNextAlarm();
      duration.tick();
      this.totalTime.setText(TimePeriodFormat.simple(duration));
      this.countDown.setText(TimePeriodFormat.clock(untilNextAlarm));
      if (untilNextAlarm.getAsSeconds() == 1) {
        voiceNotification();
      }
    }
  }

  private TimePeriod getTimeUntilNextAlarm() {
    int alarmFrequencyInSeconds = this.alarmFrequencyListener.getAsSeconds();
    int timeSinceLastAlarm = duration.getAsSeconds() % alarmFrequencyInSeconds;
    return new TimePeriod(alarmFrequencyInSeconds - timeSinceLastAlarm);
  }

  private void voiceNotification() {
    if (sayCurrentTimeCheckBox.isChecked()) {
      this.voice.appendCurrentTimeToQueue();
    }
    this.voice.appendPauseToQueue();
    if (sayTotalTimeCheckBox.isChecked()) {
      this.voice.appendTotalTimeToQueue(duration);
    }
    this.voice.sayQueue();
  }

  public void pause() {
    this.isPaused = true;
  }

  public void resume() {
    this.isPaused = false;
  }
}
