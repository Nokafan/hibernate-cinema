package com.dev.cinema.service;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InjectData {
    private final RoleService roleService;
    private final UserService userService;
    private final ShoppingCartService cartService;

    @Autowired
    public InjectData(RoleService roleService,
                      UserService userService,
                      ShoppingCartService cartService) {
        this.roleService = roleService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @PostConstruct
    public void injectAdmin() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(adminRole);
        roleService.add(userRole);

        User adminUser = new User();
        adminUser.setEmail("admin@i.ua");
        adminUser.setPassword("1234");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(adminRole);
        roleSet.add(userRole);
        adminUser.setRoles(roleSet);

        User userUser = new User();
        userUser.setEmail("user@i.ua");
        userUser.setPassword("1234");
        userUser.setRoles(Set.of(userRole));

        userService.add(adminUser);
        userService.add(userUser);
        cartService.registerNewShoppingCart(adminUser);
        cartService.registerNewShoppingCart(userUser);
    }
}
