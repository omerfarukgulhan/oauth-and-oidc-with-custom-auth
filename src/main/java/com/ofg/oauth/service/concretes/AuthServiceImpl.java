package com.ofg.oauth.service.concretes;

import com.ofg.oauth.exception.authentication.AuthenticationException;
import com.ofg.oauth.exception.authentication.UserInactiveException;
import com.ofg.oauth.model.entity.User;
import com.ofg.oauth.model.request.SignInCredentials;
import com.ofg.oauth.model.request.UserCreateRequest;
import com.ofg.oauth.model.response.AuthResponse;
import com.ofg.oauth.model.response.UserResponse;
import com.ofg.oauth.security.token.Token;
import com.ofg.oauth.security.token.TokenService;
import com.ofg.oauth.service.abstracts.AuthService;
import com.ofg.oauth.service.abstracts.UserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Autowired
    public AuthServiceImpl(UserService userService,
                           PasswordEncoder passwordEncoder,
                           TokenService tokenService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public AuthResponse login(SignInCredentials signInCredentials) {
        User user = userService.getUserByEmail(signInCredentials.email());

        validateUser(user, signInCredentials.password());

        Token token = tokenService.createToken(user, signInCredentials);
        return new AuthResponse(user, token);
    }

    private void validateUser(User user, String rawPassword) {
        if (!user.isActive()) {
            throw new UserInactiveException();
        }
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new AuthenticationException();
        }
    }

    @Override
    public UserResponse register(UserCreateRequest userCreateRequest) {
        return userService.addUser(userCreateRequest);
    }

    @Override
    public void logout(UUID userId) {
        tokenService.logout(userId);
    }
}
