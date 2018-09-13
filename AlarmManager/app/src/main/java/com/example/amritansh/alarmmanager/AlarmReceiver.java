package com.example.amritansh.alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "alarm set off!", Toast.LENGTH_SHORT).show();
//
//        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.sound);
//        mediaPlayer.start();


        Intent intent1 = new Intent(context, AlarmService.class);
        context.startService(intent1);
    }
}
