package com.example.andrew.project.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.andrew.project.Model.User;
import com.example.andrew.project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class NewService extends AppCompatActivity {

    private Text type;
    private DatabaseReference aDatabase;

    private TextInputLayout textInputService;
    private TextInputLayout textInputType;
    private TextInputLayout textInputRate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_service);

        Intent intent = getIntent();
        type = (Text) intent.getSerializableExtra("Type");

        aDatabase = FirebaseDatabase.getInstance().getReference();

        //Added by Andrew
        textInputService = findViewById(R.id.text_input_email);
        textInputType = findViewById(R.id.text_input_username);
        textInputRate = findViewById(R.id.text_input_password);


    }
}
