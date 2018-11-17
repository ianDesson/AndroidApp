package com.example.andrew.project.Model;

import java.io.Serializable;

public class Availability implements Serializable {

    private int[] times;

    public Availability () {
        times = new int[14];
    }

    // Getters for the hours for each day of the week.
    public int getSundayStart () {
        return times[0];
    }

    public int getSundayEnd () {
        return times[7];
    }

    public int getMondayStart () {
        return times[1];
    }

    public int getMondayEnd () {
        return times[8];
    }

    public int getTuesdayStart () {
        return times[2];
    }

    public int getTuesdayEnd () {
        return times[9];
    }

    public int getWednesdayStart () {
        return times[3];
    }

    public int getWednesdayEnd () {
        return times[10];
    }

    public int getThursdayStart () {
        return times[4];
    }

    public int getThursdayEnd () {
        return times[11];
    }

    public int getFridayStart () {
        return times[5];
    }

    public int getFridayEnd () {
        return times[12];
    }

    public int getSaturdayStart () {
        return times[6];
    }

    public int getSaturdayEnd () {
        return times[13];
    }

    // Setters for the hours for each day of the week.
    // times[0-6] are the start times and times[7-13] are the end times
    public void setSaturdayStart (int time) {
        times[0] = time;
    }

    public void setSaturdayEnd (int time) {
        times[7] = time;
    }

    public void setMondayStart (int time) {
        times[1] = time;
    }

    public void setMondayEnd (int time) {
        times[8] = time;
    }

    public void setTuesdayStart (int time) {
        times[2] = time;
    }

    public void setTuesdayEnd (int time) {
        times[9] = time;
    }

    public void setWednesdayStart (int time) {
        times[3] = time;
    }

    public void setWednesdayEnd (int time) {
        times[10] = time;
    }

    public void setThursdayStart (int time) {
        times[4] = time;
    }

    public void setThursdayEnd (int time) {
        times[11] = time;
    }

    public void setFridayStart (int time) {
        times[5] = time;
    }

    public void setFridayEnd (int time) {
        times[12] = time;
    }

    public void setSundayStart (int time) {
        times[6] = time;
    }

    public void setSundayEnd (int time) {
        times[13] = time;
    }



}
