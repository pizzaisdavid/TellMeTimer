package com.example.user1.tellmetimer;

import android.app.Fragment;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ClockCountUp extends Fragment {

  private Button startButton;
  private Button resetButton;
  private boolean isGoing;
  private StopWatch stopWatch;

  public ClockCountUp() {

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.clock_count_up, container, false);
    startButton = (Button) view.findViewById(R.id.start_button);
    resetButton = (Button) view.findViewById(R.id.reset_button);
    startButton.setText("test");
    //stopWatch = new StopWatch(getActivity()); //TODO get this working
    isGoing = false;
    resetButton.setVisibility(View.INVISIBLE);
    startButton.setOnClickListener(new View.OnClickListener() {

      public void onClick(View view) {
        isGoing = swapState(isGoing);
        if (isGoing) {
          //stopWatch.resume();
          startButton.setText("Pause");
          resetButton.setVisibility(View.INVISIBLE);
        } else {
          //stopWatch.pause();
          startButton.setText("Start");
          resetButton.setVisibility(View.VISIBLE);
        }
      }

      private boolean swapState(boolean state) {
        if (state) {
          return false;
        }
        return true;
      }
    });

    resetButton.setOnClickListener(new View.OnClickListener() {

      public void onClick(View view) {
        if (isGoing == false) {
          stopWatch.reset();
          resetButton.setVisibility(View.INVISIBLE);
        }
      }
    });

    return view;
  }

/*  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    StillRunningBackgroundNotification backgroundNotification = new StillRunningBackgroundNotification(this, notificationManager);
    super.onWindowFocusChanged(hasFocus);
    if (hasFocus == false && isGoing == true) {
      backgroundNotification.show();
    } else {
      backgroundNotification.hide();
    }
  }*/
}