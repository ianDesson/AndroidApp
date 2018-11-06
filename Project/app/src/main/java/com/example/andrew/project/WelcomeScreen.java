package com.example.andrew.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.andrew.project.UserClasses.Admin;
import com.example.andrew.project.UserClasses.HomeOwner;
import com.example.andrew.project.UserClasses.User;

public class WelcomeScreen extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
        TextView usernameDisplay = (TextView) findViewById(R.id.username_display);
        TextView accountType = (TextView) findViewById(R.id.account_type);
        Intent intent = getIntent();
        Button srvcBtn = findViewById(R.id.srvcBtn);

        User user = (User) intent.getSerializableExtra("User");
        usernameDisplay.setText(user.getUsername());
        if(user instanceof Admin){
            accountType.setText("Admin");
            srvcBtn.setVisibility(View.VISIBLE);
        }
        else if(user instanceof HomeOwner){
            accountType.setText("Home Owner");
            srvcBtn.setVisibility(View.INVISIBLE);
        }
        else{
            accountType.setText("Service Provider");
            srvcBtn.setVisibility(View.INVISIBLE);
        }
    }

    public void srvcBtn (View view) {
        startActivity(new Intent(WelcomeScreen.this, ServicesView.class));
    }
}
