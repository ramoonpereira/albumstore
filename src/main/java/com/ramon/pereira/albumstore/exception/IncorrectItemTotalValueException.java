package com.ramon.pereira.albumstore.exception;

import com.ramon.pereira.albumstore.exception.handler.BaseHandlerException;

public class IncorrectItemTotalValueException extends BaseHandlerException {
    public IncorrectItemTotalValueException(Object... parameters) {
        super(parameters);
    }
}
