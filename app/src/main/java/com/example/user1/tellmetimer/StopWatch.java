package com.example.user1.tellmetimer;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class StopWatch {
    // TODO NEXT
    private Timer timer;
    private TimePeriod duration;
    private VoiceNotification voice;
    private TimerTask task;
    private int alarmFrequencyInMinutes;

    public StopWatch(final Activity activity) {

        timer = new Timer();
        duration = new TimePeriod();
        voice = new VoiceNotification(activity, (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE));
        // TODO java.lang.RuntimeException: Unable to start activity ComponentInfo{com.example.user1.tellmetimer/com.example.user1.tellmetimer.MainActivity}: java.lang.IllegalStateException: System services not available to Activities before onCreate()
        alarmFrequencyInMinutes = 1; // TODO variable

        task = new TimerTask() {

            TextView totalTime = (TextView) activity.findViewById(R.id.total_time);
            TextView countDown = (TextView) activity.findViewById(R.id.count_down);
            CheckBox sayCurrentTimeCheckBox = (CheckBox) activity.findViewById(R.id.check_box_current_time);
            CheckBox sayTotalTimeCheckBox = (CheckBox) activity.findViewById(R.id.check_box_total_duration);

            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        update();
                    }
                });
            }

            public void update() {
                TimePeriod untilNextAlarm = getTimeUntilNextAlarm();
                duration.tick();
                this.totalTime.setText(TimePeriodFormat.simple(duration));
                this.countDown.setText(TimePeriodFormat.clock(untilNextAlarm));
                if (untilNextAlarm.getAsSeconds() == 1) {
                    voiceNotification();
                }
            }

            private TimePeriod getTimeUntilNextAlarm() {
                int alarmFrequencyInSeconds = alarmFrequencyInMinutes * 60; // change this for faster testing.
                int timeSinceLastAlarm = duration.getAsSeconds() % alarmFrequencyInSeconds;
                return new TimePeriod(alarmFrequencyInSeconds - timeSinceLastAlarm);
            }

            public void voiceNotification() {
                if (sayCurrentTimeCheckBox.isChecked()) {
                    voice.appendCurrentTimeToQueue();
                }
                voice.appendPauseToQueue();
                if (sayTotalTimeCheckBox.isChecked()) {
                    voice.appendTotalTimeToQueue(duration);
                }
                voice.sayQueue();
            }
        };
    }

    public void start() {
        resume();
    }

    public void resume() {
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(this.task, 0, 1000);
        // TODO starting after is was paused fails
    }

    public void pause() {
        this.timer.cancel();
        this.timer.purge();
        this.timer = null;
    }
}
