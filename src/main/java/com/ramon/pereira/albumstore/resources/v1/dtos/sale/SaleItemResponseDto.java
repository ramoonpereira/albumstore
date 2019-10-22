package com.ramon.pereira.albumstore.resources.v1.dtos.sale;


import com.ramon.pereira.albumstore.model.enDiscGenre;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleItemResponseDto {
  private Integer id;
  private Integer discId;
  private String name;
  private enDiscGenre genre;
  private Integer quantity;
  private BigDecimal price;
  private BigDecimal totalPrice;
  private BigDecimal cashBackValue;

}
