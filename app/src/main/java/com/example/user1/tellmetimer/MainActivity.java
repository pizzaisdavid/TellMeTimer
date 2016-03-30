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
import android.view.View;
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

    private Voice voice;
    private int alarmFrequencyInMinutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // TODO add countdown alarm option
        // -- Five minutes remaining, three minutes remaining...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        voice = new Voice(this, (AudioManager) getSystemService(Context.AUDIO_SERVICE));
        // Chronometer m = (Chronometer) findViewById(R.id.chronometer); TODO maybe switch to chrono
        // m.start();

        sendNotification();

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
                if (untilNextAlarm.getSeconds() == 1) {
                    voiceNotification();
                }
            }

            private TimePeriod getTimeUntilNextAlarm() {
                int alarmFrequencyInSeconds = alarmFrequencyInMinutes * 60; // change this for faster testing.
                int timeSinceLastAlarm = this.duration.getSeconds() % alarmFrequencyInSeconds;
                return new TimePeriod(alarmFrequencyInSeconds - timeSinceLastAlarm);
            }

            public void voiceNotification() {
                DateFormat dateFormat = new SimpleDateFormat("h:mm a");
                boolean isCurrentTimeBoxChecked = sayCurrentTimeCheckBox.isChecked();
                boolean isTotalTimeBoxChecked = sayTotalTimeCheckBox.isChecked();

                String message = "";

                if (isCurrentTimeBoxChecked) {
                    message += " It is currently " + dateFormat.format(new Date()) + ".";
                }
                message += "........"; // Hack-y attempt to take a breath between sentences.
                if (isTotalTimeBoxChecked) {
                    message += " This timer has been running for " + TimePeriodFormat.simple(this.duration);
                }
                voice.say(message);
            }
        };
        clock.scheduleAtFixedRate(task, 0, 1000);
    }

    public void sendNotification() {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("BasicNotifications Sample");
        builder.setContentText("Time to learn about notifications!");
        builder.setSubText("Tap to view documentation about notifications.");
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }
    // TODO add start button
    // TODO pick a start time or start now.
    // TODO on app minimize, show some kind of notification that it is still running in the background.
}