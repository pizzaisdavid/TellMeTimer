package com.pizzaisdavid.david.tellmetimer;

import android.app.Activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VoiceNotification extends Voice {

  private String queue;

  public VoiceNotification(Activity activity) {
    super(activity);
    this.queue = "";
  }

  public void appendCurrentTimeToQueue() {
    DateFormat dateFormat = new SimpleDateFormat("h:mm a");
    this.queue += " It is currently " + dateFormat.format(new Date()) + ".";
  }

  public void appendPauseToQueue() {
    this.queue += "........"; // TODO attempt to take a breath between sentences.
  }

  public void appendTotalTimeToQueue(TimePeriod duration) {
    this.queue += " This timer has been running for " + TimePeriodFormat.simple(duration) + ".";
  }

  public void sayQueue() {
    say(this.queue);
    this.queue = "";
  }
}
