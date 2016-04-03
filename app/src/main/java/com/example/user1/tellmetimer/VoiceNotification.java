package com.example.user1.tellmetimer;

import android.content.Context;
import android.media.AudioManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VoiceNotification extends Voice {

    private String message;

    public VoiceNotification(Context context, AudioManager audioManager) {
        super(context, audioManager);
        this.message = "";
    }

    public void appendCurrentTimeToQueue() {
        DateFormat dateFormat = new SimpleDateFormat("h:mm a");
        this.message += " It is currently " + dateFormat.format(new Date()) + ".";
    }

    public void appendPauseToQueue() {
        this.message += "........"; // Hack-y attempt to take a breath between sentences.
    }

    public void appendTotalTimeToQueue(TimePeriod duration) {
        this.message += " This timer has been running for " + TimePeriodFormat.simple(duration) + ".";
    }

    public void sayQueue() {
        say(this.message);
    }
}
