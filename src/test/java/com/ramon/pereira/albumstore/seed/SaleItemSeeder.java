package com.ramon.pereira.albumstore.seed;

import com.ramon.pereira.albumstore.model.SaleItem;
import com.ramon.pereira.albumstore.model.enDiscGenre;

import java.math.BigDecimal;

import lombok.NonNull;

public class SaleItemSeeder extends SeederGeneric {

  public static SaleItem saleItem(@NonNull final Integer id, @NonNull final Integer quantityItem,
                                  @NonNull final BigDecimal itemPrice, @NonNull final BigDecimal totalItemPrice,
                                  @NonNull final enDiscGenre enDiscGenre,@NonNull final BigDecimal cashBackValueItem) {
    return SaleItem.builder()
        .id(id)
        .discId(faker().number().randomDigit())
        .genre(enDiscGenre)
        .name(faker().bothify("##########"))
        .price(itemPrice)
        .quantity(quantityItem)
        .totalPrice(totalItemPrice)
        .cashBackValue(cashBackValueItem)
        .build();
  }

}
