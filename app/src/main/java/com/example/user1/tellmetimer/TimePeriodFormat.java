package com.example.user1.tellmetimer;

public final class TimePeriodFormat {

    public static String simple(TimePeriod duration) {
        int minutes = duration.getSeconds() / 60;
        int seconds = duration.getSeconds() % 60;
        return minutesToString(minutes) + " " + secondsToString(seconds);
    }

    private static String minutesToString(int minutes) {
        if (isThereAny(minutes)) {
            return getProperSubjectVerbAgreement(minutes, "minute", "minutes");
        }
        return "";
    }

    private static String secondsToString(int seconds) {
        if (isThereAny(seconds)) {
            return getProperSubjectVerbAgreement(seconds, "second", "seconds");
        }
        return "";
    }

    private static boolean isThereAny(int count) {
        return count != 0;
    }

    private static String getProperSubjectVerbAgreement(int count, String singular, String plural) {
        if (count == 1) {
            return "1 " + singular;
        }
        return count + " " + plural;
    }

    public static String clock(TimePeriod duration) {
        // TODO make smaller breakup
        int minutes = duration.getSeconds() / 60;
        int seconds = duration.getSeconds() % 60;
        String message = "";
        if (minutes == 0) {
            message += "00";
        } else {
            message += minutes;
        }
        message += ":";
        if (seconds < 10) {
            message += "0" + seconds;
        } else {
            message += seconds;
        }
        return message;
    }
}
