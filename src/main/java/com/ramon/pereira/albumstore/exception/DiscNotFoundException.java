package com.ramon.pereira.albumstore.exception;

import com.ramon.pereira.albumstore.exception.handler.BaseHandlerException;

import java.util.function.Supplier;

public class DiscNotFoundException extends BaseHandlerException{
  public DiscNotFoundException(Object... parameters) {
    super(parameters);
  }
}
