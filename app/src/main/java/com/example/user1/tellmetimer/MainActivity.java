package com.example.user1.tellmetimer;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Voice voice;
    public AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // TODO add countdown alarm option
        // -- Five minutes remaining, three minutes remaining...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        voice = new Voice(this);
        // Chronometer m = (Chronometer) findViewById(R.id.chronometer); TODO maybe switch to chrono
        // m.start();
        final Timer clock = new Timer();
        TimerTask task = new TimerTask() {
            TextView totalTime = (TextView) findViewById(R.id.total_time);
            TextView countDown = (TextView) findViewById(R.id.count_down);
            TextView alarmFrequencyText = (TextView) findViewById(R.id.alarm_frequency_text);
            CheckBox sayCurrentTimeCheckBox = (CheckBox) findViewById(R.id.check_box_current_time);
            CheckBox sayTotalTimeCheckBox = (CheckBox) findViewById(R.id.check_box_total_duration);
            SeekBar alarmFrequency = (SeekBar) findViewById(R.id.alarm_frequency);
            TimePeriod duration = new TimePeriod();
            int alarmFrequencyInSeconds = 60;


            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        update();
                    }
                });
            }

            public void update() {
                TimePeriod untilNextAlarm = getTimeUntilNextAlarm();
                this.duration.tick();

                int alarmFrequencyInMinutes = alarmFrequency.getProgress() + 1;
                this.alarmFrequencyInSeconds = alarmFrequencyInMinutes * 60;
                this.alarmFrequencyText.setText(formatAlarmFrequency(alarmFrequencyInMinutes));

                this.totalTime.setText(TimePeriodFormat.simple(this.duration));
                this.countDown.setText(TimePeriodFormat.clock(untilNextAlarm));
                if (untilNextAlarm.getSeconds() == 1) {
                    voiceNotification();
                }
            }

            private TimePeriod getTimeUntilNextAlarm() {
                int timeSinceLastAlarm = this.duration.getSeconds() % this.alarmFrequencyInSeconds;
                return new TimePeriod(this.alarmFrequencyInSeconds - timeSinceLastAlarm);
            }

            private String formatAlarmFrequency(int count) {
                final String base = "Sound the alarm every ";
                if (count == 1) {
                    return base + "minute.";
                }
                return base + count + " minutes.";
            }

            public void voiceNotification() {
                DateFormat dateFormat = new SimpleDateFormat("h:mm a");
                boolean isCurrentTimeBoxChecked = sayCurrentTimeCheckBox.isChecked();
                boolean isTotalTimeBoxChecked = sayTotalTimeCheckBox.isChecked();
                // TODO mute other audio as notification plays
                if (isCurrentTimeBoxChecked) {
                    voice.say("It is currently " + dateFormat.format(new Date()));
                }
                if (isCurrentTimeBoxChecked && isTotalTimeBoxChecked) {
                    voice.sayNothing(500);
                }
                if (isTotalTimeBoxChecked) {
                    voice.say("This timer has been running for " + TimePeriodFormat.simple(this.duration));
                }
            }
        };
        clock.scheduleAtFixedRate(task, 0, 1000);
    }
    // TODO add start button
}