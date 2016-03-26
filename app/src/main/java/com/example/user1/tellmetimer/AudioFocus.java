package com.example.user1.tellmetimer;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;

public class AudioFocus implements AudioManager.OnAudioFocusChangeListener {

    public AudioManager audioManager;

    public AudioFocus(AudioManager audioManager) {
        this.audioManager = audioManager;
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN:
                break;
            case AudioManager.AUDIOFOCUS_LOSS:

                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:

                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                break;

        }
    }
}