package com.pizzaisdavid.david.tellmetimer;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class StopWatchTest {
  StopWatchSchedule mockStopWatchSchedule;
  UpdateTask mockUpdateTask;

  @Before
  public void before() {
    mockStopWatchSchedule = Mockito.mock(StopWatchSchedule.class);
    mockUpdateTask = Mockito.mock(UpdateTask.class);
  }

  @Test
  public void constructor() throws Exception {
    StopWatch stopWatch = new StopWatch(mockStopWatchSchedule, mockUpdateTask);
  }
}