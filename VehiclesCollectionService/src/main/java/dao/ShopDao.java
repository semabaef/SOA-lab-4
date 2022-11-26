package dao;


import models.entities.Vehicle;
import models.enums.VehicleType;
import org.hibernate.Session;

import javax.ejb.Stateless;

import java.util.List;

import static configuration.HibernateSessionFactoryConfig.getSessionFactory;

@Stateless
public class ShopDao {


    public List<Vehicle> getAllVehiclesByType(VehicleType type) {
        Session session = getSessionFactory().openSession();
        List<Vehicle> vehicles = (List<Vehicle>) session
                .createQuery(" From Vehicle where type = :type")
                .setParameter("type", type)
                .getResultList();
        session.close();
        return vehicles;
    }


    public List<Vehicle> getAllVehiclesByEnginePowerInRange(Integer min, Integer max) {
        Session session = getSessionFactory().openSession();
        List<Vehicle> vehicles = (List<Vehicle>) session
                .createQuery("From Vehicle where enginePower >= :min and enginePower <= :max")
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
        session.close();
        return vehicles;
    }

}
