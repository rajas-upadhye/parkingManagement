package com.Service;
import com.Entity.Spot;
import com.Entity.Vehicle;

/**
 * Created by rajasupadhye on 2/18/17.
 */
public interface SpotService {
    public Spot suggestSpot();
    public Spot suggestSpotByType(String type);
    public String reserveSpot(Vehicle vehicle, Integer spotId);
}
