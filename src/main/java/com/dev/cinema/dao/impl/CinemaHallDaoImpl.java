package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.exeption.DataProcessingExeption;
import com.dev.cinema.model.CinemaHall;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class CinemaHallDaoImpl extends GenericDaoImpl<CinemaHall> implements CinemaHallDao {
    @Autowired
    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return super.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        log.info("Calling method getAll() at CinemaHallDaoImp");
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from CinemaHall", CinemaHall.class).getResultList();
        } catch (HibernateException e) {
            throw new DataProcessingExeption("Can't get all cinema halls from DB", e);
        }
    }
}
