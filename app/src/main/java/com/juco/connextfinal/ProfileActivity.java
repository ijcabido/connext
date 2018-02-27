package com.juco.connextfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "Profile Activity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    Button btnlogout;
    Button btnedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);

        String display = preferences.getString("display", "");

        /*TextView displayInfo = (TextView) findViewById(R.id.textViewName);
        displayInfo.setText(display);
        */
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        //Set up ViewPager with the section adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        //color of pages
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        btnedit = (Button) findViewById(R.id.btneditprofile);
        btnlogout = (Button)findViewById(R.id.logoutbutton) ;

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Finishing current activity on button click.
                finish();

                Toast.makeText(ProfileActivity.this,"Logout Successful!", Toast.LENGTH_LONG).show();
                Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(i);

            }
        });

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(j);
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.action_home:
                        Intent h = new Intent(ProfileActivity.this, HomepageActivity.class);
                        startActivity(h);
                        break;

                    case R.id.action_sell:
                        Intent j = new Intent(ProfileActivity.this, SellActivity.class);
                        startActivity(j);
                        break;

                    case R.id.action_myitems:
                        Intent k = new Intent(ProfileActivity.this, MyItemsActivity.class);
                        startActivity(k);
                        break;

                    case R.id.action_profile:
                        break;
                }
                return false;
            }
        });

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
