package com.example.maruta.moneymanage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //UI
    private Toolbar appBar;
    private ViewPager view_pager;
    private TabLayout tabs;

    //Rest
    private CustomPageAdapter pagerAdp;
    private SharedPreferences sh;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mf = getMenuInflater();
        mf.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.menu_settings:
                Intent it = new Intent(this, SettingsActivity.class);
                startActivity(it);
                return true;

            case R.id.money_hamburger:

                startActivity(new Intent(this, MoneyActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBar = findViewById(R.id.app_bar);
        setSupportActionBar(appBar);

        initStatusBar();
        initDefault();//THIS SHOULD BE AN ACTIVITY YO
        initUi();

    }

    private void initUi() {

        view_pager = findViewById(R.id.view_pager);
        pagerAdp = new CustomPageAdapter(getSupportFragmentManager());

        pagerAdp.addFragment(new Fragment_Usage(), "Usage");
        pagerAdp.addFragment(new Fragment_Spendings(), "Spendings");
        pagerAdp.addFragment(new Fragment_Statistics(), "Statistics");

        view_pager.setAdapter(pagerAdp);

        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(view_pager);


    }

    private void initDefault() {

        sh = getSharedPreferences("com.example.maruta.moneymanage", Context.MODE_PRIVATE);

        if(!sh.contains("Currency")){

            sh.edit().putString("Currency", "lei").apply();

        }

        if(!sh.contains("Budget")){

            sh.edit().putString("Budget", "0.00").apply();
        }

    }

    private void initStatusBar(){

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }
}
