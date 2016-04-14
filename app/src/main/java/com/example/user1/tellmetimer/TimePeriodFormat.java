package com.example.user1.tellmetimer;

public final class TimePeriodFormat {

  public static String simple(TimePeriod duration) {
    int minutes = duration.getMinutes();
    int seconds = duration.getSeconds();
    return simpleMinutesToString(minutes) + " " + simpleSecondsToString(seconds);
  }

  private static String simpleMinutesToString(int minutes) {
    if (isThereAny(minutes)) {
      return getProperSubjectVerbAgreement(minutes, "minute", "minutes");
    }
    return "";
  }

  private static String simpleSecondsToString(int seconds) {
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
    int minutes = duration.getMinutes();
    int seconds = duration.getSeconds();
    return addLeadingZeroIfNeeded(minutes) + ":" + addLeadingZeroIfNeeded(seconds);
  }

  private static String addLeadingZeroIfNeeded(int integer) {
    String text = Integer.toString(integer);
    if (isSingleDigit(integer)) {
      return "0" + text;
    }
    return text;
  }

  private static boolean isSingleDigit(int number) {
    return number < 10;
  }
}
