package com.example.user1.tellmetimer;

import java.util.Timer;
import java.util.TimerTask;

public class Clock extends Timer {
    // TODO NEXT

    public Clock() {

    }

    public void onTick(TimerTask task) {
        scheduleAtFixedRate(task, 0, 1000);
    }

    public void resume() {

    }

    public void pause() {

    }
}
