package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.GenericDao;
import com.dev.cinema.exeption.DataProcessingExeption;
import com.dev.cinema.util.HibernateUtil;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Log4j
public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    @Override
    public T add(T entity) {
        log.info("Сalling method add for " + entity);
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
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
}
