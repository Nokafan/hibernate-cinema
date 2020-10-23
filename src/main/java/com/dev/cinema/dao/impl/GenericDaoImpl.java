package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.GenericDao;
import com.dev.cinema.exeption.DataProcessingExeption;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j
public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    protected SessionFactory sessionFactory;

    @Autowired
    public GenericDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T add(T entity) {
        log.info("Сalling method add for " + entity);
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            log.info("Insert successful for " + entity);
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingExeption("Can't insert entity " + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public T get(Class<T> clazz, Long id) {
        log.info("Сalling method get for " + clazz.getSimpleName() + " id=" + id);
        try (Session session = sessionFactory.openSession()) {
            return session.get(clazz, id);
        }
    }
}
