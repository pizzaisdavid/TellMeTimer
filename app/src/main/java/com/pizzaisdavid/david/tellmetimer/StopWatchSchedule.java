package com.pizzaisdavid.david.tellmetimer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;

public class StopWatchSchedule extends Timer {
  private static final Logger logger = LoggerFactory.getLogger(StopWatchSchedule.class);

  public StopWatchSchedule() {
    logger.debug("Initialize");
  }

  public void start(UpdateTask task) {
    logger.debug("Start with task");
    scheduleAtFixedRate(task, 0, 1000);
  }
}
