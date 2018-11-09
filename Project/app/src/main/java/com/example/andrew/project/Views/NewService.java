package com.example.andrew.project.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.andrew.project.Model.Admin;
import com.example.andrew.project.Model.HomeOwner;
import com.example.andrew.project.Model.Service;
import com.example.andrew.project.Model.ServiceProvider;
import com.example.andrew.project.Model.User;
import com.example.andrew.project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class NewService extends AppCompatActivity {

    private Service service;
    private DatabaseReference aDatabase;

    private TextInputLayout textInputName;
    private TextInputLayout textInputType;
    private TextInputLayout textInputRate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_service);

        Intent intent = getIntent();

        aDatabase = FirebaseDatabase.getInstance().getReference();


        textInputName = findViewById(R.id.text_input_name);
        textInputType = findViewById(R.id.text_input_type);
        textInputRate = findViewById(R.id.text_input_rate);

    }

    public void finishButton(View view) {
        //get the text from the three fields
        //store them as three dif strings
        //search for the pass and username and make no spaces
        //if it fails, show warning
        //Do same thing for email
        //IF ALL THOSE ARE TRUE
        //Push to database
        //Bring to the next page (welcome page)

        //startActivity(newIntent(RegistrationUserInfo.this, WelcomeScreen.class));

       // if (!validateEmail() | !validateUsername() | !validatePassword()) {
           // return;
        //}
        service = new Service();

        String name =  textInputName.getEditText().getText().toString().trim();
        String type = textInputType.getEditText().getText().toString().trim();
        String rate = textInputRate.getEditText().getText().toString().trim();

        String input = "Name: " + service;
        input += "\n";
        input += "Type: " + type;
        input += "\n";
        input += "Rate: " + rate;

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();

        service.setName(name);
        service.setType(type);
        service.setRate(Double.parseDouble(rate));
        Intent intent = new Intent(NewService.this,  ServicesView.class);
        intent.putExtra("Service", name);

        if (service instanceof Service) {
            aDatabase.child("services").child(((Service) service).getType()).setValue(service);
        }

        startActivity(intent);

    }
}
