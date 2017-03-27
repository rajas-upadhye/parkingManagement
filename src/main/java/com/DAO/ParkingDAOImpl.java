package com.DAO;

import com.Entity.Reservation;
import com.Entity.Spot;
import com.Entity.Vehicle;
import com.MyBatisConnectionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Created by rajasupadhye on 2/19/17.
 */
public class ParkingDAOImpl implements ParkingDAO {

    private static final Logger log = LoggerFactory.getLogger(ParkingDAOImpl.class);
    private SqlSessionFactory sqlSessionFactory = null;

    public ParkingDAOImpl(){
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    @Override
    public Integer makeReservation(Integer vehicleId, Integer spotId) {
        SqlSession session = sqlSessionFactory.openSession();
        try{
            HashMap<String,Integer> params = new HashMap<String, Integer>();
            params.put("vehicle_id",vehicleId);
            params.put("spot_id",spotId);
            params.put("parkingId", 0);
            Integer parkingID = session.insert("Parking.insert",params);
            session.commit();
            if(parkingID > 0)
                return params.get("parkingId");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Reservation getReservation(Integer reservationId) {
        SqlSession session = sqlSessionFactory.openSession();
        try{
            log.info("Getting the reservation : " + reservationId);
            Reservation reservation = session.selectOne("Parking.getReservation",reservationId);
            return reservation;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean updateReservation(Integer reservationId, Spot spotId) {
        SqlSession session = sqlSessionFactory.openSession();
        try{
            log.info("Updating the reservation in DAO : " + reservationId + " with new spot " + spotId.getSpotId());
            HashMap<String,Integer> params = new HashMap<>();
            params.put("reservationId",reservationId);
            params.put("spotId",spotId.getSpotId());
            int rows = session.update("Parking.updateReservation",params);
            session.commit();
            if(rows > 0) {
                log.info("Update of reservation " + reservationId + " is Success!");
                return true;
            }else{
                log.info("Update of reservation " + reservationId + " is Problem in update!");
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }
}
