package com.example.user1.tellmetimer;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.HashMap;
import java.util.Locale;

public class Voice implements TextToSpeech.OnInitListener {

  private TextToSpeech textToSpeech;
  private HashMap<String, String> map;

  public Voice(Activity activity) {
    this.textToSpeech = new TextToSpeech(activity, this);
    this.textToSpeech.setOnUtteranceProgressListener(new RequestAudioFocus(activity));
    this.map = getHashMap();
  }

  private HashMap getHashMap() {
    HashMap<String, String> map = new HashMap<>();
    map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "UniqueID");
    return map;
  }

  @Override
  public void onInit(int status) {
    if (status == TextToSpeech.SUCCESS) {
      int result = textToSpeech.setLanguage(Locale.US);
      if (result == TextToSpeech.LANG_MISSING_DATA
              || result == TextToSpeech.LANG_NOT_SUPPORTED) {
        Log.e("error", "This Language is not supported");
      }
    } else {
      Log.e("error", "Initialization Failed!");
    }
  }

  public void say(String message) {
    textToSpeech.speak(
            message,
            TextToSpeech.QUEUE_ADD,
            this.map
    );
  }
}
