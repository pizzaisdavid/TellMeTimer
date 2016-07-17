package com.pizzaisdavid.david.tellmetimer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

public class TaskScheduler extends Timer {
  private static final Logger logger = LoggerFactory.getLogger(TaskScheduler.class);
  private int delay;
  private int period;

  public TaskScheduler() {
    logger.debug("Initialize");
    delay = 0;
    period = 1000;
  }

  public void start(TimerTask task) {
    logger.debug("Starting task with a delay of {} and a period of {}", delay, period);
    scheduleAtFixedRate(task, delay, period);
  }
}
