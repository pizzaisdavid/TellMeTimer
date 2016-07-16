package com.pizzaisdavid.david.tellmetimer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AlarmFrequencyFormat {
  private static final Logger logger = LoggerFactory.getLogger(AlarmFrequencyFormat.class);

  public static String format(int count) {
    final String base = "Sound the alarm every ";
    String message = "";
    if (count == 1) {
      message = base + "minute.";
    }
    message = base + count + " minutes.";
    logger.info("Message is formatted as \"{}\"", message);
    return message;
  }
}
