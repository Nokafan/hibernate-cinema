package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.ShoppingCartDao;
import com.dev.cinema.exeption.DataProcessingExeption;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class ShoppingCartDaoImpl extends GenericDaoImpl<ShoppingCart> implements ShoppingCartDao {
    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public ShoppingCart add(ShoppingCart entity) {
        return super.add(entity);
    }

    @Override
    public ShoppingCart get(Long id) {
        return super.get(ShoppingCart.class, id);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        log.info("Calling method getByUser() from ShoppingCartDaoImpl");
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM ShoppingCart sc "
                    + "LEFT JOIN FETCH sc.tickets "
                    + "JOIN FETCH sc.user "
                    + "WHERE sc.user = :user", ShoppingCart.class)
                    .setParameter("user", user)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new DataProcessingExeption("Couldn't get shopping cart by user id="
                    + user.getId(), e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        log.info("Calling method update() from ShoppingCartDaoImpl for " + shoppingCart);
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingExeption("Couldn't update cart with id="
                    + shoppingCart.getId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
