package com;

import com.Log.CustomLogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by rajasupadhye on 2/18/17.
 */

@ComponentScan
@EnableAutoConfiguration

public class ParkingDriver extends SpringBootServletInitializer {
    private static Class<ParkingDriver> appClass = ParkingDriver.class;

    public static void main (String args[]){
        CustomLogger customLogger = new CustomLogger();
        customLogger.log("Driver starting ...");

        SpringApplication.run(ParkingDriver.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(appClass);
    }



}
