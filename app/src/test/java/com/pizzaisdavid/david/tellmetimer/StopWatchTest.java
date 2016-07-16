package com.pizzaisdavid.david.tellmetimer;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class StopWatchTest {
  StopWatch stopWatch;
  StopWatchSchedule mockStopWatchSchedule;
  UpdateTask mockUpdateTask;

  @Before
  public void before() {
    mockStopWatchSchedule = Mockito.mock(StopWatchSchedule.class);
    mockUpdateTask = Mockito.mock(UpdateTask.class);
  }

  @Test
  public void constructor() throws Exception {
    stopWatch = new StopWatch(mockStopWatchSchedule, mockUpdateTask);
  }

  @Test
  public void setup() throws Exception {
    stopWatch = new StopWatch(mockStopWatchSchedule, mockUpdateTask);
    stopWatch.setup();
    Mockito.verify(mockUpdateTask).pause();
  }

  @Test
  public void resume() throws Exception {
    stopWatch = new StopWatch(mockStopWatchSchedule, mockUpdateTask);
    stopWatch.setup();
    stopWatch.resume();
    Mockito.verify(mockUpdateTask).resume();
  }

}