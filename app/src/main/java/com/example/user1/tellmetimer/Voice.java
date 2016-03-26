package com.example.user1.tellmetimer;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class Voice implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;

    public Voice(Context context) {
        textToSpeech = new TextToSpeech(context, this);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("error", "This Language is not supported");
            } else {
            }
        } else {
            Log.e("error", "Initilization Failed!");
        }
    }

    public void say(String message) {
        textToSpeech.speak(
                message,
                TextToSpeech.QUEUE_ADD,
                null
        );
    }

    public void sayNothing(long durationInMilliseconds) {
        textToSpeech.playSilence(
                durationInMilliseconds,
                TextToSpeech.QUEUE_ADD,
                null
        );
    }
}
