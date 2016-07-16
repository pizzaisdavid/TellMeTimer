package com.pizzaisdavid.david.tellmetimer;

import junit.framework.Assert;

import org.junit.Test;

public class TimePeriodTest {

  @Test
  public void initialize_noArgument() throws Exception {
    TimePeriod duration = new TimePeriod();
    Assert.assertEquals(0, duration.getSeconds());
    Assert.assertEquals(0, duration.getMinutes());
    Assert.assertEquals(0, duration.getAsSeconds());
  }

  @Test
  public void tick() throws Exception {
    TimePeriod duration = new TimePeriod(20);
    duration.tick();
    Assert.assertEquals(21, duration.getSeconds());
  }
}