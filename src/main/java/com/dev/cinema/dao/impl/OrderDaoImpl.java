package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.exeption.DataProcessingExeption;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
    public OrderDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Order add(Order entity) {
        return super.add(entity);
    }

    @Override
    public Order get(Long id) {
        return super.get(Order.class, id);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        log.info("Calling method getOrderHistory() from OrderDaoImpl");
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "SELECT DISTINCT o FROM Order o "
                            + "JOIN FETCH o.tickets "
                            + "WHERE o.user = :user", Order.class)
                    .setParameter("user", user)
                    .getResultList();
        } catch (HibernateException e) {
            throw new DataProcessingExeption("Couldn't get orders by user id="
                    + user.getId(), e);
        }
    }
}
