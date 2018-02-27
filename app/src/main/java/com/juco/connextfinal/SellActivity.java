package com.juco.connextfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SellActivity extends AppCompatActivity {

    //Button btnscan;

    private ZXingScannerView scannerView;

    private static final String TAG = "Sell Activity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        //Set up ViewPager with the section adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        //color of pages
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        //btnscan = (Button) findViewById(R.id.btn_scan);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.action_home:
                        Intent h = new Intent(SellActivity.this, HomepageActivity.class);
                        startActivity(h);
                        break;

                    case R.id.action_myitems:
                        Intent k = new Intent(SellActivity.this, MyItemsActivity.class);
                        startActivity(k);
                        break;

                    case R.id.action_profile:
                        Intent l = new Intent(SellActivity.this, ProfileActivity.class);
                        startActivity(l);
                        break;

                    case R.id.action_sell:
                        break;
                }
                return false;
            }
        });
    }


    /*public void scanCode(View view) {
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ZXingScannerResultHandler());

        setContentView(scannerView);
        scannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler {
        @Override
        public void handleResult(Result result) {
            String resultCode = result.getText();
            Toast.makeText(SellActivity.this, resultCode, Toast.LENGTH_SHORT).show();

            setContentView(R.layout.activity_sell);
            scannerView.stopCamera();
        }
    }*/

}