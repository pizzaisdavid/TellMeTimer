package com.pizzaisdavid.david.tellmetimer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VoiceNotificationBuilder {
  private static final Logger logger = LoggerFactory.getLogger(UpdateTask.class);
  private String queue;

  public VoiceNotificationBuilder() {
    logger.debug("Initializing");
    queue = "";
  }

  public void appendCurrentTime() {
    DateFormat dateFormat = new SimpleDateFormat("h:mm a");
    queue += " It is currently " + dateFormat.format(new Date()) + ".";
  }

  public void appendPause() {
    queue += "..."; // TODO attempt to take a breath between sentences.
  }

  public void appendDuration(TimePeriod duration) {
    queue += " This timer has been running for " + TimePeriodFormat.simple(duration) + ".";
  }

  public String toString() {
    logger.info("toString: {}", queue);
    return queue;
  }
}
