package com.bushyn.hotel.model.exception;

import com.bushyn.hotel.model.enums.ErrorType;

public class EntityException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "entity is not found!";

    public EntityException(String message) {
        super(message);
    }

    public EntityException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
