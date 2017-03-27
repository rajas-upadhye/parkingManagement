package com.Entity;

/**
 * Created by rajasupadhye on 2/18/17.
 */
public class Spot {
    Integer spotId = 0;
    String type = null;
    String isAssisted = null;
    String isReserved = null;
    Integer status = 0;

    public Spot(){

    }


    public Spot(int spotID , String spotType , String spotIsAssisted , String spotIsReserved, int spotStatus){
        spotId = spotID;
        type = spotType;
        isAssisted = spotIsAssisted;
        isReserved = spotIsReserved;
        status = spotStatus;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsAssisted() {
        return isAssisted;
    }

    public void setIsAssisted(String isAssisted) {
        this.isAssisted = isAssisted;
    }

    public String getIsReserved() {
        return isReserved;
    }

    public void setIsReserved(String isReserved) {
        this.isReserved = isReserved;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
