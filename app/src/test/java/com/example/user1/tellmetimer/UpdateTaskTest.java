package com.example.user1.tellmetimer;

import android.app.Activity;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        UpdateTaskTest.pause.class
})

public class UpdateTaskTest extends TestCase {


  public static class pause {

    @Test
    public void doNotUpdate() throws Exception {
      UpdateTask task = new UpdateTask(null); // TODO Make mock activity
                                              // Method findViewById in android.app.Activity not mocked.
      Assert.assertEquals(0, task.duration.getAsSeconds());
      task.update();
      Assert.assertEquals(1, task.duration.getAsSeconds());
      task.pause();
      task.update();
      Assert.assertEquals(1, task.duration.getAsSeconds());
    }
  }
}