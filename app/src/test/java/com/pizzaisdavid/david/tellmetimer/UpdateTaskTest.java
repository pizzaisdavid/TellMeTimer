package com.pizzaisdavid.david.tellmetimer;

import android.app.Activity;
import android.speech.tts.TextToSpeech;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.w3c.dom.Text;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class UpdateTaskTest {
  Activity mockActivity;
  TextToSpeech mockTextToSpeech;
  AlarmFrequencyListener mockAlarmFrequencyListener;

  @Before
  public void before() {
    mockActivity = Mockito.mock(Activity.class);
    mockTextToSpeech = Mockito.mock(TextToSpeech.class);
    mockAlarmFrequencyListener = Mockito.mock(AlarmFrequencyListener.class);
  }

  @Test
  public void update() throws Exception {
    UpdateTask task = new UpdateTask(mockActivity, mockTextToSpeech, mockAlarmFrequencyListener); // TODO untangle dependencies or something
    Assert.assertEquals(0, task.duration.getAsSeconds());
    task.update();
    Assert.assertEquals(1, task.duration.getAsSeconds());
  }

  @Test
  public void doNotUpdate() throws Exception {
    UpdateTask task = new UpdateTask(mockActivity, mockTextToSpeech, mockAlarmFrequencyListener);
    Assert.assertEquals(0, task.duration.getAsSeconds());
    task.pause();
    task.update();
    Assert.assertEquals(0, task.duration.getAsSeconds());
  }
}