package com.example.user1.tellmetimer;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        DurationTest.initializer.class,
        DurationTest.tick.class
})

public class DurationTest extends TestCase {

    public static class initializer {

        @Test
        public void doubleZero() throws Exception {
            //Assert.assertEquals(40, 9);
        }

        @Test
        public void singleDigitSeconds() throws Exception {
            //Assert.assertEquals(9, 9);
        }
    }

    public static class tick {

        @Test
        public void runOnce() throws Exception {
            Duration duration = new Duration(0, 20);
            duration.tick();
            Assert.assertEquals(0, duration.minutes);
            Assert.assertEquals(21, duration.seconds);
        }

        @Test
        public void convertToMinute() throws Exception {
            Duration duration = new Duration(0, 59);
            duration.tick();
            Assert.assertEquals(1, duration.minutes);
            Assert.assertEquals(0, duration.seconds);
        }
    }
}