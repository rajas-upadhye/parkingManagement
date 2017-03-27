package com.DAO;

import com.Entity.Vehicle;

/**
 * Created by rajasupadhye on 2/19/17.
 */
public interface VehicleDAO {
    public Vehicle getVehicle(String license);
    public Integer registerVehicle(Vehicle vehicle);
}
