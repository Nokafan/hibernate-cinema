package com.dev.cinema.service;

import com.dev.cinema.model.Role;

public interface ShoppingCart {
    void add(Role role);

    Role getRoleByName(String roleName);
}
