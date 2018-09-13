package com.example.amritansh.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_time)
    EditText timeText;
    @BindView(R.id.et_frequency)
    EditText frequencyText;

    private PendingIntent pendingIntent;
    private Intent intent;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


    }

    @OnClick(R.id.btn_start)
    protected void startAlarm() {
        String text = timeText.getText().toString();
        if (!text.matches("")) {
            int time = Integer.parseInt(text);

            Toast.makeText(this, "alarm is set to go after" + time + "seconds", Toast.LENGTH_SHORT).show();

            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() +
                    (time  * 1000), pendingIntent);
        } else {

            Log.d("alarm", "error enter text");
            Toast.makeText(this, "please enter the time period", Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.btn_stop)
    protected void stopAlarm() {
        if (alarmManager != null){
            stopService(new Intent(this, AlarmService.class));
            alarmManager.cancel(pendingIntent);
        }
    }

    @OnClick(R.id.btn_repeat)
    protected void repeatAlarm() {
        String text = timeText.getText().toString();
        String freq = frequencyText.getText().toString();

        if (!freq.matches("")) {
            int repeatFreq = Integer.parseInt(freq);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 19);
            calendar.set(Calendar.MINUTE, 32);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), repeatFreq, pendingIntent);
            Toast.makeText(this, "alarm will repeate every " + 40 + " seconds" , Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "please enter the time period and frequency", Toast.LENGTH_SHORT).show();
        }
    }

}
