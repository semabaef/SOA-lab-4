package dao;


import models.entities.Vehicle;
import models.enums.VehicleType;

import javax.ejb.Stateless;
import java.util.List;

import static configuration.HibernateSessionFactoryConfig.getSessionFactory;

@Stateless
public class VehicleExtraOperationsDao {

    public Long countVehiclesWhereTypeIs(String type) {
        return (Long) getSessionFactory().openSession()
                .createQuery("select count (id) From Vehicle where type = :type")
                .setParameter("type", VehicleType.valueOf(type))
                .getSingleResult();
    }


    public List<Vehicle> getVehiclesWhereNameLike(String name) {
        return (List<Vehicle>) getSessionFactory().openSession()
                .createQuery("From Vehicle where name ilike :name")
                .setParameter("name", name)
                .getResultList();
    }


    public List<Vehicle> getVehiclesWhereEnginePowerIsLessThan(Integer ep) {
        return (List<Vehicle>) getSessionFactory().openSession()
                .createQuery("From Vehicle where enginePower < :ep")
                .setParameter("ep", ep)
                .getResultList();
    }

}
