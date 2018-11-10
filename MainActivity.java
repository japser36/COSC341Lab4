package com.example.a42305169.cosc341lab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void readData(View view) {
        File f = new File("@string/dataFile");
        Intent i = null;
        if (f.exists() && !f.isDirectory()) {
            i = new Intent(this, ReadActivity.class);
        } else {
            Toast.makeText(getApplicationContext(), "@string/writeFirst", Toast.LENGTH_SHORT).show();
        }
        startActivity(i);
    }

    public void writeData(View view) {
        Intent i = new Intent(this, WriteActivity.class);
        startActivity(i);
    }
}
