package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exeption.DataProcessingExeption;
import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class MovieSessionDaoImpl extends GenericDaoImpl<MovieSession> implements MovieSessionDao {
    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public MovieSession get(Long id) {
        return super.get(MovieSession.class, id);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        log.info("Calling method findAvailableSessions() from MovieSessionDaoImpl");
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "FROM MovieSession ms "
                            + "LEFT JOIN FETCH ms.movie "
                            + "LEFT JOIN FETCH ms.cinemaHall "
                            + "WHERE ms.movie.id = :movieId "
                            + "AND ms.time BETWEEN :beginTime AND :endTime", MovieSession.class)
                    .setParameter("movieId", movieId)
                    .setParameter("beginTime", date.atTime(LocalTime.MIN))
                    .setParameter("endTime", date.atTime(LocalTime.MAX))
                    .getResultList();
        } catch (HibernateException e) {
            throw new DataProcessingExeption("Couldn't get movies sessions by id="
                    + movieId, e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return super.add(movieSession);
    }
}
