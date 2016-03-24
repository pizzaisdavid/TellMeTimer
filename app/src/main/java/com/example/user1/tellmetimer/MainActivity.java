package com.example.user1.tellmetimer;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timer clock = new Timer();
        // clock.onSecondTick(task);
        TimerTask task = new TimerTask() {
            TextView totalTime = (TextView) findViewById(R.id.total_time);
            TextView notificationCountDown = (TextView) findViewById(R.id.notification_count_down);
            final int ALARM_FREQUENCY_IN_SECONDS =  30;

            int seconds = 0;
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int durationSinceLastAlarm = seconds % ALARM_FREQUENCY_IN_SECONDS;
                        int durationUntilNextAlarm = ALARM_FREQUENCY_IN_SECONDS - durationSinceLastAlarm;
                        totalTime.setText(seconds + "");
                        notificationCountDown.setText(durationUntilNextAlarm + "");
                        seconds++;
                        if (durationUntilNextAlarm == 0) {
                            //speak alarm
                            soundAlarm();
                        }

                    }
                });
            }
        };

        clock.scheduleAtFixedRate(task,0, 1000);
    }

    public void soundAlarm() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}