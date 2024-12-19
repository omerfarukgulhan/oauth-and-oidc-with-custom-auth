package com.ofg.oauth.service.abstracts;

import com.ofg.oauth.model.request.SignInCredentials;
import com.ofg.oauth.model.request.UserCreateRequest;
import com.ofg.oauth.model.response.AuthResponse;
import com.ofg.oauth.model.response.UserResponse;

import java.util.UUID;

public interface AuthService {
    AuthResponse login(SignInCredentials signInCredentials);

    UserResponse register(UserCreateRequest userCreateRequest);

    void logout(UUID userId);
}
