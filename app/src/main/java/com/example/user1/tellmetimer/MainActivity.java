package com.example.user1.tellmetimer;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
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
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Timer clock = new Timer();
        TimerTask task = new TimerTask() {
            TextView totalTime = (TextView) findViewById(R.id.total_time);
            TextView notificationCountDown = (TextView) findViewById(R.id.notification_count_down);
            CheckBox sayCurrentTimeCheckBox = (CheckBox) findViewById(R.id.check_box_current_time);
            CheckBox sayTotalTimeCheckBox = (CheckBox) findViewById(R.id.check_box_total_duration);
            final int ALARM_FREQUENCY_IN_SECONDS = 30;
            Duration duration = new Duration();

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
                int seconds = duration.seconds + (duration.minutes * 60);
                int durationSinceLastAlarm = seconds % ALARM_FREQUENCY_IN_SECONDS;
                int durationUntilNextAlarm = ALARM_FREQUENCY_IN_SECONDS - durationSinceLastAlarm;
                totalTime.setText(duration.toString());
                notificationCountDown.setText(durationUntilNextAlarm + "");
                duration.tick();
                if (seconds % ALARM_FREQUENCY_IN_SECONDS == 0) {
                    sayTheRightThings();
                }
            }

            public void sayTheRightThings() {
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
                    voice.say("This timer has been running for " +  duration.toString());
                }
            }
        };
        clock.scheduleAtFixedRate(task, 0, 1000);
    }
}