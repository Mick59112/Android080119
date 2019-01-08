package com.aston.cdnt4.activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aston.cdnt4.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();
    private static final int REQUEST_PERMISSIONS = 123;
    private EditText myEditText, mPhoneNumber;
    private Button myButton, mCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myEditText = findViewById(R.id.editText);
        myButton = findViewById(R.id.button);
        mPhoneNumber = findViewById(R.id.main_phone);
        mCall = findViewById(R.id.main_call);
        myButton.setOnClickListener(this);
        mCall.setOnClickListener(this);
        SharedPreferences sp = getSharedPreferences("PREFS", MODE_PRIVATE);
        myEditText.setText(sp.getString("firstName", ""));
    }

    @Override
    public void onClick(View v) {
        //Log.d(TAG, "onClick: " + content); affiche le content dans les logs
        //Toast.makeText(getApplicationContext(),content, Toast.LENGTH_LONG).show(); info bulle
        if (v.getId() == myButton.getId()) {
            String content = myEditText.getText().toString();

            if (content.length() > 0) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("firstName", content);
                startActivity(intent);
            }
        } else if (v.getId() == mCall.getId()) {
            String phoneNumber = mPhoneNumber.getText().toString();

            if (phoneNumber.length() > 0) {
                Intent intentPhone = new Intent(Intent.ACTION_CALL);
                intentPhone.setData(Uri.parse("tel: " + phoneNumber));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    String[] Permissions = {Manifest.permission.CALL_PHONE};
                    ActivityCompat.requestPermissions(this, Permissions, REQUEST_PERMISSIONS);
                    return;
                }
                startActivity(intentPhone);
            }
        }
        //Toast.makeText(getApplicationContext(), "Le champ est vide", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mCall.callOnClick();
        }
    }
}