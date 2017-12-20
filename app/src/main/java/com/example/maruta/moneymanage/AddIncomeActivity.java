package com.example.maruta.moneymanage;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddIncomeActivity extends AppCompatActivity {

    private Toolbar appBar;
    private RadioGroup radioGroup;
    private RadioButton radioMonth, radioWeek, radioIrr;
    private Spinner dateSpinner;
    private ArrayAdapter<CharSequence> spinAdp;
    private TextView paidView;
    private Button finishButton;
    private EditText nameView, budgetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        initToolbar();
        initUi();
    }

    private void initUi() {

        paidView = findViewById(R.id.paidView);

        radioGroup = findViewById(R.id.radioGroup);

        nameView = findViewById(R.id.nameView);
        budgetView = findViewById(R.id.bugetView);

        finishButton = findViewById(R.id.finishButton);
        finishButton.setOnClickListener(event -> finishClick());

        radioMonth = findViewById(R.id.radioMonth);
        radioWeek = findViewById(R.id.radioWeek);
        radioIrr = findViewById(R.id.radioIrr);

        dateSpinner = findViewById(R.id.dateSpinner);

        spinAdp = ArrayAdapter.createFromResource(this,
                R.array.days_of_month, android.R.layout.simple_spinner_item);

        spinAdp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dateSpinner.setAdapter(spinAdp);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case R.id.radioMonth:

                        dateSpinner.setEnabled(true);
                        paidView.setPaintFlags(paidView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

                        spinAdp = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.days_of_month, R.layout.spinner_padding);

                        dateSpinner.setAdapter(spinAdp);

                        break;

                    case R.id.radioWeek:

                        dateSpinner.setEnabled(true);
                        paidView.setPaintFlags(paidView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

                        spinAdp = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.days_of_week, R.layout.spinner_padding);

                        dateSpinner.setAdapter(spinAdp);

                        break;

                    case R.id.radioIrr:

                        paidView.setPaintFlags(paidView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        dateSpinner.setEnabled(false);

                        break;

                }
            }
        });

    }

    private void finishClick() {

        /*check for not good*/

        if(nameView.getText().toString().equals("") || budgetView.getText().toString().equals("")){

            Toast.makeText(this, "Please fill out all the forms", Toast.LENGTH_SHORT).show();
            return;

        }

        Intent it = new Intent(this, MoneyActivity.class);

        it.putExtra("BudgetName", nameView.getText().toString());
        it.putExtra("Budget", budgetView.getText().toString());

        if(radioMonth.isChecked() || radioWeek.isChecked()){

            it.putExtra("Day", dateSpinner.getSelectedItem().toString());
        }else{

            it.putExtra("Day", "null");
        }

        startActivity(it);

    }


    private void initToolbar() {

        appBar = findViewById(R.id.app_bar);
        setSupportActionBar(appBar);

        appBar.setNavigationIcon(R.drawable.ic_back_arrow);

        appBar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MoneyActivity.class));
            }
        });
    }
}
