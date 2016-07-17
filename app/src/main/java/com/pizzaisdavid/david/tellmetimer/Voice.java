package com.pizzaisdavid.david.tellmetimer;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Locale;

public class Voice {
  private static final Logger logger = LoggerFactory.getLogger(Voice.class);
  private TextToSpeech textToSpeech;
  private HashMap<String, String> map;

  public Voice(TextToSpeech textToSpeech) {
    logger.info("Initializing");
    this.textToSpeech = textToSpeech;
    map = getHashMap();
  }

  private HashMap getHashMap() {
    HashMap<String, String> map = new HashMap<>();
    map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "UniqueID");
    return map;
  }

  public void say(String message) {
    logger.info("say: {}", message);
    textToSpeech.speak(message, TextToSpeech.QUEUE_ADD, map);
  }
}
