package com.janbask.hibernate;

import com.janbask.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.close();
        factory.close();

        EntityManagerFactory emFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager manager =  emFactory.createEntityManager();
        manager.getTransaction().begin();
        Query query =  manager.createQuery("FROM NewModel");
        List results = query.getResultList();
        manager.getTransaction().commit();
        manager.close();
        emFactory.close();
    }
}
