package dao;


import models.entities.Vehicle;

import javax.ejb.Stateless;

import java.util.List;

import static configuration.HibernateSessionFactoryConfig.getSessionFactory;

@Stateless
public class ShopDao {


    public ShopDao() {
    }

    public List<Vehicle> getAllVehiclesByType(String type) {
        return (List<Vehicle>) getSessionFactory().openSession()
                .createQuery(" From Vehicle where type = :type")
                .setParameter("type", type)
                .getResultList();
    }


    public List<Vehicle> getAllVehiclesByEnginePowerInRange(Integer min, Integer max) {
        return (List<Vehicle>) getSessionFactory().openSession()
                .createQuery("From Vehicle where enginePower >= :min and enginePower <= :max")
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }


}
