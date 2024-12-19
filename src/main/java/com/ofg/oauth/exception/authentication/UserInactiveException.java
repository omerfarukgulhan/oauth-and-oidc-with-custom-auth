package com.ofg.oauth.exception.authentication;

import com.ofg.oauth.core.util.message.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class UserInactiveException extends RuntimeException {
    public UserInactiveException() {
        super(Messages.getMessageForLocale("app.msg.user.inactive", LocaleContextHolder.getLocale()));
    }
}