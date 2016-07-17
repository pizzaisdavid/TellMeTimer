package com.pizzaisdavid.david.tellmetimer;

import android.speech.tts.TextToSpeech;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VoiceNotification extends Voice {
  private String queue;

  public VoiceNotification(TextToSpeech textToSpeech) {
    super(textToSpeech);
    queue = "";
  }

  public void appendCurrentTimeToQueue() {
    DateFormat dateFormat = new SimpleDateFormat("h:mm a");
    queue += " It is currently " + dateFormat.format(new Date()) + ".";
  }

  public void appendPauseToQueue() {
    queue += "..."; // TODO attempt to take a breath between sentences.
  }

  public void appendTotalTimeToQueue(TimePeriod duration) {
    queue += " This timer has been running for " + TimePeriodFormat.simple(duration) + ".";
  }

  public void sayQueue() {
    say(queue);
    queue = "";
  }
}
