package com.example.andrew.project.Views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.andrew.project.R;

import com.example.andrew.project.ServicesAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServicesView extends AppCompatActivity {

    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_view);

        final ArrayList<String> titlesList = new ArrayList<String>();
        final ArrayList<String> typesList = new ArrayList<String>();
        final ArrayList<Double> ratesList = new ArrayList<Double>();

        databaseReference = FirebaseDatabase.getInstance().getReference("services");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Add all the values in Firebase to an ArrayList
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    titlesList.add(postSnapshot.toString());
                    typesList.add(postSnapshot.child("type").toString());
                    ratesList.add(Double.parseDouble(postSnapshot.child("rate").toString()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Initialize arrays for ServicesAdapter by converting the ArrayLists to an Array
        String[] titles = titlesList.toArray(new String[0]);
        String[] types = typesList.toArray(new String[0]);
        double[] rates = new double[ratesList.size()];
        for (int i = 0; i < rates.length; i++) {
            rates[i] = ratesList.get(i);
        }

        // Set the ListView's Adapter
        ListView listView = findViewById(R.id.servicesList);
        ServicesAdapter adapter = new ServicesAdapter(this, titles, types, rates);

        listView.setAdapter(adapter);
    }
}
