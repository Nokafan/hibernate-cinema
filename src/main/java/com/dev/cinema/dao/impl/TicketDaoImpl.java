package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.TicketDao;
import com.dev.cinema.model.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl extends GenericDaoImpl<Ticket> implements TicketDao {
    public TicketDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Ticket add(Ticket entity) {
        return super.add(entity);
    }

    @Override
    public Ticket get(Long id) {
        return super.get(Ticket.class, id);
    }
}
