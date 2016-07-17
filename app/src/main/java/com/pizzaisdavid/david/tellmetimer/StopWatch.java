package com.pizzaisdavid.david.tellmetimer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StopWatch {
  private static final Logger logger = LoggerFactory.getLogger(StopWatch.class);
  private TaskScheduler scheduler;
  private UpdateTask task;
  // TODO duration should probably be in this class?

  public StopWatch(TaskScheduler scheduler, UpdateTask task) {
    logger.info("Initializing");
    this.scheduler = scheduler;
    this.task = task;
  }

  public void setup() {
    task.pause();
    scheduler.start(task);
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
