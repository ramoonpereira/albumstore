package com.ramon.pereira.albumstore.resources.v1.dtos.disc;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ramon.pereira.albumstore.model.enDiscGenre;

import java.math.BigDecimal;
import java.util.Date;

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
public class DiscResponseDto {
  private Integer id;
  private String name;
  private enDiscGenre genre;
  private BigDecimal price;
  @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
  private Date createdAt;
}
