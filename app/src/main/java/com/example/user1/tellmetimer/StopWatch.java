package com.example.user1.tellmetimer;

import android.app.Activity;

import java.util.Timer;

public class StopWatch {

  private Timer schedule;
  private UpdateTask task;
  // TODO duration should probably be in this class?

  public StopWatch(final Activity activity) {
    this.schedule = new Timer();
    this.task = new UpdateTask(activity);
    initialize();
  }

  private void initialize() {
    this.task.pause();
    start();
  }

  private void start() {
    this.schedule.scheduleAtFixedRate(this.task, 0, 1000);
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
