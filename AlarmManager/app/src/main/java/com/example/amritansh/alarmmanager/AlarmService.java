package com.example.amritansh.alarmmanager;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class AlarmService extends Service {

    private MediaPlayer mediaPlayer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
        mediaPlayer.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
