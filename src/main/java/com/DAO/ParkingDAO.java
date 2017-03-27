package com.DAO;

import com.Entity.Reservation;
import com.Entity.Spot;
import com.Entity.Vehicle;


/**
 * Created by rajasupadhye on 2/19/17.
 */
public interface ParkingDAO {
    public Integer makeReservation(Integer vehicleId, Integer spotId);
    public Reservation getReservation(Integer reservationId);
    public boolean updateReservation(Integer reservationId, Spot spot);
}
