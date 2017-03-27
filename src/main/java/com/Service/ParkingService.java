package com.Service;

import com.Entity.Reservation;
import com.Entity.Spot;
import com.Entity.Vehicle;

/**
 * Created by rajasupadhye on 2/19/17.
 */
public interface ParkingService {
    public Integer makeReservation(Integer vehicleId,Integer spotId);
    public Reservation updateReservation(Integer reservationId , Spot newspot);
}
