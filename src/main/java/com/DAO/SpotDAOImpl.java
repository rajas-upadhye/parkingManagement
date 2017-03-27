package com.DAO;

import org.slf4j.Logger;
import com.Entity.Spot;
import com.Entity.Vehicle;
import com.MyBatisConnectionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.LoggerFactory;

/**
 * Created by rajasupadhye on 2/18/17.
 */
public class SpotDAOImpl implements SpotDAO {

    private static final Logger log = LoggerFactory.getLogger(SpotDAOImpl.class);
    private SqlSessionFactory sqlSessionFactory = null;


    public SpotDAOImpl(){
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }


    @Override
    public Spot getFreeSpot() {
        SqlSession session = sqlSessionFactory.openSession();
        try{
            Spot resultSpot = session.selectOne("Spot.suggestSpot");
            return resultSpot;

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return null;
    }

    @Override
    public Spot getFreeSpotByType(String type) {
        SqlSession session = sqlSessionFactory.openSession();
        try{
            int spotType = 0;
            switch (type){
                case "compact" : spotType = 1;
                                 break;
                case "suv" : spotType = 2;
                             break;

                default: break;
            }
            if(spotType > 0) {
                Spot resultSpot = session.selectOne("Spot.suggestSpotByType", spotType);
                return resultSpot;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return null;
    }

    public Spot getSpotById(int spotId){
        if(spotId>0){
            SqlSession session = sqlSessionFactory.openSession();
            try{
                Spot result = session.selectOne("Spot.getSpotById",spotId);
                return result;
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                session.close();
            }
        }
        return null;
    }

    @Override
    public Integer reserveSpot(Vehicle vehicle, Integer spotId) {
        if(vehicle!=null && spotId != null && spotId > 0){
           SqlSession session = sqlSessionFactory.openSession();
            try{
                log.info("Reserving Spot : " + spotId);
                //do update on given spot and mark the spot as reserved.
                int reserveStatus  = session.update("Spot.reserveSpot",spotId);
                session.commit();
                if(reserveStatus > 0) {
                    log.info("Done Reserving Spot :  " + spotId);
                    return 1;
                }
                else {
                    log.info("Problem in Reserving the Spot " + spotId);
                    return -1;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                session.close();
            }
        }
        return -1;
    }

    @Override
    public boolean freeSpot(Integer spotId) {
        SqlSession session = sqlSessionFactory.openSession();
        try{
            log.info("Trying to free the spot  : " + spotId);
            int rows = session.update("Spot.freeSpot", spotId);
            session.commit();
            if(rows > 0) {
                log.info("Update of spot " + spotId + " is Success!");
                return true;
            }else{
                log.info("Update of spot " + spotId + " is Problem in update!");
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return false;
    }

    @Override
    public boolean reserveSpot(Integer spotId) {
        if(spotId!=null && spotId > 0){
            SqlSession session = sqlSessionFactory.openSession();
            try{
                log.info("Reserving Spot : " + spotId);
                //do update on given spot and mark the spot as reserved.
                int reserveStatus  = session.update("Spot.reserveSpot",spotId);
                session.commit();
                if(reserveStatus > 0) {
                    log.info("Done Reserving Spot :  " + spotId);
                    return true;
                }
                else {
                    log.info("Problem in Reserving the Spot " + spotId);
                    return false;
                }

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                session.close();
            }
        }
        return false;
    }
}
