package dao;


import jakarta.persistence.NoResultException;
import models.entities.Coordinates;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.Stateless;
import java.util.List;

import static configuration.HibernateSessionFactoryConfig.getSessionFactory;

@Stateless
public class CoordinatesDao {
    public void save(Coordinates coordinates) {
        Session session = getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(coordinates);
        tx1.commit();
        session.close();
    }


    public Coordinates findByXAndY(float x, long y) {
        Coordinates coordinates = null;
        try {
            Session session = getSessionFactory().openSession();
            coordinates = (Coordinates) session
                    .createQuery(" From Coordinates where x = :x and y = :y")
                    .setParameter("x", x)
                    .setParameter("y", y)
                    .getSingleResult();
            session.close();
        } catch (NoResultException ignored) {
        }
        return coordinates;
    }

}
