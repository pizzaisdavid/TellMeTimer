package com.example.user1.tellmetimer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
  TabHost host;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    host = (TabHost)findViewById(R.id.tabHost);
    host.setup();

    //Tab 1
    TabHost.TabSpec spec = host.newTabSpec("Tab One");
    spec.setContent(R.id.tab1);
    spec.setIndicator("Tab One");
    host.addTab(spec);

    //Tab 2
    spec = host.newTabSpec("Tab Two");
    spec.setContent(R.id.tab2);
    spec.setIndicator("Tab Two");
    host.addTab(spec);
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
}