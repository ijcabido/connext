package com.juco.connextfinal;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();

//        if (restoredText != null) {
//            String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
//            int idName = prefs.getInt("idName", 0); //0 is the default value.
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etStudentID = (EditText) findViewById(R.id.etStudID);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        final Button bLogin = (Button) findViewById(R.id.bSignIn);

        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String studid = etStudentID.getText().toString();
                final String password = etPassword.getText().toString();

//                SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
//                SharedPreferences.Editor editor = preferences.edit();
//
//
//                String userDetails = preferences.getString(studid + password + "data", "No information on that user.");
//                editor = preferences.edit();
//                editor.putString("display",userDetails);
//                editor.commit();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            //
                            Log.d("Test", jsonResponse.toString());
                            //
                            if (success) {
                                String fullname = jsonResponse.getString("fullname");
                                int age = jsonResponse.getInt("age");

                                //
                                SharedPreferences.Editor editor = getSharedPreferences("UserSharedPreference", MODE_PRIVATE).edit();
                                editor.putString("id", jsonResponse.getString("studentid"));
                                editor.putString("password", jsonResponse.getString("password"));
                                editor.apply();
                                //
                                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                                intent.putExtra("fullname", fullname);
                                intent.putExtra("age", age);
                                intent.putExtra("studentid", studid);
                                LoginActivity.this.startActivity(intent);

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(studid, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}