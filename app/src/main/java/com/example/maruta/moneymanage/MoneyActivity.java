package com.example.maruta.moneymanage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class MoneyActivity extends AppCompatActivity {

    private Toolbar appBar;
    private ImageView addIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        initToolbar();
        initUi();

    }

    private void initUi() {

        addIncome = findViewById(R.id.addIncome);

        addIncome.playSoundEffect(android.view.SoundEffectConstants.CLICK);
    }

    private void initToolbar() {

        appBar = findViewById(R.id.app_bar);
        setSupportActionBar(appBar);

        appBar.setNavigationIcon(R.drawable.ic_back_arrow);

        appBar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}
