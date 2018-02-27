package com.juco.connextfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Intent intent = getIntent();
        String fullname = intent.getStringExtra("fullname");
        String studentid = intent.getStringExtra("studentid");
        int age = intent.getIntExtra("age", -1);

        TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        EditText etStudentID = (EditText) findViewById(R.id.etSID);
        EditText etAge = (EditText) findViewById(R.id.etAge);

        // Display user details
        String message = fullname + " welcome to your user area";
        tvWelcomeMsg.setText(message);
        etStudentID.setText(studentid);
        etAge.setText(age + "");
    }
}