package com.example.andrew.project.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andrew.project.R;
import com.example.andrew.project.Model.Admin;
import com.example.andrew.project.Model.HomeOwner;
import com.example.andrew.project.Model.User;

public class WelcomeScreen extends AppCompatActivity{

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
        TextView usernameDisplay = (TextView) findViewById(R.id.username_display);
        TextView accountType = (TextView) findViewById(R.id.account_type);
        Intent intent = getIntent();
        Button srvcBtn = findViewById(R.id.srvcBtn);

        user = (User) intent.getSerializableExtra("User");
        usernameDisplay.setText(user.getUsername());
        if(user instanceof Admin){
            accountType.setText("Admin");
            srvcBtn.setVisibility(View.VISIBLE);
        }
        else if(user instanceof HomeOwner){
            accountType.setText("Home Owner");
        }
        else{
            accountType.setText("Service Provider");
            srvcBtn.setVisibility(View.INVISIBLE);

            LinearLayout serviceProviderInfo = findViewById(R.id.service_provider_info);
            serviceProviderInfo.setVisibility(View.VISIBLE);
        }
    }

    public void srvcBtn (View view) {
        Intent intent = new Intent(WelcomeScreen.this, ServicesView.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void srcvViewBtn (View view) {
        // Button that will open the Activity allowing the Service Provider to view their Services
        Intent intent = new Intent(WelcomeScreen.this, ServiceProviderServicesView.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void srcvAddBtn (View view) {
        // Button that will open the Activity allowing the Service Provider to add to their Services
        //Intent intent = new Intent(WelcomeScreen.this, <Change to correct class name>);
        //intent.putExtra("User", user);
        //startActivity(intent);
    }

    public void avlViewBtn (View view) {
        // Button that will open the Activity containing all of the Service Provider's availabilities
        //Intent intent = new Intent(WelcomeScreen.this, <Change to correct class name>);
        //intent.putExtra("User", user);
        //startActivity(intent);
    }

    public void avlEditBtn (View view) {
        // Button that will open the Activity that will edit availabilities
        //Intent intent = new Intent(WelcomeScreen.this, <Change to correct class name>);
        //intent.putExtra("User", user);
        //startActivity(intent);

    }

}
