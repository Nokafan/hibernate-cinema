package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieDao;
import com.dev.cinema.exeption.DataProcessingExeption;
import com.dev.cinema.model.Movie;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class MovieDaoImpl extends GenericDaoImpl<Movie> implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        return super.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return super.get(Movie.class, id);
    }

    @Override
    public List<Movie> getAll() {
        log.info("Callig method getAll() from MovieDaoImpl");
        try (Session session = super.sessionFactory.openSession()) {
            Query<Movie> getAllMovieQuery = session.createQuery("from Movie", Movie.class);
            return getAllMovieQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingExeption("Can't get all Movies from DB", e);
        }
    }
}
