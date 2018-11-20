package com.example.andrew.project.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andrew.project.Model.Availability;
import com.example.andrew.project.Model.ServiceProvider;
import com.example.andrew.project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AvailabilityListView extends AppCompatActivity {

    DatabaseReference db;
    ListView listView;

    ServiceProvider user;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.availability_list_view);

        db = FirebaseDatabase.getInstance().getReference("users");

        Intent intent = getIntent();
        user = (ServiceProvider) intent.getSerializableExtra("User");

        listView = findViewById(R.id.availability_list);

        Availability availability = user.getAvailability();

        ArrayList<String> days = new ArrayList<>();
        ArrayList<Integer> startTimes = new ArrayList<>();
        ArrayList<Integer> endTimes = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            int time = availability.getTime(i);
            if (time != -1) {
                // We ignore times with -1
                if (i%2 == 0 | i== 0) {
                    // This is a start time
                    startTimes.add(time);
                } else {
                    //This is an end time
                    endTimes.add(time);
                }
            }
            switch (time) {
                case 0:
                case 1:
                    days.add("Sunday");
                    break;
                case 2:
                case 3:
                    days.add("Monday");
                    break;
                case 4:
                case 5:
                    days.add("Tuesday");
                    break;
                case 6:
                case 7:
                    days.add("Wednesday");
                    break;
                case 8:
                case 9:
                    days.add("Thursday");
                    break;
                case 10:
                case 11:
                    days.add("Friday");
                    break;
                case 12:
                case 13:
                    days.add("Sunday");
                    break;
            }
        }
        String[] days2 = new String[days.size()];
        days2 = days.toArray(days2);

        int[] starts = new int[startTimes.size()];
        int[] ends = new int[endTimes.size()];

        for (int i = 0; i < startTimes.size(); i++) {
            starts[i] = startTimes.get(i);
            ends[i] = endTimes.get(i);
        }

        AvailabilityAdapter adapter = new AvailabilityAdapter(AvailabilityListView.this, days2, starts, ends);

        listView.setAdapter(adapter);
    }




    class AvailabilityAdapter extends BaseAdapter {
        Context context;
        String[] days;
        int[] startTimes;
        int[] endTimes;
        private LayoutInflater inflater = null;

        public AvailabilityAdapter(Context context, String[] days, int[] startTimes, int[] endTimes) {
            this.context = context;
            this.days = days;
            this.startTimes = startTimes;
            this.endTimes= endTimes;
        }

        @Override
        public int getCount() {
            return days.length-1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        public String getDay(int position) {
            return days[position];
        }

        public int getStartTime(int position) {
            return startTimes[position];
        }

        public int getEndTime(int position) {
            return endTimes[position];
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
                rowView = inflater.inflate(R.layout.availability_list_row, null);

            TextView titleText = rowView.findViewById(R.id.day);
            titleText.setText(days[position]);

            TextView typeText = rowView.findViewById(R.id.time);
            typeText.setText(startTimes[position] + " - " + endTimes[position]);

            return rowView;
        }
    }

}
