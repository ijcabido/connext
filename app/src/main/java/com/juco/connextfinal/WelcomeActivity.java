package com.juco.connextfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class WelcomeActivity extends AppCompatActivity {
    LinearLayout l1;
    Button buttonwelcome;
    Animation uptodown, downtoup;

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("UserSharedPreference", MODE_PRIVATE);
        String id = prefs.getString("id", null);
        String password = prefs.getString("password", null);
        if (id != null && password != null) {
            Intent i = new Intent(this, HomepageActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        buttonwelcome = (Button)findViewById(R.id.buttonwelcome);
        l1 = (LinearLayout)findViewById(R.id.l1);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        l1.setAnimation(uptodown);

        buttonwelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
