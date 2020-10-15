package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieDao;
import com.dev.cinema.exeption.DataProcessingExeption;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Movie;
import com.dev.cinema.util.HibernateUtil;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Log4j
@Dao
public class MovieDaoImpl extends GenericDaoImpl<Movie> implements MovieDao {
    @Override
    public Movie add(Movie movie) {
        return super.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        log.info("Callig method getAll() from MovieDaoImpl");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Movie> getAllMovieQuery = session.createQuery("from Movie", Movie.class);
            return getAllMovieQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingExeption("Can't get all Movies from DB", e);
        }
    }
}
