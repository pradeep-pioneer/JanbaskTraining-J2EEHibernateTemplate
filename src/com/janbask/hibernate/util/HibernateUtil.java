package com.janbask.hibernate.util;

import com.janbask.hibernate.models.NewModel;
import com.janbask.hibernate.models.TestModel;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    //XML based configuration
    private static SessionFactory sessionFactory;

    //Annotation based configuration
    private static EntityManagerFactory entityManagerFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.addClass(TestModel.class);
            configuration.addClass(NewModel.class);
            configuration.addResource("com/janbask/hibernate/models/testmodel.hbm.xml");
            configuration.addResource("com/janbask/hibernate/models/newmodel.hbm.xml");
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

    public static EntityManagerFactory getEntityManagerFactory(){
        if(entityManagerFactory==null)
            entityManagerFactory = Persistence.createEntityManagerFactory("PERSISTENCE");
        return entityManagerFactory;
    }
}
