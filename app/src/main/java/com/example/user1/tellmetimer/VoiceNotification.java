package com.example.user1.tellmetimer;

import android.content.Context;
import android.media.AudioManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VoiceNotification extends Voice {

    private String queue;

    public VoiceNotification(Context context, AudioManager audioManager) {
        super(context, audioManager);
        this.queue = "";
    }

    public void appendCurrentTimeToQueue() {
        DateFormat dateFormat = new SimpleDateFormat("h:mm a");
        this.queue += " It is currently " + dateFormat.format(new Date()) + ".";
    }

    public void appendPauseToQueue() {
        this.queue += "........"; // Hack-y attempt to take a breath between sentences.
    }

    public void appendTotalTimeToQueue(TimePeriod duration) {
        this.queue += " This timer has been running for " + TimePeriodFormat.simple(duration) + ".";
    }

    public void sayQueue() {
        say(this.queue);
        this.queue = "";
    }
}
