package com.example.user1.tellmetimer;

public class Duration {
    // TODO maybe extend from Date()

    public int minutes;
    public int seconds;

    public Duration() {
        this.minutes = 0;
        this.seconds = 0;
    }

    public Duration(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public void tick() {
        final int SECONDS_PER_MINUTE = 60;
        this.seconds++;
        if (SECONDS_PER_MINUTE <= this.seconds) {
            this.minutes++;
            this.seconds = 0;
        }
    }

    @Override
    public String toString() {
        // TODO should formatting be its own class?
        return this.minutesToString() + " " + this.secondsToString();
    }

    private String minutesToString() {
        if (isThereAny(this.minutes)) {
            return getProperSubjectVerbAgreement(this.minutes, "minute", "minutes");
        }
        return "";
    }

    private String secondsToString() {
        if (isThereAny(this.seconds)) {
            return getProperSubjectVerbAgreement(this.seconds, "second", "seconds");
        }
        return "";
    }

    private boolean isThereAny(int count) {
        return count != 0;
    }

    private String getProperSubjectVerbAgreement(int count, String singular, String plural) {
        if (count == 1) {
            return "1 " + singular;
        }
        return count + " " + plural;
    }
}
