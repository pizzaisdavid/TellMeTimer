package com.pizzaisdavid.david.tellmetimer;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class UpdateTask extends TimerTask {
  private static final Logger logger = LoggerFactory.getLogger(UpdateTask.class);
  public TimePeriod duration;
  private boolean isPaused;
  private Activity activity;
  private TextView totalTime;
  private TextView countDown;
  private CheckBox sayCurrentTimeCheckBox;
  private CheckBox sayTotalTimeCheckBox; // TODO make a class that deals with all the graphics
  private SeekBar alarmFrequency;
  private AlarmFrequencyListener alarmFrequencyListener;
  private VoiceNotification voice;


  public UpdateTask(Activity activity, TextToSpeech textToSpeech, AlarmFrequencyListener alarmFrequencyListener) {
    logger.info("Initializing");
    this.totalTime = (TextView) activity.findViewById(R.id.total_time);
    this.countDown = (TextView) activity.findViewById(R.id.count_down);
    this.sayCurrentTimeCheckBox = (CheckBox) activity.findViewById(R.id.check_box_current_time);
    this.sayTotalTimeCheckBox = (CheckBox) activity.findViewById(R.id.check_box_total_duration);
    this.alarmFrequency = (SeekBar) activity.findViewById(R.id.alarm_frequency);
    this.voice = new VoiceNotification(textToSpeech);
    this.activity = activity;
    this.alarmFrequencyListener = alarmFrequencyListener;
    this.alarmFrequency.setOnSeekBarChangeListener(this.alarmFrequencyListener); // TODO this breaks the test, move this or something
    this.duration = new TimePeriod();
    this.isPaused = false;
  }

  @Override
  public void run() {
    activity.runOnUiThread(new Runnable() {
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
      totalTime.setText(TimePeriodFormat.simple(duration));
      countDown.setText(TimePeriodFormat.clock(untilNextAlarm));
      if (untilNextAlarm.getAsSeconds() == 1) {
        voiceNotification();
      }
    }
  }

  private TimePeriod getTimeUntilNextAlarm() {
    int alarmFrequencyInSeconds = alarmFrequencyListener.getAsSeconds();
    int timeSinceLastAlarm = duration.getAsSeconds() % alarmFrequencyInSeconds;
    logger.debug("Time since last alarm: {}", timeSinceLastAlarm);
    return new TimePeriod(alarmFrequencyInSeconds - timeSinceLastAlarm);
  }

  private void voiceNotification() {
    if (sayCurrentTimeCheckBox.isChecked()) {
      voice.appendCurrentTimeToQueue();
    }
    voice.appendPauseToQueue();
    if (sayTotalTimeCheckBox.isChecked()) {
      voice.appendTotalTimeToQueue(duration);
    }
    voice.sayQueue();
  }

  public void pause() {
    isPaused = true;
  }

  public void resume() {
    isPaused = false;
  }

  public void reset() {
    duration = new TimePeriod(0);
    totalTime.setText("0 seconds");
    countDown.setText("00:00");
  }
}
