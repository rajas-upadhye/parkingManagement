package com.Entity;

/**
 * Created by rajasupadhye on 2/23/17.
 */
public class Reservation {
    Integer reservationId ;
    Integer spotId ;
    Integer vehicleId ;
    Integer status ;

    public Reservation(){
        reservationId = null;
        spotId= null;
        vehicleId = null;
        status=0;
    }

    public Reservation(Integer reservation_id , Integer spot_id, Integer vehicle_id, Integer st){
        reservationId = reservation_id;
        spotId= spot_id;
        vehicleId = vehicle_id;
        status=st;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getSpotId() {
        return spotId;
    }

    public void setSpotId(Integer spotId) {
        this.spotId = spotId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
