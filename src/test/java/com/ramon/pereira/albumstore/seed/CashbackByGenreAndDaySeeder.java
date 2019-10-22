package com.ramon.pereira.albumstore.seed;

import com.ramon.pereira.albumstore.model.CashbackByGenreAndDay;
import com.ramon.pereira.albumstore.model.enDay;
import com.ramon.pereira.albumstore.model.enDiscGenre;

import java.math.BigDecimal;

public class CashbackByGenreAndDaySeeder extends SeederGeneric{

  public static CashbackByGenreAndDay cashbackByGenreAndDay() {
    return CashbackByGenreAndDay.builder()
        .day(enDay.FRIDAY)
        .genre(enDiscGenre.ROCK)
        .percentCashBack(new BigDecimal(10.00))
        .build();
  }
}
