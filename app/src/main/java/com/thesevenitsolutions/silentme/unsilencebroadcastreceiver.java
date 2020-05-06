package com.thesevenitsolutions.silentme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;

public class unsilencebroadcastreceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        AudioManager audio = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

        Log.d("search","normal");
    }
}
