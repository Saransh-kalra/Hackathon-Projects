package com.example.saranshkalra.health_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewPagePatient extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthlistner;
    private DatabaseReference myRef;
    private String UID;


    private TextView Fullname;
    private TextView Age;
    private TextView Address;
    private TextView DOB;
    private TextView Height;
    private TextView Weight;
    private TextView BloodGroup;
    private TextView Allergies;
    private TextView Extra;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page_patient);

        Fullname = findViewById(R.id.full_name);
        Age = findViewById(R.id.age);
        Address = findViewById(R.id.address);
        DOB = findViewById(R.id.dob);
        Height = findViewById(R.id.height);
        Weight = findViewById(R.id.weight);
        BloodGroup = findViewById(R.id.blood_group);
        Allergies = findViewById(R.id.allergies);
        Extra = findViewById(R.id.extra);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        UID = user.getUid();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void showData(DataSnapshot dataSnapshot){
        for(DataSnapshot ds: dataSnapshot.getChildren()){

            System.out.println(ds.child(UID).getKey());

            String[] Ori = new String[9];
                    Ori = ds.child(UID).getValue().toString().split(",");

             System.out.println(Ori);

            String[] Final = new String[13];
/*
            for(int j=0; j<8; j++){

                System.out.println("Final[]=="+Ori[j]);

            }
*/
           // New[8] = "dekwncklenk";
           // System.out.println(New[8]);
           // int var = 8;
            //if(New[8] != ""){var=9;}


            for(int i=0;i<9;i++) {
                String[] Smallsplit = Ori[i].split("=");
                Final[i] = Smallsplit[1];


            }



            Fullname.setText(Final[0]);
            Age.setText(Final[6]);
            Address.setText(Final[8]);
            DOB.setText(Final[4]);
            Height.setText(Final[5]);
            Weight.setText(Final[2]);
            BloodGroup.setText(Final[6]);
            Allergies.setText(Final[3]);
            Extra.setText(Final[1]);


        }



    }




}

