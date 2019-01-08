package com.aston.cdnt4.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import com.aston.cdnt4.R;

import adapters.MyAdapter;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // recuepere l'id de monTextView
        TextView myTextView = findViewById(R.id.monTextView);
        Intent intent = getIntent();
        String fromMainActivity = intent.getStringExtra("firstName");
        myTextView.setText("Bonjour " + fromMainActivity);
        SharedPreferences sp = getSharedPreferences("PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("firstName", fromMainActivity);
        editor.apply();
        // recupere l'id de listview dans la vue activity_second
        ListView listView = findViewById(R.id.second_listView);
        MyAdapter myAdapter = new MyAdapter(this,getLayoutInflater());
        listView.setAdapter(myAdapter);

    }
}
