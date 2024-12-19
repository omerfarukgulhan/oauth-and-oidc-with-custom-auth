package com.ofg.oauth.model.response;

import com.ofg.oauth.security.token.Token;

public record TokenResponse(
        String prefix,
        String token) {
    public TokenResponse(Token token) {
        this(token.getPrefix(), token.getToken());
    }
}
