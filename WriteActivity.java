package com.example.a42305169.cosc341lab4;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileOutputStream;

public class WriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.divisions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onSubmit(View view) {
        String filename = getResources().getString(R.string.dataFile);
        String fileContents = getData();
        if (fileContents.equals("")) return; // dont continue if there was an error
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
            finish();
        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), "@string/write_error", Toast.LENGTH_SHORT).show();
        }
    }

    private String getData() {
        String snum = ((EditText) findViewById(R.id.editText)).getText().toString();
        if (snum.length() != 8) {
            Toast.makeText(getApplicationContext(), "@string/snum_error", Toast.LENGTH_SHORT).show();
            return "";
        }
        String fname = ((EditText) findViewById(R.id.editText3)).getText().toString();
        String lname = ((EditText) findViewById(R.id.editText2)).getText().toString();
        if (lname.equals("") && fname.equals("")) {
            Toast.makeText(getApplicationContext(), "@string/name_error", Toast.LENGTH_SHORT).show();
            return "";
        }
        if (fname.equals("")) {
            Toast.makeText(getApplicationContext(), "@string/fname_error", Toast.LENGTH_SHORT).show();
            return "";
        }
        if (lname.equals("")) {
            Toast.makeText(getApplicationContext(), "@string/lname_error", Toast.LENGTH_SHORT).show();
            return "";
        }
        String gender = "Other";
        RadioGroup rg = findViewById(R.id.radioGroup);
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.radioButton:
                //male
                gender = "Male";
                break;
            case R.id.radioButton2:
                //female
                gender = "Female";
        }
        String division = ((Spinner) findViewById(R.id.spinner)).getSelectedItem().toString();
        return snum+","+fname+","+lname+","+gender+","+division+"\n";
    }
}
