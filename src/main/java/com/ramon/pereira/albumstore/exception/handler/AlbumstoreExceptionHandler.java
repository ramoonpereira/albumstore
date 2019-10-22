package com.ramon.pereira.albumstore.exception.handler;

import com.ramon.pereira.albumstore.exception.DiscNotFoundException;
import com.ramon.pereira.albumstore.exception.IncorrectItemTotalValueException;
import com.ramon.pereira.albumstore.exception.IncorrectTotalValueException;
import com.ramon.pereira.albumstore.exception.SaleNotFoundException;

import com.ramon.pereira.albumstore.exception.SpotifyAuthenticateException;
import com.ramon.pereira.albumstore.exception.SpotifyIntegrationException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Order(0)
public class AlbumstoreExceptionHandler {

  @ResponseBody
  @ExceptionHandler(DiscNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorMessage exceptionHandler(final DiscNotFoundException ex) {
    return ErrorMessage.builder()
        .developerMessage("No records were found for your search")
        .userMessage("No records were found for your search")
        .errorCode(2).build();
  }

  @ResponseBody
  @ExceptionHandler(SaleNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorMessage exceptionHandler(final SaleNotFoundException ex) {
    return ErrorMessage.builder()
        .developerMessage("No records were found for your search")
        .userMessage("No records were found for your search")
        .errorCode(2).build();
  }

  @ResponseBody
  @ExceptionHandler(IncorrectItemTotalValueException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage exceptionHandler(final IncorrectItemTotalValueException ex) {
    return ErrorMessage.builder()
            .developerMessage("Incorrect value, divergent from the value of the item and its quantity")
            .userMessage("Incorrect value, divergent from the value of the item and its quantity")
            .errorCode(3).build();
  }

  @ResponseBody
  @ExceptionHandler(IncorrectTotalValueException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage exceptionHandler(final IncorrectTotalValueException ex) {
    return ErrorMessage.builder()
            .developerMessage("Total incorrect value, divergent from the sum of the subtotals")
            .userMessage("Total incorrect value, divergent from the sum of the subtotals")
            .errorCode(4).build();
  }

  @ResponseBody
  @ExceptionHandler(SpotifyAuthenticateException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorMessage exceptionHandler(final SpotifyAuthenticateException ex) {
    return ErrorMessage.builder()
            .developerMessage("An error occurred while authenticating with the Spotify service.")
            .userMessage("An error occurred while authenticating with the Spotify service.")
            .errorCode(5).build();
  }

  @ResponseBody
  @ExceptionHandler(SpotifyIntegrationException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorMessage exceptionHandler(final SpotifyIntegrationException ex) {
    return ErrorMessage.builder()
            .developerMessage("An error occurred while integrating with the Spotify service.")
            .userMessage("An error occurred while integrating with the Spotify service.")
            .errorCode(6).build();
  }
}