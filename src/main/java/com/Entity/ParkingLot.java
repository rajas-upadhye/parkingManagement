package com.Entity;

/**
 * Created by rajasupadhye on 2/18/17.
 */
public class ParkingLot {
    int lotId = 0;
    String code = null;
    String address = null;
    String contact = null;

    public ParkingLot(){

    }

    public ParkingLot(int lotID , String lotCode , String lotAddress , String lotContact){
        lotId = lotID;
        code = lotCode;
        address = lotAddress;
        contact = lotContact;
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
