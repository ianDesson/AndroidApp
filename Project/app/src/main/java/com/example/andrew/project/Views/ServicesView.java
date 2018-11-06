package com.example.andrew.project.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.andrew.project.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ServicesView extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_view);

        adapter = new ArrayAdapter(this, R.layout.services_view);
        ListView listView = findViewById(R.id.servicesList);
        listView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("services");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void addService(){

        startActivity(new Intent(ServicesView.this, NewService.class));

    }
}
