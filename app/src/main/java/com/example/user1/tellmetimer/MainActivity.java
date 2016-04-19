package com.example.user1.tellmetimer;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    //TODO app is unresponsive for a few seconds after start. Started after adding tabs.
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    FragmentTransaction ft = getFragmentManager().beginTransaction();
    ft.replace(R.id.fragment_container, new ClockCountUp());
    ft.commit();
      // TODO back button should remember tabs
  }
  // TODO pick a start time or start now.
}