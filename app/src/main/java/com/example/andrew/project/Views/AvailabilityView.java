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
    EditText textViewSat; EditText textViewSun; EditText textViewMon; EditText textViewTue;
    EditText textViewWed; EditText textViewThu; EditText textViewFri;

    Availability avail;

    Intent intent;
    Intent intent2;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.availability);

        textViewSat = findViewById(R.id.editTextSaturday);
        textViewSun = findViewById(R.id.editTextSunday);
        textViewMon = findViewById(R.id.editTextMonday);
        textViewTue = findViewById(R.id.editTextTuesday);
        textViewWed = findViewById(R.id.editTextWednesday);
        textViewThu = findViewById(R.id.editTextThursday);
        textViewFri = findViewById(R.id.editTextFriday);


        intent = getIntent();
        intent2 = new Intent(AvailabilityView.this,  ServicesView.class);
        saveChangesButton = findViewById(R.id.AvailabilitySaveChangesButton);

    }

    public void saveChangesButton(View view){

        avail = new Availability();
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
