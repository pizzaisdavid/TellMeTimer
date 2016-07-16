package com.pizzaisdavid.david.tellmetimer;

public class StopWatch {
  private StopWatchSchedule schedule;
  private UpdateTask task;
  // TODO duration should probably be in this class?

  public StopWatch(StopWatchSchedule schedule, UpdateTask task) {
    this.schedule = schedule;
    this.task = task;
    initialize();
  }

  private void initialize() {
    task.pause();
    schedule.start(task);
  }

  public void resume() {
    task.resume();
  }

  public void pause() {
    task.pause();
  }

  public void reset() {
    task.reset();
  }
}
