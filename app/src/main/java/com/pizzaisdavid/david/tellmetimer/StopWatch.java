package com.pizzaisdavid.david.tellmetimer;

import android.app.Activity;

import java.util.Timer;

public class StopWatch {
  private StopWatchSchedule schedule;
  private UpdateTask task;
  // TODO duration should probably be in this class?

  public StopWatch(Activity activity) {
    this.task = new UpdateTask(activity);
    initialize();
  }
  public StopWatch(StopWatchSchedule schedule, UpdateTask task) {
    this.schedule = schedule;
    this.task = task;
    initialize();
  }

  private void initialize() {
    this.task.pause();
    schedule.start(task);
  }

  public void resume() {
    this.task.resume();
  }

  public void pause() {
    this.task.pause();
  }

  public void reset() {
    this.task.reset();
  }
}
