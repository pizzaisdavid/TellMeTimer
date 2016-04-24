package com.example.user1.tellmetimer;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.LayoutParams;

// TODO make a toast when volume is muted to notify.

public class MainActivity extends Activity {

  private static final int CONTENT_VIEW_ID = 10101010;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    //TODO app is unresponsive for a few seconds after start. Started after adding tabs.
    super.onCreate(savedInstanceState);
    FrameLayout frame = new FrameLayout(this);
    setContentView(frame, new LayoutParams(
            LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

    if (savedInstanceState == null) {
      Fragment newFragment = new ClockCountUp();
      FragmentTransaction ft = getFragmentManager().beginTransaction();
      ft.add(android.R.id.content, newFragment).commit();
    }
  }
  // TODO pick a start time or start now.
}