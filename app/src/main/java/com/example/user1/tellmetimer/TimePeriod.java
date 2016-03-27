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

    public int getSeconds() {
        return this.seconds;
    }
}
