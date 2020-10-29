package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exeption.DataProcessingExeption;
import com.dev.cinema.model.User;
import java.util.Optional;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public User get(Long id) {
        return super.get(User.class, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("Calling method findByEmail() from UserDaoImpl");
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                        "FROM User u "
                            + "WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();
        } catch (HibernateException e) {
            throw new DataProcessingExeption("Faild to get user with email = " + email, e);
        }
    }

    @Override
    public User add(User entity) {
        return super.add(entity);
    }
}
