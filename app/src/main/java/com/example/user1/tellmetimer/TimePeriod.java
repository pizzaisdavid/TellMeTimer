package com.example.user1.tellmetimer;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.Duration;

public class TimePeriod {

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
        final int SECONDS_PER_MINUTE = 60;
        return this.seconds % 60;
    }

    public int getMinutes() {
        final int SECONDS_PER_MINUTE = 60;
        return this.seconds / 60;
    }
}
