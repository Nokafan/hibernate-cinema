package com.dev.cinema.security;

import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final String ROLE_USER = "USER";
    private final UserService userService;
    private final ShoppingCartService cartService;
    private final RoleService roleService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService cartService,
                                     RoleService roleService) {
        this.userService = userService;
        this.cartService = cartService;
        this.roleService = roleService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(List.of(roleService.getRoleByName(ROLE_USER)));
        user = userService.add(user);
        cartService.registerNewShoppingCart(user);
        log.info("Registered user for email " + email);
        return user;
    }
}
