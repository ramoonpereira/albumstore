package com.ramon.pereira.albumstore.resources.v1.dtos.sale;

import com.ramon.pereira.albumstore.model.enDiscGenre;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
public class SaleItemRequestDto {

  @Valid
  @NotNull
  private Integer discId;

  @Valid
  @NotNull
  private String name;

  @Valid
  @NotNull
  private enDiscGenre genre;

  @Valid
  @NotNull
  private Integer quantity;

  @Valid
  @NotNull
  private BigDecimal price;

  @Valid
  @NotNull
  private BigDecimal totalPrice;
}
