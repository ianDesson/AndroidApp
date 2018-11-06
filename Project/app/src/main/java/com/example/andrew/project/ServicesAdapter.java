package com.example.andrew.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class ServicesAdapter extends BaseAdapter {
    Context context;
    String[] titles;
    String[] types;
    double[] rates;
    private static LayoutInflater inflater = null;

    public ServicesAdapter(Context context, String[] titles, String[] types, double[] rates) {
        this.context = context;
        this.titles = titles;
        this.types = types;
        this.rates = rates;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v != null) {
            v = inflater.inflate(R.layout.service_row, null);

            TextView titleText = v.findViewById(R.id.title);
            titleText.setText(titles[position]);

            TextView typeText = v.findViewById(R.id.type);
            typeText.setText(types[position]);

            TextView rateText = v.findViewById(R.id.rate);
            rateText.setText(Double.toString(rates[position]));
        }
        return v;
    }
}
