package com.pizzaisdavid.david.tellmetimer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

public class TaskScheduler extends Timer {
  private static final Logger logger = LoggerFactory.getLogger(TaskScheduler.class);

  public TaskScheduler() {
    logger.debug("Initialize");
  }

  public void start(TimerTask task) {
    int DELAY = 0;
    int PERIOD = 1000;
    logger.debug("Starting task with a delay of {} and a period of {}", DELAY, PERIOD);
    scheduleAtFixedRate(task, DELAY, PERIOD);
  }
}
