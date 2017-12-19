package com.example.maruta.moneymanage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailText, passText, confPassText;
    private Button createUserBtn;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initUi();
        initStatusBar();

    }

    private void initStatusBar(){
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }

    private void initUi() {

        fAuth = FirebaseAuth.getInstance();

        emailText = findViewById(R.id.emailText);
        passText = findViewById(R.id.passText);
        confPassText = findViewById(R.id.confPassText);

        createUserBtn = findViewById(R.id.createButton);

        createUserBtn.setOnClickListener((View event) -> createUser());

    }

    public void createUser(){

        FirebaseUser currentUser = fAuth.getCurrentUser();

        if(currentUser != null){

            Toast.makeText(this, "You are already signed in", Toast.LENGTH_LONG).show();
            return;
        }

        String emailString = emailText.getText().toString();
        String passString = passText.getText().toString();
        String confPassString = confPassText.getText().toString();

        /*Check wrong cases*/

        if(emailString.equals("") || passString.equals("") || confPassString.equals("")){

            Toast.makeText(this, "Please fill out all the fields", Toast.LENGTH_LONG).show();
            return;

        }else if(!passString.equals(confPassString)){

            Toast.makeText(this, "Both password fields must match", Toast.LENGTH_LONG).show();
            passText.setText("");
            confPassText.setText("");

            return;
        }else if(passString.length() < 6){

            Toast.makeText(this, "Password must be atleast 6 characters long", Toast.LENGTH_LONG).show();

            return;
        }else {

            boolean isNumber = false;

            for(char c: passString.toCharArray()){

                if(Character.isDigit(c)){
                    isNumber = true;
                }
            }

            if(!isNumber){

                Toast.makeText(this, "Password must contain a digit", Toast.LENGTH_LONG).show();

                return;
            }
        }

        fAuth.createUserWithEmailAndPassword(emailString,passString)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(SignUpActivity.this, "Account creation successfull", Toast.LENGTH_LONG).show();

                            Intent it = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(it);

                        }else{

                            Toast.makeText(SignUpActivity.this, "Something went wrong, please try again", Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }
}
