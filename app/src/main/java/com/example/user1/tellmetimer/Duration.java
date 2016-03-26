package com.example.user1.tellmetimer;

public class Duration {

    private int seconds;
    private int minutes;

    public Duration() {
        this.seconds = 0;
        this.minutes = 0;
    }

    public void tick() {
        final int SECONDS_PER_MINUTE = 60;
        // TODO might have a one off.
        if (SECONDS_PER_MINUTE < this.seconds) {
            this.minutes++;
            this.seconds = 0;
        }
        this.seconds++;
    }

    public int getAsSeconds() {
        return this.seconds;
    }

    @Override
    public String toString() {
        String message = "";
        if (minutes != 0) {
            message = message + " " + minutes + " minutes";
        }
        if (seconds != 0) {
            message = message + " " + seconds + " seconds.";
        }
        return message;
    }
}
