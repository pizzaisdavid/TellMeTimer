package com.pizzaisdavid.david.tellmetimer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StopWatch {
  private static final Logger logger = LoggerFactory.getLogger(StopWatch.class);
  private StopWatchSchedule schedule;
  private UpdateTask task;
  // TODO duration should probably be in this class?

  public StopWatch(StopWatchSchedule schedule, UpdateTask task) {
    logger.info("Initialize");
    this.schedule = schedule;
    this.task = task;
  }

  public void setup() {
    task.pause();
    schedule.start(task);
  }

  public void resume() {
    logger.info("Resume");
    task.resume();
  }

  public void pause() {
    logger.info("Pause");
    task.pause();
  }

  public void reset() {
    logger.info("Reset");
    task.reset();
  }
}
