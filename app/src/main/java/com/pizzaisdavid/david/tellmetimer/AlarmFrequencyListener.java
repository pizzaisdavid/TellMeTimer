package com.pizzaisdavid.david.tellmetimer;

import android.app.Activity;
import android.widget.SeekBar;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AlarmFrequencyListener implements SeekBar.OnSeekBarChangeListener {
  private static final Logger logger = LoggerFactory.getLogger(AlarmFrequencyListener.class);
  private int minutes;
  private TextView alarmFrequencyText;

  public AlarmFrequencyListener(Activity activity) {
    logger.info("Initializing");
    int DEFAULT_ALARM_FREQUENCY = 2;
    this.alarmFrequencyText = (TextView) activity.findViewById(R.id.alarm_frequency_text);
    minutes = DEFAULT_ALARM_FREQUENCY;
  }

  @Override
  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    minutes = progress + 1;
    logger.info("onProgressChanged minutes set to {}", minutes);
    alarmFrequencyText.setText(AlarmFrequencyFormat.format(minutes));
  }

  @Override
  public void onStartTrackingTouch(SeekBar seekBar) {
  }

  @Override
  public void onStopTrackingTouch(SeekBar seekBar) {
  }

  public int getAsSeconds() {
    final int SECONDS_PER_MINUTE = 60;
    int seconds = minutes * SECONDS_PER_MINUTE;
    logger.info("Convert {} minutes to {} seconds", minutes, seconds);
    return seconds;
  }
}
