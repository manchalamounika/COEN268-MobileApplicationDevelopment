package com.mmanchala.coen268.sqlitesaveuserdata;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper db;
    Button addBtn, viewBtn, updateBtn, deleteBtn;
    EditText name, tvShow, email, id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBaseHelper(this);

        name = (EditText) findViewById(R.id.editTextName);
        email = (EditText) findViewById(R.id.editTextEmail);
        tvShow = (EditText) findViewById(R.id.editTextShow);
        id = (EditText) findViewById(R.id.txtID);

        addBtn = findViewById(R.id.addButton);
        viewBtn = findViewById(R.id.viewButton);
        updateBtn = findViewById(R.id.updateButton);
        deleteBtn = findViewById(R.id.deleteButton);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("") || email.getText().toString().equals("") ||
                        tvShow.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please enter all data", Toast.LENGTH_LONG).show();
                }
                else{
                    boolean result = db.addData(name.getText().toString(), email.getText().toString(), tvShow.getText().toString());
                    if(result == true) {
                        Toast.makeText(MainActivity.this,"Data Inserted Successfully!", Toast.LENGTH_LONG).show();
                    }
                    name.setText(null);
                    email.setText(null);
                    tvShow.setText(null);
                    id.setText(null);
                }

            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = db.retriveData();
                if(cursor.getCount() == 0) {
                    showData("Data", "No Data Found!");
                }
                else{
                    StringBuffer sb = new StringBuffer();
                    while(cursor.moveToNext()) {
                        sb.append("ID: " + cursor.getString(0) + "\n");
                        sb.append("Name: " + cursor.getString(1) + "\n");
                        sb.append("Email: " + cursor.getString(2) + "\n");
                        sb.append("Favorite TV Show: " + cursor.getString(3) + "\n");
                        sb.append("\n");
                    }
                    showData("Stored Data",sb.toString());
                }
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getText().toString().equals("") || name.getText().toString().equals("") ||
                        email.getText().toString().equals("") ||
                        tvShow.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this,"Please enter all the data and "+
                            "ID to update the values!", Toast.LENGTH_LONG).show();
                }
                else{
                    boolean result = db.updateData(id.getText().toString(),name.getText().toString(), email.getText().toString(), tvShow.getText().toString());
                    if(result == true) {
                        Toast.makeText(MainActivity.this,"Data Updated Successfully!", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(MainActivity.this,"Data NOT Updated!", Toast.LENGTH_LONG).show();
                    name.setText(null);
                    email.setText(null);
                    tvShow.setText(null);
                    id.setText(null);
                }

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DELETE", "Delete functionality entered");
                if(id.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this,"ID Field is NULL!", Toast.LENGTH_LONG).show();
                }
                else{
                    Integer result = db.deleteData(id.getText().toString());
                    if(result > 0) {
                        Toast.makeText(MainActivity.this,"Data Deleted Successfully!", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(MainActivity.this,"Data NOT Deleted!", Toast.LENGTH_LONG).show();
                    id.setText(null);
                }

            }
        });
    }

    public void showData(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
