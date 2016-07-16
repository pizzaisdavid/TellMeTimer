package com.pizzaisdavid.david.tellmetimer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimePeriod {
  private static final Logger logger = LoggerFactory.getLogger(MainActivity.class);
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
    logger.info("tick second");
    this.seconds++;
  }

  public int getAsSeconds() {
    return this.seconds;
  }

  public int getSeconds() {
    return this.seconds % this.SECONDS_PER_MINUTE;
  }

  public int getMinutes() {
    return this.seconds / this.SECONDS_PER_MINUTE;
  }
}
