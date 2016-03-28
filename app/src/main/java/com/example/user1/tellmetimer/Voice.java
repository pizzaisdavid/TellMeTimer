package com.example.user1.tellmetimer;

import android.content.Context;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import java.util.HashMap;
import java.util.Locale;

public class Voice implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;
    private AudioManager am;
    private HashMap<String, String> map;

    public Voice(Context context, AudioManager am) {
        this.textToSpeech = new TextToSpeech(context, this);
        this.am = am;
        this.textToSpeech.setOnUtteranceProgressListener(requestAudioFocus);
        this.map = new HashMap<String, String>();
        this.map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "UniqueID");
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
            Log.e("error", "Initilization Failed!");
        }
    }


    UtteranceProgressListener requestAudioFocus = new UtteranceProgressListener() {
        // TODO put this in its own class
        // - TODO create isAllowedToTalk, which is checked before running "say()."

        @Override
        public void onStart(String utteranceId) {
            int result = am.requestAudioFocus(afChangeListener,
                    AudioManager.STREAM_NOTIFICATION,
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        }

        @Override
        public void onDone(String utteranceId) {
            am.abandonAudioFocus(afChangeListener);
        }

        @Override
        public void onError(String utteranceId) {

        }
    };

    public void say(String message) {
        textToSpeech.speak(
                message,
                TextToSpeech.QUEUE_ADD,
                this.map
        );
    }

    public void takeBreath() {
        say(".....");
    }

    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                am.abandonAudioFocus(afChangeListener);
            }
        }
    };
}
