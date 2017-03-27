package com.DAO;

import com.Entity.Spot;
import com.Entity.Vehicle;

/**
 * Created by rajasupadhye on 2/18/17.
 */
public interface SpotDAO {
    public Spot getFreeSpot();
    public Spot getFreeSpotByType(String type);
    public Integer reserveSpot(Vehicle vehicle, Integer spotId);
    public boolean reserveSpot(Integer spotId);
    public boolean freeSpot(Integer spotId);
}
