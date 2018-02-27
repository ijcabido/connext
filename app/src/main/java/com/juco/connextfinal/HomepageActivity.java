package com.juco.connextfinal;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class HomepageActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://simplifiedcoding.net/demos/marvel";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;

    private static final String TAG = "HomepageActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    Button filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        //loadRecycleViewData();

        for(int i = 0; i<=10; i++){
            ListItem listItem = new ListItem(
                    "heading " + (i+1),
                    "Zzzzzzzzzzzzzzz"
            );

            listItems.add(listItem);
        }

        //initialize adapter
        adapter = new ItemAdapter(listItems, this);

        recyclerView.setAdapter(adapter);

        filter = (Button)findViewById(R.id.btnfilter);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FilterActivity.class);
                startActivity(i);
            }
        });

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        //Set up ViewPager with the section adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        //color of pages
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.action_home:
                        break;

                    case R.id.action_sell:
                        Intent j = new Intent(HomepageActivity.this, SellActivity.class);
                        startActivity(j);
                        break;

                    case R.id.action_myitems:
                        Intent k = new Intent(HomepageActivity.this, MyItemsActivity.class);
                        startActivity(k);
                        break;

                    case R.id.action_profile:
                        Intent m = new Intent(HomepageActivity.this, ProfileActivity.class);
                        startActivity(m);
                        break;
                }
                return false;
            }
        });
    }

    /*private  void loadRecycleViewData(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data....");
        progressDialog.show();
    }*/

}
