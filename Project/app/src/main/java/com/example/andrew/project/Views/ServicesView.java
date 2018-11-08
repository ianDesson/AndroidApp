package com.example.andrew.project.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andrew.project.R;

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
                    titlesList.add(postSnapshot.child("name").getValue().toString());
                    typesList.add(postSnapshot.child("type").getValue().toString());
                    ratesList.add(Double.parseDouble(postSnapshot.child("rate").getValue().toString()));
                }
                // Initialize arrays for ServicesAdapter by converting the ArrayLists to an Array
                String[] titles = titlesList.toArray(new String[0]);
                String[] types = typesList.toArray(new String[0]);
                double[] rates = new double[ratesList.size()];
                for (int i = 0; i < rates.length; i++) {
                    rates[i] = ratesList.get(i);
                }

                // Set the ListView's Adapter
                ListView listView = findViewById(R.id.servicesList);
                ServicesAdapter adapter = new ServicesAdapter(ServicesView.this, titles, types, rates);
                Log.i("YEET", ""+rates.length);

                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void addService(View view){

        startActivity(new Intent(ServicesView.this, NewService.class));

    }





    class ServicesAdapter extends BaseAdapter {
        Context context;
        String[] titles;
        String[] types;
        double[] rates;
        private LayoutInflater inflater = null;

        public ServicesAdapter(Context context, String[] titles, String[] types, double[] rates) {
            this.context = context;
            this.titles = titles;
            this.types = types;
            this.rates = rates;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View rowView = convertView;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (rowView == null)
                rowView = inflater.inflate(R.layout.service_row, parent);

            TextView titleText = rowView.findViewById(R.id.title);
            titleText.setText(titles[position]);

            TextView typeText = rowView.findViewById(R.id.type);
            typeText.setText(types[position]);

            TextView rateText = rowView.findViewById(R.id.rate);
            rateText.setText(Double.toString(rates[position]));
            return rowView;
        }
    }
}
