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
        DurationTest.formatter.class
})

public class DurationTest extends TestCase {

    public static class initializer {

        @Test
        public void doubleZero() throws Exception {
            Assert.assertEquals(40, 9);
        }

        @Test
        public void singleDigitSeconds() throws Exception {
            Assert.assertEquals(9, 9);
        }

    }

    public static class formatter {

        @Test
        public void GetFormattedForDisplay_() throws Exception {
            Assert.assertEquals(":09", "5");
        }
    }
}