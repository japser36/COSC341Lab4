package com.example.a42305169.cosc341lab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {
    private ArrayList<String[]> records;
    private int current = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        records = getRecords(getResources().getString(R.string.dataFile));
        if (records == null || records.isEmpty()) return; // something went wrong, no data found.
        printData(records.get(current));
    }

    public void nextRecord(View view) {
        current++;
        if (current > records.size()-1) current = 0;
        printData(records.get(current));
    }

    public void previousRecord(View view) {
        current--;
        if (current < 0) current = records.size()-1;
        printData(records.get(current));
    }

    public void backToMain(View view) {
        finish();
    }

    private void printData(String[] sa) {
        ((TextView) findViewById(R.id.textView7)).setText(sa[0]);
        ((TextView) findViewById(R.id.textView9)).setText(getResources().getString(R.string.namef,sa[2],sa[1]));
        ((TextView) findViewById(R.id.textView11)).setText(sa[3]);
        ((TextView) findViewById(R.id.textView13)).setText(sa[4]);
        ((TextView) findViewById(R.id.textView14)).setText(getResources().getString(R.string.progressf,(current+1),records.size()));
    }

    private ArrayList<String[]> getRecords(String filename) {
        ArrayList<String[]> a = new ArrayList<>();

        try {
            FileInputStream fis = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] entry = line.split(",");
                a.add(entry);
            }
            br.close();
            return a;
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "@string/read_error", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
