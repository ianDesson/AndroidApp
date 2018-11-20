package com.example.andrew.project.Model;

import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class Availability implements Serializable {

    private ArrayList<Integer> times;

    public Availability () {
        times = new ArrayList<>(14);
    }

    // Getter method

    public int getTime(int index) {
        return times.get(index);
    }
    public ArrayList<Integer> getTimes() {
        return times;
    }

    // Setters for the hours for each day of the week.
    // times[0-6] are the start times and times[7-13] are the end times
    public void setSundayStart (int time) {
        times.add(0, time);
    }
    public void setSundayEnd (int time) {
        times.add(1, time);
    }

    public void setMondayStart (int time) {
        times.add(2, time);
    }
    public void setMondayEnd (int time) {
        times.add(3, time);
    }

    public void setTuesdayStart (int time) {
        times.add(4, time);
    }
    public void setTuesdayEnd (int time) {
        times.add(5, time);
    }

    public void setWednesdayStart (int time) {
        times.add(6, time);
    }
    public void setWednesdayEnd (int time) {
        times.add(7, time);
    }

    public void setThursdayStart (int time) {
        times.add(8, time);
    }
    public void setThursdayEnd (int time) {
        times.add(9, time);
    }

    public void setFridayStart (int time) {
        times.add(10, time);
    }
    public void setFridayEnd (int time) {
        times.add(11, time);
    }

    public void setSaturdayStart (int time) {
        times.add(12, time);
    }
    public void setSaturdayEnd (int time) {
        times.add(13, time);
    }
}
