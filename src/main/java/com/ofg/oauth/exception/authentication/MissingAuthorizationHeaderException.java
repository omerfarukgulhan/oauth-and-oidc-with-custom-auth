package com.ofg.oauth.exception.authentication;

import com.ofg.oauth.core.util.message.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class MissingAuthorizationHeaderException extends RuntimeException {
    public MissingAuthorizationHeaderException() {
        super(Messages.getMessageForLocale("app.msg.auth.header.missing", LocaleContextHolder.getLocale()));
    }
}