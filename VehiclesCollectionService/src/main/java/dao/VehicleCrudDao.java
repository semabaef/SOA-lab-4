package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.*;
import models.entities.Vehicle;
import models.enums.SortByType;
import models.enums.SortOrder;
import models.enums.VehicleType;
import org.hibernate.Session;
import org.hibernate.Transaction;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static configuration.HibernateSessionFactoryConfig.getSessionFactory;

@Stateless
public class VehicleCrudDao {

    @EJB
    private CoordinatesDao coordinatesDao;

    public Vehicle findById(Long id) {
        Vehicle vehicle = null;
        Session session = getSessionFactory().openSession();
        try {
            vehicle = session.get(Vehicle.class, id);
        } catch (NoResultException ignored) {
        }
        session.close();
        return vehicle;
    }


    public void save(Vehicle vehicle) {
        Session session = getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(vehicle);
        tx1.commit();
        session.close();
    }


    public void update(Vehicle vehicle) {
        Session session = getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(vehicle);
        tx1.commit();
        session.close();
    }


    public void delete(Vehicle vehicle) {
        Session session = getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(vehicle);
        tx1.commit();
        session.close();
    }


    public List<Vehicle> getAllMatchingFieldsSortedPageable(Map<String, String> fieldToFilter, SortByType type,
                                                            SortOrder order, Integer page, Integer limit) {
        Session session = getSessionFactory().openSession();
        EntityManager em = session.getEntityManagerFactory().createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Vehicle> query = builder.createQuery(Vehicle.class);
        Root<Vehicle> root = query.from(Vehicle.class);
        query.select(root);

        if (order.equals(SortOrder.ASC)) {
            query.orderBy(builder.asc(sortParam(root, type.name())));
        } else
            query.orderBy(builder.desc(sortParam(root, type.name())));

        query.where(preparePredicatesFromFilter(builder, fieldToFilter, root));
        List<Vehicle> vehicles = em.createQuery(query)
                .setFirstResult((page - 1) * limit)
                .setMaxResults(limit)
                .getResultList();
        session.close();
        return vehicles;
    }

    public List<Vehicle> getAllMatchingFieldsPageable(Map<String, String> fieldToFilter, Integer page, Integer limit) {
        Session session = getSessionFactory().openSession();
        EntityManager em = session.getEntityManagerFactory().createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Vehicle> query = builder.createQuery(Vehicle.class);
        Root<Vehicle> root = query.from(Vehicle.class);
        query.select(root);
        query.where(preparePredicatesFromFilter(builder, fieldToFilter, root));
        List<Vehicle> vehicles = em.createQuery(query)
                .setFirstResult((page - 1) * limit)
                .setMaxResults(limit)
                .getResultList();
        session.close();
        return vehicles;
    }


    private Predicate[] preparePredicatesFromFilter(CriteriaBuilder builder, Map<String, String> fieldToVal, Root<Vehicle> root) {
        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, String> entry : fieldToVal.entrySet()) {
            switch (entry.getKey()) {
                case "creationDate": {
                    predicates.add(builder.equal(root.get(entry.getKey()), ZonedDateTime.parse(entry.getValue())));
                    break;
                }
                case "type": {
                    predicates.add(builder.equal(root.get(entry.getKey()), VehicleType.valueOf(entry.getValue())));
                    break;
                }
                case "x": {
                    predicates.add(builder.equal(root.get("coordinates").get("x"), entry.getValue()));
                }
                case "y": {
                    predicates.add(builder.equal(root.get("coordinates").get("y"), entry.getValue()));
                }
                default: {
                    predicates.add(builder.equal(root.get(entry.getKey()), entry.getValue()));
                }
            }
        }
        return predicates.toArray(new Predicate[0]);
    }

    private Path<Object> sortParam(Root<Vehicle> root, String type) {
        Path<Object> objectPath;
        switch (type) {
            case "x": {
                objectPath = root.get("coordinates").get("x");
                break;
            }
            case "y": {
                objectPath = root.get("coordinates").get("y");
                break;
            }
            default:
                objectPath = root.get(type);
        }
        return objectPath;
    }
}