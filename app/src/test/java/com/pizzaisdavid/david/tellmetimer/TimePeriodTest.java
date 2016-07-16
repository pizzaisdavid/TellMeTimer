package com.pizzaisdavid.david.tellmetimer;

import junit.framework.Assert;

import org.junit.Test;

public class TimePeriodTest {

  @Test
  public void run() throws Exception {
    TimePeriod duration = new TimePeriod(20);
    duration.tick();
    Assert.assertEquals(21, duration.getSeconds());
  }
}