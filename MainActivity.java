package com.example.a42305169.cosc341lab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void readData(View view) {
        try {
            FileInputStream f = openFileInput(getResources().getString(R.string.dataFile));
            Intent i;
            if (f != null) {
                f.close();
                i = new Intent(this, ReadActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.writeFirst), Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeData(View view) {
        Intent i = new Intent(this, WriteActivity.class);
        startActivity(i);
    }
}
