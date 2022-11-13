package configuration;

import lombok.NoArgsConstructor;
import models.entities.Coordinates;
import models.entities.Vehicle;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.validator.internal.engine.ValidatorFactoryImpl;

import javax.ejb.Stateless;

@NoArgsConstructor
@Stateless
public class HibernateSessionFactoryConfig {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Coordinates.class);
                configuration.addAnnotatedClass(Vehicle.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }


}
