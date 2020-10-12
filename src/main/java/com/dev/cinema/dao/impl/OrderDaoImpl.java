package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.exeption.DataProcessingExeption;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

@Dao
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
    @Override
    public Order add(Order entity) {
        return super.add(entity);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT DISTINCT o FROM Order o "
                            + "JOIN FETCH o.tickets "
                            + "JOIN FETCH o.user "
                            + "WHERE o.user = :user", Order.class)
                    .setParameter("user", user)
                    .getResultList();
        } catch (HibernateException e) {
            throw new DataProcessingExeption("Couldn't get orders by user id="
                    + user.getId(), e);
        }
    }
}