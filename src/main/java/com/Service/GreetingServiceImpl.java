package com.Service;

import com.Entity.Status;

import java.util.Calendar;

/**
 * Created by rajasupadhye on 2/18/17.
 */
public class GreetingServiceImpl implements GreetingService{
    public Status checkStatus(){
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        if(checkHour(hour))
            return new Status("Welcome to Parking");
        else
            return new Status("Sorry we are closed for the day !");
    }

    static boolean checkHour(int h){
        if(h >= 9 && h<= 21)
            return true;
        else
            return false;
    }
}
