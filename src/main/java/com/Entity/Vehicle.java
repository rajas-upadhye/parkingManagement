package com.Entity;

/**
 * Created by rajasupadhye on 2/19/17.
 */
public class Vehicle {
    String license = null;
    Integer vehicleId = null;

    public Vehicle(){
        license = " ";
        vehicleId = 0;
    }

    public Vehicle(String licensePlate, Integer Id){
        license = licensePlate;
        vehicleId = Id;

    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
