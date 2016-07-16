package com.pizzaisdavid.david.tellmetimer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimePeriod {
  private static final Logger logger = LoggerFactory.getLogger(TimePeriod.class);
  final int SECONDS_PER_MINUTE = 60;
  private int seconds;

  public TimePeriod() {
    this(0);
  }

  public TimePeriod(int seconds) {
    logger.info("Initialize with {} seconds", seconds);
    this.seconds = seconds;
  }

  public void tick() {
    int incrementedSeconds = seconds + 1;
    logger.info("tick {} to {}", seconds, incrementedSeconds);
    seconds = incrementedSeconds;
  }

  public int getAsSeconds() {
    return seconds;
  }

  public int getSeconds() {
    return seconds % SECONDS_PER_MINUTE;
  }

  public int getMinutes() {
    int minutes = seconds / SECONDS_PER_MINUTE;
    logger.debug("getMinutes {}", minutes);
    return minutes;
  }
}
