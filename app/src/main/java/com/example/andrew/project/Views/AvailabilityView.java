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

        String input;

        if (mondayStartStr.isEmpty()) {
            textViewMon.setError("Empty field");
            input = "Cannot input an empty field.";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (mondayEndStr.isEmpty()) {
            textViewMonEnd.setError("Empty field");
            input = "Cannot input an empty field.";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (mondayStart > 24) {
            textViewMon.setError("Must be 0-24h");
            input = "Must input an hour 0-24";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (mondayEnd > 24) {
            textViewMonEnd.setError("Must be 0-24h");
            input = "Must input an hour 0-24";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (mondayEnd <= mondayStart) {
            textViewMon.setError("Invalid range");
            input = "Invalid range: end time must be bigger than start time";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            textViewMon.setError(null);
            textViewMonEnd.setError(null);
            return true;
        }
    }

    private boolean validateTuesday() {
        String tuesdayStartStr = textViewTue.getText().toString().trim();
        String tuesdayEndStr = textViewTueEnd.getText().toString().trim();
        int tuesdayStart = Integer.parseInt(textViewTue.getText().toString().trim());
        int tuesdayEnd = Integer.parseInt(textViewTueEnd.getText().toString().trim());

        String input;

        if (tuesdayStartStr.isEmpty()) {
            textViewTue.setError("Empty field");
            input = "Cannot input an empty field.";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (tuesdayEndStr.isEmpty()) {
            textViewTueEnd.setError("Empty field");
            input = "Cannot input an empty field.";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (tuesdayStart > 24) {
            textViewTue.setError("Must be 0-24h");
            input = "Must input an hour 0-24";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (tuesdayEnd > 24) {
            textViewTueEnd.setError("Must be 0-24h");
            input = "Must input an hour 0-24";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (tuesdayEnd <= tuesdayStart) {
            textViewTue.setError("Invalid range");
            input = "Invalid range: end time must be bigger than start time";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            textViewTue.setError(null);
            textViewTueEnd.setError(null);
            return true;
        }
    }

    private boolean validateWednesday() {
        String wednesdayStartStr = textViewWed.getText().toString().trim();
        String wednesdayEndStr = textViewWedEnd.getText().toString().trim();
        int wednesdayStart = Integer.parseInt(textViewWed.getText().toString().trim());
        int wednesdayEnd = Integer.parseInt(textViewWedEnd.getText().toString().trim());

        String input;

        if (wednesdayStartStr.isEmpty()) {
            textViewWed.setError("Empty field");
            input = "Cannot input an empty field.";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (wednesdayEndStr.isEmpty()) {
            textViewWedEnd.setError("Empty field");
            input = "Cannot input an empty field.";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (wednesdayStart > 24) {
            textViewWed.setError("Must be 0-24h");
            input = "Must input an hour 0-24";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (wednesdayEnd > 24) {
            textViewWedEnd.setError("Must be 0-24h");
            input = "Must input an hour 0-24";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (wednesdayEnd <= wednesdayStart) {
            textViewWed.setError("Invalid range");
            input = "Invalid range: end time must be bigger than start time";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            textViewWed.setError(null);
            textViewWedEnd.setError(null);
            return true;
        }
    }

    private boolean validateThursday() {
        String thursdayStartStr = textViewThu.getText().toString().trim();
        String thursdayEndStr = textViewThuEnd.getText().toString().trim();
        int thursdayStart = Integer.parseInt(textViewThu.getText().toString().trim());
        int thursdayEnd = Integer.parseInt(textViewThuEnd.getText().toString().trim());

        String input;

        if (thursdayStartStr.isEmpty()) {
            textViewThu.setError("Empty field");
            input = "Cannot input an empty field.";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (thursdayEndStr.isEmpty()) {
            textViewThuEnd.setError("Empty field");
            input = "Cannot input an empty field.";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (thursdayStart > 24) {
            textViewThu.setError("Must be 0-24h");
            input = "Must input an hour 0-24";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (thursdayEnd > 24) {
            textViewThuEnd.setError("Must be 0-24h");
            input = "Must input an hour 0-24";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (thursdayEnd <= thursdayStart) {
            textViewThu.setError("Invalid range");
            input = "Invalid range: end time must be bigger than start time";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            textViewThu.setError(null);
            textViewThuEnd.setError(null);
            return true;
        }
    }

    private boolean validateFriday() {
        String fridayStartStr = textViewFri.getText().toString().trim();
        String fridayEndStr = textViewFriEnd.getText().toString().trim();
        int fridayStart = Integer.parseInt(textViewFri.getText().toString().trim());
        int fridayEnd = Integer.parseInt(textViewFriEnd.getText().toString().trim());

        String input;

        if (fridayStartStr.isEmpty()) {
            textViewFri.setError("Empty field");
            input = "Cannot input an empty field.";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (fridayEndStr.isEmpty()) {
            textViewFriEnd.setError("Empty field");
            input = "Cannot input an empty field.";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (fridayStart > 24) {
            textViewFri.setError("Must be 0-24h");
            input = "Must input an hour 0-24";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (fridayEnd > 24) {
            textViewFriEnd.setError("Must be 0-24h");
            input = "Must input an hour 0-24";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (fridayEnd <= fridayStart) {
            textViewFri.setError("Invalid range");
            input = "Invalid range: end time must be bigger than start time";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            textViewFri.setError(null);
            textViewFriEnd.setError(null);
            return true;
        }
    }

    private boolean validateSaturday() {
        String saturdayStartStr = textViewSat.getText().toString().trim();
        String saturdayEndStr = textViewSatEnd.getText().toString().trim();
        int saturdayStart = Integer.parseInt(textViewSat.getText().toString().trim());
        int saturdayEnd = Integer.parseInt(textViewSatEnd.getText().toString().trim());

        String input;

        if (saturdayStartStr.isEmpty()) {
            textViewSat.setError("Empty field");
            input = "Cannot input an empty field.";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (saturdayEndStr.isEmpty()) {
            textViewSatEnd.setError("Empty field");
            input = "Cannot input an empty field.";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (saturdayStart > 24) {
            textViewSat.setError("Must be 0-24h");
            input = "Must input an hour 0-24";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (saturdayEnd > 24) {
            textViewSatEnd.setError("Must be 0-24h");
            input = "Must input an hour 0-24";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (saturdayEnd <= saturdayStart) {
            textViewSat.setError("Invalid range");
            input = "Invalid range: end time must be bigger than start time";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            textViewSat.setError(null);
            textViewSatEnd.setError(null);
            return true;
        }
    }

    private boolean validateSunday() {
        String sundayStartStr = textViewSun.getText().toString().trim();
        String sundayEndStr = textViewSunEnd.getText().toString().trim();
        int sundayStart = Integer.parseInt(textViewSun.getText().toString().trim());
        int sundayEnd = Integer.parseInt(textViewSunEnd.getText().toString().trim());

        String input;

        if (sundayStartStr.isEmpty()) {
            textViewSun.setError("Empty field");
            input = "Cannot input an empty field.";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (sundayEndStr.isEmpty()) {
            textViewSunEnd.setError("Empty field");
            input = "Cannot input an empty field.";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (sundayStart > 24) {
            textViewSun.setError("Must be 0-24h");
            input = "Must input an hour 0-24";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (sundayEnd > 24) {
            textViewSunEnd.setError("Must be 0-24h");
            input = "Must input an hour 0-24";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else if (sundayEnd <= sundayStart) {
            textViewSun.setError("Invalid range");
            input = "Invalid range: end time must be bigger than start time";
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            textViewSun.setError(null);
            textViewSunEnd.setError(null);
            return true;
        }
    }

    public void saveChangesButton(View view) {

        if(!validateMonday()|!validateTuesday()|!validateWednesday()|!validateThursday()|!validateFriday()|!validateSaturday()|!validateSunday()){
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
