package com.example.andrew.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Registration extends AppCompatActivity {

    private boolean adminCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


    }

    public void createAdmin(View view) {
        startActivity(new Intent(Registration.this, RegistrationUserInfo.class));
    }

    public void createHomeOwner(View view) {
        startActivity(new Intent(Registration.this, RegistrationUserInfo.class));
    }

    public void createServiceProvider(View view) {
        startActivity(new Intent(Registration.this, RegistrationUserInfo.class));
    }
}
