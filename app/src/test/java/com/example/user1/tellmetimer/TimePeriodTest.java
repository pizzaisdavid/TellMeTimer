package com.example.user1.tellmetimer;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        TimePeriodTest.initializer.class,
        TimePeriodTest.tick.class
})

public class TimePeriodTest extends TestCase {

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
        public void run() throws Exception {
            TimePeriod duration = new TimePeriod(20);
            duration.tick();
            Assert.assertEquals(21, duration.getSeconds());
        }

    }
}