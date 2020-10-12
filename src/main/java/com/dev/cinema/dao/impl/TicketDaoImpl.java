package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.TicketDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Ticket;

@Dao
public class TicketDaoImpl extends GenericDaoImpl<Ticket> implements TicketDao {
    @Override
    public Ticket add(Ticket entity) {
        return super.add(entity);
    }
}
