package com.example.user1.tellmetimer;

import android.app.Activity;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

/*@Suite.SuiteClasses({
        UpdateTaskTest.pause.class
})*/

public class UpdateTaskTest extends TestCase {

    @Test
    public void doNotUpdate() throws Exception {
      Activity mockActivity = Mockito.mock(Activity.class);
      UpdateTask task = new UpdateTask(mockActivity); // TODO untangle dependencies or something
      Assert.assertEquals(0, task.duration.getAsSeconds());
      task.update();
      Assert.assertEquals(1, task.duration.getAsSeconds());
      task.pause();
      task.update();
      Assert.assertEquals(1, task.duration.getAsSeconds());
    }
}