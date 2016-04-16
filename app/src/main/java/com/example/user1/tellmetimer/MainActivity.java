package com.example.user1.tellmetimer;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
  TabHost host;
  private Button startButton;
  private Button resetButton;
  private boolean isGoing;
  private StopWatch stopWatch;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    //TODO app is unresponsive for a few seconds after start. Started after adding tabs.
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    host = (TabHost)findViewById(R.id.tabHost);
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

    startButton = (Button) findViewById(R.id.start_button);
    resetButton = (Button) findViewById(R.id.reset_button);
    stopWatch = new StopWatch(MainActivity.this);
    isGoing = false;
    resetButton.setVisibility(View.INVISIBLE);

    startButton.setOnClickListener(new View.OnClickListener() {

      // TODO back button should remember tabs

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
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    StillRunningBackgroundNotification backgroundNotification = new StillRunningBackgroundNotification(this, notificationManager);
    super.onWindowFocusChanged(hasFocus);
    if (hasFocus == false && isGoing == true) {
      // TODO NEXT-ISH if paused, don't make notification.
      backgroundNotification.show();
    } else {
      backgroundNotification.hide();
    }
  }
  // TODO pick a start time or start now.
}