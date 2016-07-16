package com.pizzaisdavid.david.tellmetimer;

import java.util.Timer;

public class StopWatchSchedule extends Timer {

  public StopWatchSchedule() {
  }

  public void start(UpdateTask task) {
    scheduleAtFixedRate(task, 0, 1000);
  }
}
