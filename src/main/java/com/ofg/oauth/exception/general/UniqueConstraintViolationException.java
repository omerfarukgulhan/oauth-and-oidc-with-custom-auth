package com.ofg.oauth.exception.general;

import com.ofg.oauth.core.util.message.Messages;
import org.springframework.context.i18n.LocaleContextHolder;


public class UniqueConstraintViolationException extends RuntimeException {
    public UniqueConstraintViolationException() {
        super(Messages.getMessageForLocale("app.msg.unique.constraint.violation", LocaleContextHolder.getLocale()));
    }
}
