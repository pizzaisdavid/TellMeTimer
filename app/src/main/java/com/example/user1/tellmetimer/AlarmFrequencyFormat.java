package com.example.user1.tellmetimer;


public final class AlarmFrequencyFormat {

    public static String format(int count) {
        final String base = "Sound the alarm every ";
        if (count == 1) {
            return base + "minute.";
        }
        return base + count + " minutes.";
    }
}
