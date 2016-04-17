package com.example.user1.tellmetimer;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
  TabHost host;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    //TODO app is unresponsive for a few seconds after start. Started after adding tabs.
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    host = (TabHost) findViewById(R.id.tabHost);
    host.setup();

    //Tab 1
    TabHost.TabSpec spec = host.newTabSpec("Count Up");
    spec.setContent(R.id.tab1);
    spec.setIndicator("Count Up");
    host.addTab(spec);

    //Tab 2
    spec = host.newTabSpec("Count Down");
    spec.setContent(R.id.tab2);
    spec.setIndicator("Count Down");
    host.addTab(spec);
  }
  // TODO pick a start time or start now.
}