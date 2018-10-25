package com.example.andrew.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
        TextView usernameDisplay = (TextView) findViewById(R.id.username_display);
        TextView accountType = (TextView) findViewById(R.id.account_type);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("User");
        usernameDisplay.setText(user.getUsername());
        if(user instanceof Admin){
            accountType.setText("Admin");
        }
        else if(user instanceof HomeOwner){
            accountType.setText("Home Owner");
        }
        else{
            accountType.setText("Service Provider");
        }
    }
}
