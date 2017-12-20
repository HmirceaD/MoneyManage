package com.example.maruta.moneymanage;

import android.app.LauncherActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MoneyActivity extends AppCompatActivity {


    //UI
    private Toolbar appBar;
    private ImageView addIncome;
    private ListView budgetList;

    //ListStufff
    private List<IncomeListItem> listItems;
    private IncomeAdapter incAdp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        initToolbar();
        initUi();
        initList();

    }

    private void initList() {

        budgetList = findViewById(R.id.budgetList);

        listItems = new ArrayList<>();

        incAdp = new IncomeAdapter(this, listItems);

        budgetList.setAdapter(incAdp);

    }

    @Override
    protected void onResume() {
        super.onResume();

        /*Create a new list item when returning from the add income activity*/

        Intent it = getIntent();

        if(it.getStringExtra("BudgetName") != null && it.getStringExtra("Day") != null){

            listItems.add(new IncomeListItem(it.getStringExtra("BudgetName"),
                    it.getStringExtra("Budget"),
                    it.getStringExtra("Day")));

            budgetList.setAdapter(incAdp);

        }else {
            Toast.makeText(this, "ZILE GURTa", Toast.LENGTH_SHORT).show();
        }
    }

    private void initUi() {

        addIncome = findViewById(R.id.addIncome);

        addIncome.setOnClickListener((View event) -> {

            addIncome.playSoundEffect(android.view.SoundEffectConstants.CLICK);
            startActivity(new Intent(this, AddIncomeActivity.class));

        });

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
