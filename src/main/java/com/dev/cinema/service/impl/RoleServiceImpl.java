package com.dev.cinema.service.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.model.Role;
import com.dev.cinema.service.ShoppingCart;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements ShoppingCart {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.getRoleByName(roleName);
    }
}
