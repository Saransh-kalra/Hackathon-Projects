package com.example.saranshkalra.health_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseUser;


public class PatientDetails extends AppCompatActivity {

    public FirebaseDatabase database;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
    }

    public void dbupdate(View view){

        //Collect data:
        EditText eFullname = findViewById(R.id.full_name);
        String Fullname = eFullname.getText().toString();

        EditText eAge = findViewById(R.id.age);
        String Age = eAge.getText().toString();

        EditText eAddress = findViewById(R.id.address);
        String Address=eAddress.getText().toString();

        EditText eDOB = findViewById(R.id.dob);
        String DOB=eDOB.getText().toString();

        EditText eHeight = findViewById(R.id.height);
        String Height=eHeight.getText().toString();

        EditText eWeight = findViewById(R.id.weight);
        String Weight=eWeight.getText().toString();

        EditText eBloodGroup = findViewById(R.id.blood_group);
        String BloodGroup=eBloodGroup.getText().toString();

        EditText eAllergies = findViewById(R.id.allergies);
        String Allergies=eAllergies.getText().toString();



        //Database::
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Patients");


        //Data Entry::
        //Date now = new Date();
        //String id = now.toString();
        FirebaseUser user = mAuth.getCurrentUser();
        String id = user.getUid();
        myRef.child(id);
        myRef.child(id).child("Fullname").setValue(Fullname);
        myRef.child(id).child("Age").setValue(Age);
        myRef.child(id).child("Address").setValue(Address);
        myRef.child(id).child("DOB").setValue(DOB);
        myRef.child(id).child("Height").setValue(Height);
        myRef.child(id).child("Weight").setValue(Weight);
        myRef.child(id).child("BloodGroup").setValue(BloodGroup);
        myRef.child(id).child("Allergies").setValue(Allergies);
        myRef.child(id).child("Extra").setValue("No Medical History yet.");


        Intent i = new Intent(this, ViewPagePatient.class);
        startActivity(i);

    }


}
