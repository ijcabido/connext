package com.juco.connextfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MyItemsActivity extends AppCompatActivity {

    private static final String TAG = "Notification Activity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myitems);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        //Set up ViewPager with the section adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        //color of pages
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.action_home:
                        Intent h = new Intent(MyItemsActivity.this, HomepageActivity.class);
                        startActivity(h);
                        break;

                    case R.id.action_sell:
                        Intent j = new Intent(MyItemsActivity.this, SellActivity.class);
                        startActivity(j);
                        break;

                    case R.id.action_myitems:
                        break;

                    case R.id.action_profile:
                        Intent l = new Intent(MyItemsActivity.this, ProfileActivity.class);
                        startActivity(l);
                        break;
                }
                return false;
            }
        });
    }
}
