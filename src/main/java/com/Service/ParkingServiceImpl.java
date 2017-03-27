package com.Service;

import com.DAO.ParkingDAOImpl;
import com.DAO.SpotDAOImpl;
import com.Entity.Reservation;
import com.Entity.Spot;
import com.Entity.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rajasupadhye on 2/19/17.
 */
public class ParkingServiceImpl implements ParkingService {
    private static final Logger log = LoggerFactory.getLogger(ParkingServiceImpl.class);
    ParkingDAOImpl parkingDAO = null;
    SpotDAOImpl spotDAO = null;

    public ParkingServiceImpl(){
        parkingDAO = new ParkingDAOImpl();
        spotDAO = new SpotDAOImpl();
    }

    @Override
    public synchronized Integer makeReservation(Integer vehicleId, Integer spotId) {
        if(parkingDAO!=null){
            return parkingDAO.makeReservation(vehicleId,spotId);
        }
        return null;
    }

    @Override
    public synchronized Reservation updateReservation(Integer reservationId, Spot newspot){
        if(parkingDAO!=null){
            log.info("Find the old reservation");
            //get old spot from res id
            Reservation oldReservation = parkingDAO.getReservation(reservationId);
            int oldSpotId = oldReservation.getSpotId();
            log.info("Got the old reservation : " + oldSpotId);
            log.info("Update the reservation with new spot");
            //update the reservation in parking table with new spot
            Boolean updateReservationResult = parkingDAO.updateReservation(reservationId,newspot);
            log.info("Updated the reservation with new spot : " + updateReservationResult);
            if(updateReservationResult) {
                //free old spot
                log.info("Begin free the old spot");
                Boolean freeSpotResult = spotDAO.freeSpot(oldSpotId);
                log.info("Old spot freed : " + freeSpotResult);

                //mark the new spot as reserved
                log.info("Begin reserve the new spot");
                Boolean reserveSpotResult = spotDAO.reserveSpot(newspot.getSpotId());
                log.info("New spot reserved : " + reserveSpotResult);

                Reservation newReservation = parkingDAO.getReservation(reservationId);
                return newReservation;


            }
        }
        return null;
    }
}
