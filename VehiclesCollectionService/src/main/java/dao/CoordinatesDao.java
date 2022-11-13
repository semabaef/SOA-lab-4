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

    public List<Coordinates> findByX(float x) {
        return (List<Coordinates>) getSessionFactory().openSession()
                .createQuery(" From Coordinates where x = :x ")
                .setParameter("x", x)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public List<Coordinates> findByY(long y) {
        return (List<Coordinates>) getSessionFactory().openSession()
                .createQuery(" From Coordinates where  y = :y")
                .setParameter("y", y)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public Coordinates findByXAndY(float x, long y) {
        Coordinates coordinates = null;
        try {
            coordinates = (Coordinates) getSessionFactory().openSession()
                    .createQuery(" From Coordinates where x = :x and y = :y")
                    .setParameter("x", x)
                    .setParameter("y", y)
                    .getSingleResult();
        } catch (NoResultException ignored) {}
        return coordinates;
    }

}
