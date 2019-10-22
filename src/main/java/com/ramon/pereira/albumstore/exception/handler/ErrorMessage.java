package com.ramon.pereira.albumstore.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class ErrorMessage {
  private String developerMessage;
  private String userMessage;
  private int errorCode;
}
