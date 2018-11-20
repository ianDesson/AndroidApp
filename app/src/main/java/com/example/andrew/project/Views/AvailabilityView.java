package com.example.andrew.project.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andrew.project.Model.Availability;
import com.example.andrew.project.Model.ServiceProvider;
import com.example.andrew.project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AvailabilityView extends AppCompatActivity {

    Button saveChangesButton;

    //Start times
    EditText textViewSat;
    EditText textViewSun;
    EditText textViewMon;
    EditText textViewTue;
    EditText textViewWed;
    EditText textViewThu;
    EditText textViewFri;

    //End times
    EditText textViewSatEnd;
    EditText textViewSunEnd;
    EditText textViewMonEnd;
    EditText textViewTueEnd;
    EditText textViewWedEnd;
    EditText textViewThuEnd;
    EditText textViewFriEnd;

    //Checkboxes
    CheckBox checkBoxMon;
    CheckBox checkBoxTue;
    CheckBox checkBoxWed;
    CheckBox checkBoxThu;
    CheckBox checkBoxFri;
    CheckBox checkBoxSat;
    CheckBox checkBoxSun;
    Availability avail;

    ServiceProvider user;
    Intent intent;
    Intent intent2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.availability);
        //Start times
        textViewSat = findViewById(R.id.editTextSaturday);
        textViewSun = findViewById(R.id.editTextSunday);
        textViewMon = findViewById(R.id.editTextMonday);
        textViewTue = findViewById(R.id.editTextTuesday);
        textViewWed = findViewById(R.id.editTextWednesday);
        textViewThu = findViewById(R.id.editTextThursday);
        textViewFri = findViewById(R.id.editTextFriday);

        //End times (dun dun dunnn)
        textViewSatEnd = findViewById(R.id.editTextSaturdayEnd);
        textViewSunEnd = findViewById(R.id.editTextSundayEnd);
        textViewMonEnd = findViewById(R.id.editTextMondayEnd);
        textViewTueEnd = findViewById(R.id.editTextTuesdayEnd);
        textViewWedEnd = findViewById(R.id.editTextWednesdayEnd);
        textViewThuEnd = findViewById(R.id.editTextThursdayEnd);
        textViewFriEnd = findViewById(R.id.editTextFridayEnd);

        //Checkboxes
        checkBoxMon = findViewById(R.id.checkBoxMonday);
        checkBoxTue = findViewById(R.id.checkBoxTuesday);
        checkBoxWed = findViewById(R.id.checkBoxWednesday);
        checkBoxThu = findViewById(R.id.checkBoxThursday);
        checkBoxFri = findViewById(R.id.checkBoxFriday);
        checkBoxSat = findViewById(R.id.checkBoxSaturday);
        checkBoxSun = findViewById(R.id.checkBoxSunday);

        intent = getIntent();
        user = (ServiceProvider) intent.getSerializableExtra("User");
        intent2 = new Intent(AvailabilityView.this, ServicesView.class);
        saveChangesButton = findViewById(R.id.AvailabilitySaveChangesButton);

    }

    private boolean validateMonday() {
        String mondayStartStr = textViewMon.getText().toString().trim();
        String mondayEndStr = textViewMonEnd.getText().toString().trim();
        int mondayStart = Integer.parseInt(textViewMon.getText().toString().trim());
        int mondayEnd = Integer.parseInt(textViewMonEnd.getText().toString().trim());

        if (mondayStartStr.isEmpty()) {
            textViewMon.setError("Empty field");
            return false;
        } else if (mondayEndStr.isEmpty()) {
            textViewMonEnd.setError("Empty field");
            return false;
        } else if (mondayStart > 24) {
            textViewMon.setError("Must be 0-24h");
            return false;
        } else if (mondayEnd > 24) {
            textViewMonEnd.setError("Must be 0-24h");
            return false;
        } else if (mondayEnd <= mondayStart) {
            textViewMon.setError("Invalid range");
            return false;
        } else {
            textViewMon.setError(null);
            textViewMonEnd.setError(null);
            return true;
        }
    }

    public void saveChangesButton(View view) {

        if(!validateMonday()){
            return;
        }
        
        avail = new Availability();

        //Sunday
        if (checkBoxSun.isChecked()) {
            avail.setSundayStart(Integer.parseInt(textViewSun.getText().toString().trim()));
            avail.setSundayEnd(Integer.parseInt(textViewSunEnd.getText().toString().trim()));
        } else {
            avail.setSundayStart(-1);
            avail.setSundayEnd(-1);
        }

        //Monday
        if (checkBoxMon.isChecked()) {
            avail.setMondayStart(Integer.parseInt(textViewMon.getText().toString().trim()));
            avail.setMondayEnd(Integer.parseInt(textViewMonEnd.getText().toString().trim()));
        } else {
            avail.setMondayStart(-1);
            avail.setMondayEnd(-1);
        }

        //Tuesday
        if (checkBoxTue.isChecked()) {
            avail.setTuesdayStart(Integer.parseInt(textViewTue.getText().toString().trim()));
            avail.setTuesdayEnd(Integer.parseInt(textViewTueEnd.getText().toString().trim()));
        } else {
            avail.setTuesdayStart(-1);
            avail.setTuesdayEnd(-1);
        }

        //Wednesday
        if (checkBoxWed.isChecked()) {
            avail.setWednesdayStart(Integer.parseInt(textViewWed.getText().toString().trim()));
            avail.setWednesdayEnd(Integer.parseInt(textViewWedEnd.getText().toString().trim()));
        } else {
            avail.setWednesdayStart(-1);
            avail.setWednesdayEnd(-1);
        }

        //Thursday
        if (checkBoxThu.isChecked()) {
            avail.setThursdayStart(Integer.parseInt(textViewThu.getText().toString().trim()));
            avail.setThursdayEnd(Integer.parseInt(textViewThuEnd.getText().toString().trim()));
        } else {
            avail.setThursdayStart(-1);
            avail.setThursdayEnd(-1);
        }

        //Friday
        if (checkBoxFri.isChecked()) {
            avail.setFridayStart(Integer.parseInt(textViewFri.getText().toString().trim()));
            avail.setFridayEnd(Integer.parseInt(textViewFriEnd.getText().toString().trim()));
        } else {
            avail.setFridayStart(-1);
            avail.setFridayEnd(-1);
        }
        //Saturday
        if (checkBoxSat.isChecked()) {
            avail.setSaturdayStart(Integer.parseInt(textViewSat.getText().toString().trim()));
            avail.setSaturdayEnd(Integer.parseInt(textViewSatEnd.getText().toString().trim()));
        } else {
            avail.setSaturdayStart(-1);
            avail.setSaturdayEnd(-1);
        }


        // Save to database
        user.setAvailability(avail);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");
        db.child("serviceProviders").child(user.getUsername()).setValue(user);

        startActivity(intent2);
    }


}
