package com.dev.cinema.security;

import com.dev.cinema.exeption.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> optionalUser = userService.findByEmail(email);
        if (optionalUser.isPresent() && idPassworValid(optionalUser.get(), password)) {
            return optionalUser.get();
        }
        throw new AuthenticationException("Incorrect username or password");
    }

    @Override
    public User register(String email, String password) {
        if (email != null && password != null
                && !email.isEmpty() && !password.isEmpty()) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            return userService.add(user);
        }
        throw new AuthenticationException("Incorrect username or password");
    }

    private boolean idPassworValid(User user, String password) {
        String saltedPassword = HashUtil.hashPassword(password, user.getSalt());
        return user.getPassword().equals(saltedPassword);
    }
}
