package com.Service;

import com.DAO.VehicleDAOImpl;
import com.Entity.Vehicle;

/**
 * Created by rajasupadhye on 2/19/17.
 */
public class VehicleServiceImpl implements VehicleService {
    VehicleDAOImpl vehicleDAO = null;
    public VehicleServiceImpl(){
        vehicleDAO = new VehicleDAOImpl();
    }

    @Override
    public Vehicle getVehicle(String license) {
        if(vehicleDAO!=null){
            return vehicleDAO.getVehicle(license);
        }else
        return null;
    }

    @Override
    public Integer registerVehicle(Vehicle vehicle) {
        if(vehicleDAO!=null){
            Integer id =  vehicleDAO.registerVehicle(vehicle);
            if(id!=null && id > 0)
                return id;
        }
        return Integer.MIN_VALUE;
    }
}
