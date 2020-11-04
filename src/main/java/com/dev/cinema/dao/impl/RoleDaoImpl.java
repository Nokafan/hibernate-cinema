package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.exeption.DataProcessingExeption;
import com.dev.cinema.model.Role;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Role add(Role entity) {
        return super.add(entity);
    }

    @Override
    public Role get(Long id) {
        return super.get(Role.class, id);
    }

    @Override
    public Role getRoleByName(String roleName) {
        log.info("Calling method getRoleByName() at RoleDaoImpl");
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Role "
                    + "WHERE roleName = :roleName", Role.class)
                    .setParameter("roleName", Role.of(roleName).getRoleName())
                    .getSingleResult();
        } catch (HibernateException e) {
            throw new DataProcessingExeption("Can't get Role from DB", e);
        }
    }
}
