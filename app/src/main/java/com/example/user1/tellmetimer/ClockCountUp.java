package com.example.user1.tellmetimer;

import android.app.Activity;
import android.app.Fragment;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ClockCountUp extends Fragment {

  private Activity activity;
  private Button startButton;
  private Button resetButton;
  private boolean isGoing;
  private StopWatch stopWatch;

  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    // TODO something about running onAttach or something
    View view = inflater.inflate(R.layout.clock_count_up, container, false);
    startButton = (Button) activity.findViewById(R.id.start_button);
    resetButton = (Button) activity.findViewById(R.id.reset_button);
    stopWatch = new StopWatch(activity);
    isGoing = false;
    resetButton.setVisibility(View.INVISIBLE);

    startButton.setOnClickListener(new View.OnClickListener() {

      public void onClick(View view) {
        isGoing = swapState(isGoing);
        if (isGoing) {
          stopWatch.resume();
          startButton.setText("Pause");
          resetButton.setVisibility(View.INVISIBLE);
        } else {
          stopWatch.pause();
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

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    this.activity = getActivity();
  }

/*  @Override
  public void onResume() {
    NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
    StillRunningBackgroundNotification backgroundNotification = new StillRunningBackgroundNotification(getContext(), notificationManager);
    if (isGoing == true) {
      // TODO NEXT-ISH if paused, don't make notification.
      backgroundNotification.show();
    } else {
      backgroundNotification.hide();
    }
  }*/
}