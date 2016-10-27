package com.ponikarchuk.dao;

import com.ponikarchuk.model.WordFrequency;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WordFrequencyDaoImpl implements WordFrequencyDAO {
    private static SessionFactory sessionFactory = createSessionFactory();

    private static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public void addWordFrequency(WordFrequency wordFrequency) {
        Session session = sessionFactory.openSession();
        session.save(wordFrequency);
        session.close();
    }

    public List<WordFrequency> listWordFrequency() {
        Session session = sessionFactory.openSession();
        List<WordFrequency> list = session.createQuery("from WordFrequency").list();
        session.close();
        return list;
    }

    public void deleteAll() {
        Session session = sessionFactory.openSession();
        session.createQuery("delete from WordFrequency").executeUpdate();
        session.close();
    }
}
