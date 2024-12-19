package com.ofg.oauth.model.response;

import com.ofg.oauth.model.entity.User;
import com.ofg.oauth.security.token.Token;

public record AuthResponse(
        UserResponse user,
        TokenResponse token) {
    public AuthResponse(User user, Token token) {
        this(new UserResponse(user), new TokenResponse(token));
    }
}
