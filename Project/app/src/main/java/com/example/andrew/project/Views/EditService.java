package com.example.andrew.project.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.andrew.project.Model.Service;
import com.example.andrew.project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EditService extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;
    private DatabaseReference mDatabase;
    private TextInputLayout newService;
    private TextInputLayout newType;
    private TextInputLayout newRate;
    Service service;
    Intent intent;
    Button deleteButton;
    Button saveChangeButton;
    Intent intent2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_edit);

        saveChangeButton = findViewById(R.id.saveChangeButtonEdit);
        deleteButton = findViewById(R.id.deleteButton);
        intent = getIntent();
        service = (Service) intent.getSerializableExtra("Service");
        ref = database.getReference("services");
        newService = findViewById(R.id.text_input_name);
        newType = findViewById(R.id.text_input_type);
        newRate = findViewById(R.id.text_input_rate);
        if(ref.child(service.getName())== null) {
            startActivity(intent2);
        }
        else {
            newService.getEditText().setText(service.getName());
            newType.getEditText().setText(service.getType());
            newRate.getEditText().setText(""+service.getRate());
        }
    }

    public void saveChange(View view){

        if (!validateService() | !validateRate() | !validateType())
            return;

        ref.child(service.getName().trim()).removeValue();
        service.setName(newService.getEditText().getText().toString().trim());
        service.setType(newType.getEditText().getText().toString().trim());
        service.setRate(Double.parseDouble(newRate.getEditText().getText().toString().trim()));
        ref.child(service.getName()).setValue(service);
        intent2 = new Intent(EditService.this,  ServicesView.class);
        startActivity(intent2);
    }

    public void deleteService(View view){

        ref.child(service.getName()).removeValue();
        intent2 = new Intent(EditService.this,  ServicesView.class);
        startActivity(intent2);
    }

    private boolean validateService(){
        String serviceInput = newRate.getEditText().getText().toString().trim();

        if(serviceInput.isEmpty()){
            newRate.setError("Field can't be empty");
            return false;
        }else{
            newRate.setError(null);
            return true;
        }
    }
    private boolean validateType(){
        String typeInput = newType.getEditText().getText().toString().trim();

        if (typeInput.isEmpty()){
            newType.setError("Field can't be empty");
            return false;
        }else{
            newType.setError(null);
            return true;
        }
    }
    private boolean validateRate(){
        String rateInput = newRate.getEditText().getText().toString().trim();
        try {
            Double.parseDouble(rateInput);
        } catch (Exception e) {
            newRate.setError("Not a valid rate");
            return false;
        }
        if(rateInput.isEmpty()){
            newRate.setError("Field can't be empty");
            return false;
        }else{
            newRate.setError(null);
            return true;
        }
    }

}
