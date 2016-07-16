package com.pizzaisdavid.david.tellmetimer;

public class TimePeriod {
  final int SECONDS_PER_MINUTE = 60;
  private int seconds;

  public TimePeriod() {
    this.seconds = 0;
  }

  public TimePeriod(int seconds) {
    this.seconds = seconds;
  }

  public void tick() {
    this.seconds++;
  }

  public int getAsSeconds() {
    return this.seconds;
  }

  public int getSeconds() {
    return this.seconds % this.SECONDS_PER_MINUTE;
  }

  public int getMinutes() {
    return this.seconds / this.SECONDS_PER_MINUTE;
  }
}
