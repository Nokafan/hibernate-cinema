package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import java.util.Optional;
import org.hibernate.Session;

@Dao
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM User u "
                            + "WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();
        }
    }

    @Override
    public User add(User entity) {
        return super.add(entity);
    }
}
