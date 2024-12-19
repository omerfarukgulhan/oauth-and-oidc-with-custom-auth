package com.ofg.oauth.exception.authentication;

import com.ofg.oauth.core.util.message.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException() {
        super(Messages.getMessageForLocale("app.msg.auth.token.expired", LocaleContextHolder.getLocale()));
    }
}
