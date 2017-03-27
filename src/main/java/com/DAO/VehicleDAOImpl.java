package com.DAO;

import com.Entity.Vehicle;
import com.MyBatisConnectionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rajasupadhye on 2/19/17.
 */
public class VehicleDAOImpl implements VehicleDAO{

    private static final Logger log = LoggerFactory.getLogger(VehicleDAOImpl.class);
    private SqlSessionFactory sqlSessionFactory = null;

    public VehicleDAOImpl(){
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    @Override
    public Vehicle getVehicle(String license) {
        SqlSession session = sqlSessionFactory.openSession();
        try{
            Vehicle vehicle = session.selectOne("Vehicle.getVehicle",license);
            return vehicle;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return null;
    }

    @Override
    public Integer registerVehicle(Vehicle vehicle) {
        SqlSession session = sqlSessionFactory.openSession();
        try{
            Integer vehicleID = session.insert("Vehicle.insert",vehicle);
            log.info("Vehicle registration ID is : " + vehicle.getVehicleId());
            session.commit();
            if(vehicleID > 0)
                return vehicle.getVehicleId();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return Integer.MIN_VALUE;
    }
}
