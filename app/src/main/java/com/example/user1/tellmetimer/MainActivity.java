package com.example.user1.tellmetimer;

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

/*        new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                TextView totalTime = (TextView) findViewById(R.id.total_time);
                TextView notificationCountDown = (TextView) findViewById(R.id.notification_count_down);
                notificationCountDown.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                TextView notificationCountDown = (TextView) findViewById(R.id.notification_count_down);
                notificationCountDown.setText("done!");
            }
        }.start();*/

        Timer clock = new Timer();
        TimerTask task = new TimerTask() {
            TextView totalTime = (TextView) findViewById(R.id.total_time);
            TextView notificationCountDown = (TextView) findViewById(R.id.notification_count_down);
            int alarmTimeFrequencyInSeconds =  60 * 5;

            int seconds = 0;
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int durationUntilNextAlarm = alarmTimeFrequencyInSeconds - seconds;
                        totalTime.setText(seconds + "");
                        notificationCountDown.setText(durationUntilNextAlarm + "");
                        seconds++;
                    }
                });
            }
        };

        clock.scheduleAtFixedRate(task,0, 1000);
    }
}