package com.ramon.pereira.albumstore.resources.v1.dtos.sale;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SaleResponseDto {
  private Integer id;
  private String customerName;
  private String customerEmail;
  private Long customerCpf;
  private List<SaleItemResponseDto> items;
  private BigDecimal totalPrice;
  private BigDecimal cashBackTotalValue;
  @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
  private Date createdAt;
}
