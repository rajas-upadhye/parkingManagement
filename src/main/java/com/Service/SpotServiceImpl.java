package com.Service;

import com.DAO.ParkingDAOImpl;
import com.DAO.SpotDAOImpl;
import com.DAO.VehicleDAOImpl;
import com.Entity.Spot;
import com.Entity.Vehicle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rajasupadhye on 2/18/17.
 */
public class SpotServiceImpl implements SpotService {
    private static final Logger log = LoggerFactory.getLogger(SpotServiceImpl.class);
    SpotDAOImpl spotDAO = null;
    VehicleDAOImpl vehicleDAO = null;
    ParkingDAOImpl parkingDAO = null;
    ObjectMapper mapper = null;

    public SpotServiceImpl(){
        spotDAO = new SpotDAOImpl();
        vehicleDAO = new VehicleDAOImpl();
        parkingDAO = new ParkingDAOImpl();
        mapper = new ObjectMapper();
    }
    @Override
    public Spot suggestSpot() {
        if(spotDAO!=null){
            return spotDAO.getFreeSpot();
        }else
        return null;
    }

    @Override
    public Spot suggestSpotByType(String type) {
        if(spotDAO!=null){
            return spotDAO.getFreeSpotByType(type);
        }else
        return null;
    }

    @Override
    public String reserveSpot(Vehicle vehicle, Integer spotId) {
        if(spotDAO!=null){
            log.info("Got a request for reserveSpot" + spotId + " from vehicle " + vehicle.getLicense());

            //check if spot already reserved
            Spot spotExists = spotDAO.getSpotById(spotId);
            if(spotExists!=null && spotExists.getStatus() == 0) {
                //perform reservation
                Integer reserveSpotresult = spotDAO.reserveSpot(vehicle, spotId);
                log.info("Reserved spot is  : " + reserveSpotresult);
                if (reserveSpotresult > 0) {
                    //check if vehicle already is registered with parking management
                    log.info("Vehicle search begin");
                    Vehicle vehicleSearched = vehicleDAO.getVehicle(vehicle.getLicense());
                    log.info("Vehicle search done");

                    Integer vehicleRegistrationId = null;
                    if (!isRegisteredVehicle(vehicleSearched)) {
                        log.info("Register the new vehicle");
                        vehicleRegistrationId = vehicleDAO.registerVehicle(vehicle);
                        log.info("Done with registration of vehicle with id " + vehicleRegistrationId);
                    } else {
                        vehicleRegistrationId = vehicleSearched.getVehicleId();
                    }
                    //enter and create a parking entry
                    log.info("Make Parking reservation begin");
                    Integer reservationId = parkingDAO.makeReservation(vehicleRegistrationId, spotId);
                    log.info("Make Parking reservation ends with parking id " + reservationId);
                    ObjectNode response = mapper.createObjectNode();
                    response.put("spotId", spotId);
                    response.put("reservationId", reservationId);
                    return response.toString();
                }
            }else{
                log.info("Spot already in use. Please request another spot.");
                Errors errors = new Errors();
                errors.setErrorMessage("Spot already in use. Please request another spot.");
                return errors.getErrorMessage();
            }
        }
        return "Major problem in reserving spot";
    }

    public static boolean isRegisteredVehicle(Vehicle vehicle){
        if(vehicle!=null){
            if(vehicle.getVehicleId()!=null) {
                log.info("Vehicle is registered previously");
                return true;
            }
        }
        log.info("Vehicle is new");
        return false;
    }

}
