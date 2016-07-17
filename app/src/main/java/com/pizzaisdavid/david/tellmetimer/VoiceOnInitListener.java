package com.pizzaisdavid.david.tellmetimer;

import android.speech.tts.TextToSpeech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoiceOnInitListener implements TextToSpeech.OnInitListener {
  private static final Logger logger = LoggerFactory.getLogger(VoiceOnInitListener.class);

  public VoiceOnInitListener() {
    logger.info("Initializing");
  }

  @Override
  public void onInit(int status) {
    if (status == TextToSpeech.SUCCESS) {
    } else {
      logger.error("Initialization Failed!");
    }
  }

}
