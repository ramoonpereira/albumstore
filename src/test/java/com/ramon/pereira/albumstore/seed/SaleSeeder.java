package com.ramon.pereira.albumstore.seed;

import com.ramon.pereira.albumstore.model.Sale;
import com.ramon.pereira.albumstore.model.enDiscGenre;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import lombok.NonNull;

public class SaleSeeder extends SeederGeneric {

  public static Sale sale(@NonNull final Integer id, @NonNull final BigDecimal totalPrice,
                          @NonNull final Integer quantityItem, @NonNull final BigDecimal itemPrice,
                          @NonNull final BigDecimal totalItemPrice, @NonNull enDiscGenre enDiscGenre,
                          @NonNull final BigDecimal cashBackValueItem, @NonNull final BigDecimal cashBackTotalValue) {
    return Sale.builder()
        .id(id)
        .customerCpf(faker().number().randomNumber(11, false))
        .customerName(faker().bothify("##########"))
        .customerEmail(faker().bothify("##########"))
        .totalPrice(totalPrice)
        .cashBackTotalValue(cashBackTotalValue)
        .items(Arrays.asList(SaleItemSeeder.saleItem(id, quantityItem, itemPrice, totalItemPrice, enDiscGenre, cashBackValueItem)))
        .createdAt(new Date())
        .build();
  }

  public static Sale sale(@NonNull final Integer id) {
    return sale(id, new BigDecimal(faker().number().randomNumber()),
        faker().number().randomDigit(), new BigDecimal(faker().number().randomNumber()),
        new BigDecimal(faker().number().randomNumber()), enDiscGenre.ROCK, new BigDecimal(faker().number().randomNumber())
        , new BigDecimal(faker().number().randomNumber()));
  }

  public static Sale sale(@NonNull final Integer id, @NonNull final BigDecimal totalPrice,
                          @NonNull final Integer quantityItem, @NonNull final BigDecimal itemPrice,
                          @NonNull final BigDecimal totalItemPrice) {
    return sale(id, totalPrice, quantityItem, itemPrice, totalItemPrice, enDiscGenre.ROCK, new BigDecimal(faker().number().randomNumber()), new BigDecimal(faker().number().randomNumber()));
  }

}
