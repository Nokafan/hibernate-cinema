package com.dev.cinema.security;

import com.dev.cinema.exeption.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Inject
    private ShoppingCartService cartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> optionalUser = userService.findByEmail(email);
        if (optionalUser.isPresent() && idPasswordValid(optionalUser.get(), password)) {
            return optionalUser.get();
        }
        throw new AuthenticationException("Incorrect username or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        User dbUser = userService.add(user);
        cartService.registerNewShoppingCart(dbUser);
        return dbUser;
    }

    private boolean idPasswordValid(User user, String password) {
        String hashedPassword = HashUtil.hashPassword(password, user.getSalt());
        return user.getPassword().equals(hashedPassword);
    }
}
