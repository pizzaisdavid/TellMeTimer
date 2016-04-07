package com.example.user1.tellmetimer;

public class TimePeriod {

    private int seconds;
    final int SECONDS_PER_MINUTE = 60;

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
