package com.Controllers;


/**
 * Created by rajasupadhye on 2/18/17.
 */

import com.Entity.Spot;
import com.Entity.Vehicle;
import com.Service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//the controller will call the service

public class Greeting {
    private static final Logger log = LoggerFactory.getLogger(Greeting.class);
    GreetingServiceImpl greetingService = new GreetingServiceImpl();
    SpotServiceImpl spotService = new SpotServiceImpl();
    ParkingServiceImpl parkingService = new ParkingServiceImpl();

    ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(method = RequestMethod.GET, value = "/status")
    public ResponseEntity<?> greeting(){
        try {
            String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(greetingService.checkStatus());
            return new ResponseEntity<Object>(message, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Errors error = new Errors("Please try later as the parking lot is closed");
        return new ResponseEntity<Object>(error.getErrorMessage(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/spot")
    public ResponseEntity<?> getSpot(){
        try{
            String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(spotService.suggestSpot());
            return new ResponseEntity<Object>(message, HttpStatus.OK);
            //return (spotService.suggestSpot());
        }catch (Exception e){
            e.printStackTrace();
        }
        Errors error = new Errors("Please try later as the parking lot is full");
        return new ResponseEntity<Object>(error.getErrorMessage(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/spot" , params = "type")
    public ResponseEntity<?> getSpotByType(@RequestParam String type){
        try{
            String message =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(spotService.suggestSpotByType(type));
            log.info("Suggest spot result : " + message);
            if(message != null && !message.equals("null")) {
                log.info("Message is " + message + "with length " + message.length());
                return new ResponseEntity<Object>(message, HttpStatus.OK);
            }
            else {
                log.info("Sending error message since type is not correctly passed");
                Errors error = new Errors("Please use the parameter values correctly. Valid types are only compact and suv.");
                return new ResponseEntity<Object>(error.getErrorMessage(),HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            e.printStackTrace();
            log.info("Caught exception in getSpotByType");
            Errors error = new Errors("Error occured in getting spot by the requested type!");
            return new ResponseEntity<Object>(error.getErrorMessage(),HttpStatus.OK);
        }
    }

    //Here, POST is used to create a new reservation
    @RequestMapping(method = RequestMethod.POST, value = "/reservation/{spotId}")
    public ResponseEntity<?> reserveSpot(@RequestBody Vehicle vehicle, @PathVariable Integer spotId){
        try{
            //String message = "\'" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(spotService.reserveSpot(vehicle,spotId)) + "\'";
            String result = spotService.reserveSpot(vehicle,spotId);
            return new ResponseEntity<Object>(result,HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            Errors error = new Errors("Already reserved. Please request a new spot.");
            return new ResponseEntity<Object>(error.getErrorMessage(),HttpStatus.CONFLICT);
        }
    }

    //PUT is used to update the existing reservation
    @RequestMapping(method = RequestMethod.PUT, value = "/reservation/{reservationId}")
    public  ResponseEntity<?> updateReservation(@RequestBody Spot newspot, @PathVariable Integer reservationId){
        try{
            String message =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parkingService.updateReservation(reservationId,newspot));
            log.info("Updated reservation : " + message);
            if(message != null && !message.equals("null")) {
                log.info("Update successful now return response");
                return new ResponseEntity<Object>(message,HttpStatus.OK);
            }else{
                log.info("Sending error message since cant update reservation");
                Errors error = new Errors("Please use the path variable values correctly. Problem with given reservation.");
                return new ResponseEntity<Object>(error.getErrorMessage(),HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            e.printStackTrace();
            Errors error = new Errors("Update unsuccessful. Please request again.");
            return new ResponseEntity<Object>(error.getErrorMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    //DELETE is used to delete the existing reservation








}
