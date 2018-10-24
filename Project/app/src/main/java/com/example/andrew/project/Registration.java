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
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        if (adminCreated) {
            Button adminBtn = findViewById(R.id.btn1);
            adminBtn.setVisibility(View.GONE);
        }

    }

    public void createAdmin(View view) {
        adminCreated = true;
        String id = databaseUsers.push().getKey();
        user = new Admin();
        databaseUsers.child(id).setValue(user);
        Intent intent = new Intent(Registration.this, RegistrationUserInfo.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void createHomeOwner(View view) {
        String id = databaseUsers.push().getKey();
        user = new HomeOwner();
        databaseUsers.child(id).setValue(user);
        Intent intent = new Intent(Registration.this, RegistrationUserInfo.class);
        intent.putExtra("User", user);
        Registration.this.startActivity(new Intent());
    }

    public void createServiceProvider(View view) {
        String id = databaseUsers.push().getKey();
        user = new ServiceProvider();
        databaseUsers.child(id).setValue(user);
        Intent intent = new Intent(Registration.this, RegistrationUserInfo.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void login(View view) {

    }
}

