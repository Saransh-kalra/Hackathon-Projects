package com.example.saranshkalra.health_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;


import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    EditText Email, Password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        //MainOnCreate
        FirebaseApp.initializeApp(this);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

    }



    public void registeruser(View view){

        String email = Email.getText().toString();
        String password = Password.getText().toString();
        System.out.println("Email --" + email +"Paswd--" + password);
        mAuth.createUserWithEmailAndPassword(email,password);

        Intent i = new Intent(this,Choosepage.class);
        startActivity(i);


    }




}
