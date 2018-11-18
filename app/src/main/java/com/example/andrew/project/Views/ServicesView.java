package com.example.andrew.project.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andrew.project.Model.Admin;
import com.example.andrew.project.Model.Service;
import com.example.andrew.project.Model.ServiceProvider;
import com.example.andrew.project.R;

import com.example.andrew.project.Model.User;

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
                final ServicesAdapter adapter = new ServicesAdapter(ServicesView.this, titles, types, rates);

                listView.setAdapter(adapter);

                Intent intent = getIntent();
                final User user = (User) intent.getSerializableExtra("User");

                if (user instanceof Admin) {

                    // Add the ability to click on a listed service for editing (only admins can)
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Service edit = new Service(adapter.getType(position),
                                    adapter.getTitle(position), adapter.getRate(position));

                            Intent intent = new Intent(ServicesView.this, EditService.class);
                            intent.putExtra("Service", edit);
                            startActivity(intent);
                        }
                    });
                    // Make the Action button visible
                    FloatingActionButton actionButton = findViewById(R.id.floatingActionButton2);
                    actionButton.show();
                } else if (user instanceof ServiceProvider) {

                    // Add the ability to click on a listed service to add it to a Service Providers' service
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            // Create the new Service
                            Service newService = new Service(adapter.getType(position),
                                    adapter.getTitle(position), adapter.getRate(position));
                            ServiceProvider temp = (ServiceProvider) user;
                            // Add it to the user
                            temp.addService(newService);
                            // Get a reference to Firebase
                            DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("users");
                            // Push the updated list of services
                            userReference.child("serviceProviders").child(temp.getUsername())
                                    .child("services").setValue(temp.getServices());
                            Intent intent = new Intent(ServicesView.this, WelcomeScreen.class);
                            intent.putExtra("User", temp);
                            startActivity(intent);
                        }
                    });
                }
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

        public String getTitle(int position) {
            return titles[position];
        }

        public String getType(int position) {
            return types[position];
        }

        public double getRate(int position) {
            return rates[position];
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
                rowView = inflater.inflate(R.layout.service_row, null);

            TextView titleText = rowView.findViewById(R.id.title);
            titleText.setText(titles[position]);

            TextView typeText = rowView.findViewById(R.id.type);
            typeText.setText("Service Type: " + types[position]);

            TextView rateText = rowView.findViewById(R.id.rate);
            rateText.setText("Rate: " + Double.toString(rates[position]));
            return rowView;
        }
    }
}
