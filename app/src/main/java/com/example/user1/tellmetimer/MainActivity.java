package com.example.user1.tellmetimer;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private VoiceNotification voice;
    private int alarmFrequencyInMinutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO add countdown alarm option
        // -- Five minutes remaining, three minutes remaining...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        voice = new VoiceNotification(this, (AudioManager) getSystemService(Context.AUDIO_SERVICE));
        // Chronometer m = (Chronometer) findViewById(R.id.chronometer); TODO maybe switch to chrono
        // m.start();

        alarmFrequencyInMinutes = 2;
        Button startButton = (Button) findViewById(R.id.start_button);
        SeekBar alarmFrequency = (SeekBar) findViewById(R.id.alarm_frequency);

        alarmFrequency.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView alarmFrequencyText = (TextView) findViewById(R.id.alarm_frequency_text);
                alarmFrequencyInMinutes = progress + 1;
                alarmFrequencyText.setText(formatAlarmFrequency(alarmFrequencyInMinutes));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            private String formatAlarmFrequency(int count) {
                final String base = "Sound the alarm every ";
                if (count == 1) {
                    return base + "minute.";
                }
                return base + count + " minutes.";
            }
        });


        final Timer clock = new Timer();
        TimerTask task = new TimerTask() {
            TextView totalTime = (TextView) findViewById(R.id.total_time);
            TextView countDown = (TextView) findViewById(R.id.count_down);
            CheckBox sayCurrentTimeCheckBox = (CheckBox) findViewById(R.id.check_box_current_time);
            CheckBox sayTotalTimeCheckBox = (CheckBox) findViewById(R.id.check_box_total_duration);
            TimePeriod duration = new TimePeriod();


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
                this.totalTime.setText(TimePeriodFormat.simple(this.duration));
                this.countDown.setText(TimePeriodFormat.clock(untilNextAlarm));
                if (untilNextAlarm.getAsSeconds() == 1) {
                    voiceNotification();
                }
            }

            private TimePeriod getTimeUntilNextAlarm() {
                int alarmFrequencyInSeconds = alarmFrequencyInMinutes * 60; // change this for faster testing.
                int timeSinceLastAlarm = this.duration.getAsSeconds() % alarmFrequencyInSeconds;
                return new TimePeriod(alarmFrequencyInSeconds - timeSinceLastAlarm);
            }

            public void voiceNotification() {
                if (sayCurrentTimeCheckBox.isChecked()) {
                    voice.appendCurrentTimeToQueue();
                }
                voice.appendPauseToQueue();
                if (sayTotalTimeCheckBox.isChecked()) {
                    voice.appendTotalTimeToQueue(this.duration);
                }
                voice.sayQueue();
            }
        };
        clock.scheduleAtFixedRate(task, 0, 1000);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        final int NOTIFICATION_ID = 1;
        StillRunningBackgroundNotification backgroundNotification = new StillRunningBackgroundNotification(
                NOTIFICATION_ID,
                this,
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE)
        );
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus == false) {
            // TODO if app is open, and we open the notification drawer, it shouldn't push a notification.
            backgroundNotification.show();
        } else {
            backgroundNotification.hide();
        }
    }
    // TODO -->NEXT<-- add start button
    // TODO pick a start time or start now.
}