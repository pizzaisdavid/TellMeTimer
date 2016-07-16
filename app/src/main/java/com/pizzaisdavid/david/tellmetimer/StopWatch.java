package com.pizzaisdavid.david.tellmetimer;

import android.util.Log;

public class StopWatch {
  private StopWatchSchedule schedule;
  private UpdateTask task;
  // TODO duration should probably be in this class?

  public StopWatch(StopWatchSchedule schedule, UpdateTask task) {
    Log.i("AccessTokenWrapper", "Init");
    this.schedule = schedule;
    this.task = task;
  }

  public void setup() {
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
