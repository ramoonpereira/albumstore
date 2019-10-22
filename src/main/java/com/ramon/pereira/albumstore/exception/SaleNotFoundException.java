package com.ramon.pereira.albumstore.exception;

import com.ramon.pereira.albumstore.exception.handler.BaseHandlerException;

public class SaleNotFoundException extends BaseHandlerException {
  public SaleNotFoundException(Object... parameters) {
    super(parameters);
  }
}
