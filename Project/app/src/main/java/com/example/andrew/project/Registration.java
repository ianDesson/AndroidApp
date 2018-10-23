package com.example.andrew.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Registration extends AppCompatActivity {

    private boolean adminCreated;
    private DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        if (adminCreated) {
            Button adminBtn = (Button) findViewById(R.id.btn1);
            adminBtn.setVisibility(View.GONE);
        }

    }

    public void createAdmin(View view) {
        String id = databaseUsers.push().getKey();
        Admin admin = new Admin();
        databaseUsers.child(id).setValue(admin);
        startActivity(new Intent(Registration.this, RegistrationUserInfo.class));
        adminCreated = true;
    }

    public void createHomeOwner(View view) {
        String id = databaseUsers.push().getKey();
        HomeOwner homeOwner = new HomeOwner();
        databaseUsers.child(id).setValue(homeOwner);
        startActivity(new Intent(Registration.this, RegistrationUserInfo.class));
    }

    public void createServiceProvider(View view) {
        String id = databaseUsers.push().getKey();
        ServiceProvider sp = new ServiceProvider();
        databaseUsers.child(id).setValue(sp);
        startActivity(new Intent(Registration.this, RegistrationUserInfo.class));
    }
}

