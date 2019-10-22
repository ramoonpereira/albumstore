package com.ramon.pereira.albumstore.exception;

import com.ramon.pereira.albumstore.exception.handler.BaseHandlerException;

public class SpotifyAuthenticateException  extends BaseHandlerException {
    public SpotifyAuthenticateException(Object... parameters) {
        super(parameters);
    }
}
