package com.aston.cdnt4.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aston.cdnt4.R;
import com.orhanobut.hawk.Hawk;

import adapters.MyAdapter;
import common.Constants;

public class SecondActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // recuepere l'id de monTextView
        TextView myTextView = findViewById(R.id.monTextView);
        Intent intent = getIntent();
        String fromMainActivity = intent.getStringExtra(Constants.FIRST_NAME);
        myTextView.setText(getString(R.string.hello_name,fromMainActivity));
        Hawk.put(Constants.FIRST_NAME, fromMainActivity);

        // recupere l'id de listview
        ListView listView = findViewById(R.id.second_listView);
        final MyAdapter myAdapter = new MyAdapter(this, getLayoutInflater());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        View empty = findViewById(R.id.second_listView_empty);
        listView.setEmptyView(empty);

        // recupere l'id du bouton populate
       findViewById(R.id.second_populate).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               myAdapter.setCount(100);
               myAdapter.notifyDataSetChanged();
           }
       });
    }

    // Affiche un toas lors d'un click sur un element de la liste
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Position :" + position, Toast.LENGTH_SHORT).show();
    }

    // Affiche une alerte/infobulle lors d'un clique long sur un element de la liste
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
        builder.setTitle("Liste cliquée")
                .setMessage("Ligne " + position)
                .setPositiveButton("OK", null);
        builder.create().show();
        // true n'affiche pas le toast lors d'un long click
        return false;
    }
}
