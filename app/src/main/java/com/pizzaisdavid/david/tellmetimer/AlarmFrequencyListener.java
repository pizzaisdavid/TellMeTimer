package com.pizzaisdavid.david.tellmetimer;

import android.app.Activity;
import android.widget.SeekBar;
import android.widget.TextView;


public class AlarmFrequencyListener implements SeekBar.OnSeekBarChangeListener {

  private int minutes;
  private TextView alarmFrequencyText;

  public AlarmFrequencyListener(Activity activity) {
    this.alarmFrequencyText = (TextView) activity.findViewById(R.id.alarm_frequency_text);
    this.minutes = 2;
  }

  @Override
  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    this.minutes = progress + 1;
    this.alarmFrequencyText.setText(AlarmFrequencyFormat.format(this.minutes));
  }

  @Override
  public void onStartTrackingTouch(SeekBar seekBar) {
  }

  @Override
  public void onStopTrackingTouch(SeekBar seekBar) {
  }

  public int getAsSeconds() {
    final int SECONDS_PER_MINUTE = 60; // change this for faster testing.
    return this.minutes * SECONDS_PER_MINUTE;
  }
}
