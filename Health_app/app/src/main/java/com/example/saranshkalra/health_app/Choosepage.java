package com.example.saranshkalra.health_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Choosepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosepage);
    }

    public void gotoPatientinfo(View view){

        Intent i = new Intent(this,PatientDetails.class);
        startActivity(i);

    }

    public void gotoDoctorinfo(View view){

        Intent i = new Intent(this,QRscan.class);
        startActivity(i);

    }



}
