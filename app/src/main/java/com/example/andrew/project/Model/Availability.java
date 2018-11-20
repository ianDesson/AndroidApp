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
        if (0 >= times.size())
            times.add(0, time);
        else
            times.set(0, time);
    }
    public void setSundayEnd (int time) {
        if (1 >= times.size())
            times.add(1, time);
        else
            times.set(1, time);
    }

    public void setMondayStart (int time) {
        if (2 >= times.size())
            times.add(2, time);
        else
            times.set(2, time);
    }
    public void setMondayEnd (int time) {
        if (3 >= times.size())
            times.add(3, time);
        else
            times.set(3, time);
    }

    public void setTuesdayStart (int time) {
        if (4 >= times.size())
            times.add(4, time);
        else
            times.set(4, time);
    }
    public void setTuesdayEnd (int time) {
        if (5 >= times.size())
            times.add(5, time);
        else
            times.set(5, time);
    }

    public void setWednesdayStart (int time) {
        if (6 >= times.size())
            times.add(6, time);
        else
            times.set(6, time);
    }
    public void setWednesdayEnd (int time) {
        if (7 >= times.size())
            times.add(7, time);
        else
            times.set(7, time);
    }

    public void setThursdayStart (int time) {
        if (8 >= times.size())
            times.add(8, time);
        else
            times.set(8, time);
    }
    public void setThursdayEnd (int time) {
        if (9 >= times.size())
            times.add(9, time);
        else
            times.set(9, time);
    }

    public void setFridayStart (int time) {
        if (10 >= times.size())
            times.add(10, time);
        else
            times.set(10, time);
    }
    public void setFridayEnd (int time) {
        if (11 >= times.size())
            times.add(11, time);
        else
            times.set(11, time);
    }

    public void setSaturdayStart (int time) {
        if (12 >= times.size())
            times.add(12, time);
        else
            times.set(12, time);
    }
    public void setSaturdayEnd (int time) {
        if (13 >= times.size())
            times.add(13, time);
        else
            times.set(13, time);
    }
}
