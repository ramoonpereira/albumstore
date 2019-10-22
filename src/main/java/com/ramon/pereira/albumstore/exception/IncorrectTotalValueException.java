package com.ramon.pereira.albumstore.exception;

import com.ramon.pereira.albumstore.exception.handler.BaseHandlerException;

public class IncorrectTotalValueException extends BaseHandlerException {
    public IncorrectTotalValueException(Object... parameters) {
        super(parameters);
    }
}
