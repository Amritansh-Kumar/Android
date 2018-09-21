package com.example.amritansh.contentproviderdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.amritansh.contentproviderdemo.Fragments.ContactsFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout container;
    private Fragment contactsFrag;
    private static final int PERMISSIONS_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPermission();

        container = findViewById(R.id.container);
        contactsFrag = new ContactsFragment();
        transaction();
    }

    private void transaction() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.container, contactsFrag);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    private void getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .READ_CONTACTS)) {
                Toast.makeText(this, "grant permissions to access contacts",
                        Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    transaction();
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_CODE);
                }
            }
        }
    }
}
