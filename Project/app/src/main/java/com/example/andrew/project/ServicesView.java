package com.example.andrew.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.example.andrew.project.UserClasses.Service;

public class ServicesView extends AppCompatActivity {

    private ArrayAdapter<Service> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_view);


    }
}
