package com.example.andrew.project.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.andrew.project.Model.Availability;
import com.example.andrew.project.R;

public class AvailabilityView extends AppCompatActivity {

    Button saveChangesButton;
    EditText textViewSat = findViewById(R.id.editTextSaturday);
    EditText textViewSun = findViewById(R.id.editTextSunday);
    EditText textViewMon = findViewById(R.id.editTextMonday);
    EditText textViewTue = findViewById(R.id.editTextTuesday);
    EditText textViewWed = findViewById(R.id.editTextWednesday);
    EditText textViewThu = findViewById(R.id.editTextThursday);
    EditText textViewFri = findViewById(R.id.editTextFriday);

    Availability avail;

    Intent intent;
    Intent intent2;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.availability);
        intent = getIntent();
        intent2 = new Intent(AvailabilityView.this,  ServicesView.class);
        saveChangesButton = findViewById(R.id.AvailabilitySaveChangesButton);

    }

    public void saveChangesButton(View view){
       /*
        avail.setSaturdayStart();
        avail.setSaturdayEnd();
        avail.setSundayStart();
        avail.setSundayEnd();
        avail.setMondayStart();
        avail.setMondayEnd();
        avail.setTuesdayStart();
        avail.setTuesdayEnd();
        avail.setWednesdayStart();
        avail.setWednesdayEnd();
        avail.setThursdayStart();
        avail.setThursdayEnd();
        avail.setFridayStart();
        avail.setFridayEnd();
        */

    }


}
