package com.example.andrew.project.Model;

import java.util.ArrayList;

public class ServiceProvider extends User {

    private String companyName, phoneNumber, description;
    private boolean isLicensed;
    private ArrayList<Service> services;

    public ServiceProvider() {
        super(null, null, null);
        companyName = null; phoneNumber = null; description = null;
        isLicensed = false;
        services = new ArrayList<>();
    }

    public void addService (Service service) {
        services.add(service);
    }

    public void setCompanyName (String companyName) {
        this.companyName = companyName;
    }

    public void setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public void setLicensed (boolean licensed) {
        this.isLicensed = licensed;
    }


    public String getCompanyName () {
        return companyName;
    }

    public String getPhoneNumber () {
        return phoneNumber;
    }

    public String getDescription () {
        return description;
    }

    public ArrayList<Service> getServices () {
        return services;
    }

    public void removeService (Service service) {
        services.remove(service);
    }

    public boolean isLicensed() {
        return isLicensed;
    }
}
