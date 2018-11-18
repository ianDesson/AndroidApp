package com.example.andrew.project.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.andrew.project.Model.ServiceProvider;
import com.example.andrew.project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServiceProviderRegistration extends AppCompatActivity {

    private EditText compNameText, pNumberText, descText;
    private CheckBox isLicensedBox;

    private ServiceProvider user;

    private DatabaseReference mDatabase;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_provider_registration);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        compNameText = findViewById(R.id.compName);
        pNumberText = findViewById(R.id.pNumber);
        descText = findViewById(R.id.description);
        isLicensedBox = findViewById(R.id.licensedBox);

        Intent intent = getIntent();

        user = (ServiceProvider) intent.getSerializableExtra("User");

    }


    public void nextButton(View view) {

        // If any of the inputs are invalid, we stop and wait for another button press.
        if (!verifyCompanyName() | !verifyPhoneNumber())
            return;

        String compName = compNameText.getText().toString().trim();
        String pNumber = pNumberText.getText().toString().trim();
        String description = descText.getText().toString().trim();
        boolean isLicensed = isLicensedBox.isChecked();

        user.setCompanyName(compName);
        user.setPhoneNumber(pNumber);
        user.setDescription(description);
        user.setLicensed(isLicensed);

        mDatabase.child("users").child("serviceProviders").child(user.getUsername()).setValue(user);

    }

    private boolean verifyCompanyName () {

        String compName = compNameText.getText().toString().trim();

        if (compName.isEmpty()) {
            compNameText.setError("Company Name cannot be empty");
        }

        return true;
    }

    private boolean verifyPhoneNumber () {

        String pNumber = pNumberText.getText().toString().trim();

        if (pNumber.isEmpty()) {
            pNumberText.setError("Phone Number cannot be empty");
            return false;
        }

        return true;
    }
}
