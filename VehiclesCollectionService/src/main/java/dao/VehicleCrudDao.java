package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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
        try {
            vehicle = getSessionFactory().openSession().get(Vehicle.class, id);
        } catch (NoResultException ignored) {
        }
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
            query.orderBy(builder.asc(root.get(type.name())));
        } else
            query.orderBy(builder.desc(root.get(type.name())));

        query.where(preparePredicatesFromFilter(builder, fieldToFilter, root));
        return em.createQuery(query)
                .setFirstResult((page - 1) * limit)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Vehicle> getAllMatchingFieldsPageable(Map<String, String> fieldToFilter, Integer page, Integer limit) {
        Session session = getSessionFactory().openSession();
        EntityManager em = session.getEntityManagerFactory().createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Vehicle> query = builder.createQuery(Vehicle.class);
        Root<Vehicle> root = query.from(Vehicle.class);
        query.select(root);
        query.where(preparePredicatesFromFilter(builder, fieldToFilter, root));
        return em.createQuery(query)
                .setFirstResult((page - 1) * limit)
                .setMaxResults(limit)
                .getResultList();
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
                case "x_y": {
                    //0-ой элемент в массиве - это х, 1-ый - y
                    String xy[] = entry.getValue().split(" ");
                    if (coordinatesDao.findByXAndY(Float.parseFloat(xy[0]), Long.parseLong(xy[1])) != null) {
                        Long id = coordinatesDao.findByXAndY(Float.parseFloat(xy[0]), Long.parseLong(xy[1])).getId();
                        predicates.add(builder.equal(root.get("coordinates_id"), id));
                    }
                }
                case "x": {
                    if (coordinatesDao.findByX(Float.parseFloat(entry.getValue())) != null)
                        coordinatesDao.findByX(Float.parseFloat(entry.getValue())).
                                stream()
                                .map((coordinates) -> predicates.add(builder.equal(root.get("coordinates_id"), coordinates.getId())));
                }
                case "y": {
                    if (coordinatesDao.findByY(Long.parseLong(entry.getValue())) != null)
                        coordinatesDao.findByY(Long.parseLong(entry.getValue())).
                                stream()
                                .map((coordinates) -> predicates.add(builder.equal(root.get("coordinates_id"), coordinates.getId())));
                }

                default: {
                    predicates.add(builder.equal(root.get(entry.getKey()), entry.getValue()));
                }
            }
        }
        return predicates.toArray(new Predicate[0]);
    }


}
