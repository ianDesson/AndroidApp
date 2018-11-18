package com.example.andrew.project.Views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.andrew.project.R;
import com.example.andrew.project.Model.ServiceProvider;
import com.example.andrew.project.Model.Admin;
import com.example.andrew.project.Model.HomeOwner;
import com.example.andrew.project.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Registration extends AppCompatActivity {

    private DatabaseReference databaseUsers;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        databaseUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("admin").getValue() != null) {
                    Button adminBtn = findViewById(R.id.btn1);
                    adminBtn.setVisibility(View.GONE);
                } else {
                    Button adminBtn = findViewById(R.id.btn1);
                    adminBtn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void createAdmin(View view) {
        String id = databaseUsers.push().getKey();
        user = new Admin();
        databaseUsers.child(id).child("admin").setValue(user);
        Intent intent = new Intent(Registration.this, RegistrationUserInfo.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void createHomeOwner(View view) {
        String id = databaseUsers.push().getKey();
        user = new HomeOwner();
        databaseUsers.child(id).child("homeOwners").setValue(user);
        Intent intent = new Intent(Registration.this, RegistrationUserInfo.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void createServiceProvider(View view) {
        String id = databaseUsers.push().getKey();
        user = new ServiceProvider();
        databaseUsers.child(id).child("serviceProviders").setValue(user);
        Intent intent = new Intent(Registration.this, RegistrationUserInfo.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void login(View view) {
        startActivity(new Intent(Registration.this, LoginScreen.class));
    }
}

