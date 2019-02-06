package com.mmanchala.coen268.wikiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> stringArrayList = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] strings = {"IBM", "eBay", "Apple", "Google", "Amazon","Yahoo"};

        ListView objlist = findViewById(R.id.list_item);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringArrayList);
        objlist.setAdapter(arrayAdapter);
        for (String s: strings) {
            stringArrayList.add(s);
            Log.i("MainActivity123","String added to arraylist--"+s);
        }

        objlist.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("MainActivity123","Clicked String--"+stringArrayList.get(position));
                startActivity(new Intent(getApplicationContext(), InfoActivity.class).
                        putExtra("URL","https://en.wikipedia.org/wiki/"+
                                stringArrayList.get(position)));
            }
        });
    }
}
