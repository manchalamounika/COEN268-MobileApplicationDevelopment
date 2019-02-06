package com.mmanchala.coen268.scrollablelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addButton;
    ListView list;
    EditText text;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.add);
        list = findViewById(R.id.list_item);
        text = findViewById(R.id.text);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        list.setAdapter(adapter);
        arrayList.add("Mary");
        arrayList.add("Jenny");

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text.getText().toString().trim().length() == 0 )
                    return;
                adapter.add(text.getText().toString());
                adapter.notifyDataSetChanged();
                text.setText("");
            }
        });

    }
}
