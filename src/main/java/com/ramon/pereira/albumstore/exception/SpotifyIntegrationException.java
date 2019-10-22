package com.ramon.pereira.albumstore.exception;

import com.ramon.pereira.albumstore.exception.handler.BaseHandlerException;

public class SpotifyIntegrationException extends BaseHandlerException {
    public SpotifyIntegrationException(Object... parameters) {
        super(parameters);
    }
}
