package com.example.amritansh.smsapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CODE = 21;
    private EditText phoneNo;
    private EditText messageText;
    private String number = "";
    private String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkForPermission();
        phoneNo = findViewById(R.id.contact);
        messageText = findViewById(R.id.message);

        number = phoneNo.getText().toString();
        message = messageText.getText().toString();
    }


    private void checkForPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
            + ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            != PackageManager
                    .PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .SEND_SMS) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                Toast.makeText(this, "grant permissions to enable messaging and calling features", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS, Manifest.permission.CALL_PHONE},
                        PERMISSIONS_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length > 0) {

                boolean callPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                boolean smsPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                if (callPermission && smsPermission) {
                    Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.SEND_SMS, Manifest.permission.CALL_PHONE},
                            PERMISSIONS_REQUEST_CODE);
                }
            }

        }
    }

    // send message using SmsManager
    public void sendMessage(View view) {

        if (!message.matches("") && !number.matches("")) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, message, null, null);

            Toast.makeText(this, "Message Sent successfully!",
                    Toast.LENGTH_LONG).show();
        } else {
            if (number.matches("")) {
                Toast.makeText(this, "enter validNumber", Toast.LENGTH_SHORT).show();
            }
            if (message.matches("")) {
                Toast.makeText(this, "empty message body", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void makeCall(View view) {

        if (!number.matches("")) {
            String tel = "tel:" + number;
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(tel));
            startActivity(callIntent);
        } else {
            Toast.makeText(this, "enter validNumber", Toast.LENGTH_SHORT).show();
        }
    }

}
