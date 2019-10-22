package com.ramon.pereira.albumstore.model;

import lombok.Getter;

@Getter
public enum enDay {
  MONDAY(1),
  TUESDAY(2),
  WEDNESDAY(3),
  THURSDAY(4),
  FRIDAY(5),
  SATURDAY(6),
  SUNDAY(7);

  private Integer id;

  enDay(Integer id) {
    this.id = id;
  }
}