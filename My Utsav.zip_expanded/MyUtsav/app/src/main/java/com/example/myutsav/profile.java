package com.example.myutsav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {
    TextView name,number,email,password,date;
    Button update;
    String userid;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=findViewById(R.id.usernameedit);
        number=findViewById(R.id.usernumber);
        email=findViewById(R.id.useremail);
        password=findViewById(R.id.userpass);
        date=findViewById(R.id.userdate);
        userid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        reference= FirebaseDatabase.getInstance().getReference("users");
        reference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    String strname= snapshot.child("fullname").getValue(String.class);
                    String strevent=snapshot.child("date").getValue(String.class);
                    String stremail=snapshot.child("email").getValue(String.class);
                    String strphone=snapshot.child("phone").getValue(String.class);
                    String strpass=snapshot.child("password").getValue(String.class);
                    name.setText(strname);
                    number.setText(strphone);
                    email.setText(stremail);
                    password.setText(strpass);
                    date.setText(strevent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}