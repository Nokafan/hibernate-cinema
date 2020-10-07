package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

@Log4j
@Dao
public class MovieSessionDaoImpl extends GenericDaoImpl<MovieSession> implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        log.info("--- findAvailableSessions ---");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return super.add(movieSession);
    }
}
