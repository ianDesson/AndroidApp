package com.example.andrew.project.Views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andrew.project.Model.Admin;
import com.example.andrew.project.Model.Service;
import com.example.andrew.project.Model.ServiceProvider;
import com.example.andrew.project.Model.User;
import com.example.andrew.project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceProviderServicesView extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private ServiceProvider user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_provider_services);

        Intent intent = getIntent();
        user = (ServiceProvider) intent.getSerializableExtra("User");

        final ArrayList<String> titlesList = new ArrayList<String>();
        final ArrayList<String> typesList = new ArrayList<String>();
        final ArrayList<Double> ratesList = new ArrayList<Double>();

        final ListView listView = findViewById(R.id.servicesList);



        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Add all the values in Firebase to an ArrayList
                for (DataSnapshot postSnapshot : dataSnapshot.child("serviceProviders")
                        .child(user.getUsername()).child("services").getChildren()) {

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
                final ServiceProviderServicesAdapter adapter = new ServiceProviderServicesAdapter(ServiceProviderServicesView.this, titles, types, rates);

                listView.setAdapter(adapter);

                Intent intent = getIntent();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final ServiceProvider userTemp = (ServiceProvider) intent.getSerializableExtra("User");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ServiceProviderServicesAdapter adapter = (ServiceProviderServicesAdapter) listView.getAdapter();
                final int positionToDelete = position;
                final String titleOfDeleted = adapter.getTitle(position);
                // Open the delete dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ServiceProviderServicesView.this);
                builder.setTitle("Delete?");
                builder.setMessage("Are you sure you want to delete " + titleOfDeleted + "?");
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Delete", new AlertDialog.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Delete data here
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");
                        user.removeService(user.getService(positionToDelete));
                        db.child("serviceProviders").child(userTemp.getUsername()).child("services").setValue(userTemp.getServices());
                        Intent intent = new Intent(ServiceProviderServicesView.this, WelcomeScreen.class);
                        intent.putExtra("User", userTemp);
                        startActivity(intent);
                    }
                });
                builder.show();

            }
        });

    }


    class ServiceProviderServicesAdapter extends BaseAdapter {
        Context context;
        String[] titles;
        String[] types;
        double[] rates;
        private LayoutInflater inflater = null;

        public ServiceProviderServicesAdapter(Context context, String[] titles, String[] types, double[] rates) {
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
                rowView = inflater.inflate(R.layout.service_provider_service_row, null);

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