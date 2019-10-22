package ru.zagshak.buySupply.util.exception;

import org.springframework.http.HttpStatus;

import static ru.zagshak.buySupply.util.exception.NotFoundException.NOT_FOUND_EXCEPTION;

public class NoAccessException extends ApplicationException {

    public static final String NO_ACCESS_EXCEPTION = "exception.common.NoAccess";

    public NoAccessException(String arg) {
        super(ErrorType.ACCESS_DENIED, NO_ACCESS_EXCEPTION, HttpStatus.UNPROCESSABLE_ENTITY, arg);
    }
}
