package com.ramon.pereira.albumstore.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "cashback_by_genre_and_day", schema = "albumstore")
public class CashbackByGenreAndDay {

  @Id
  private Integer id;

  @Column
  @Enumerated(EnumType.STRING)
  private enDiscGenre genre;

  @Column
  @Enumerated(EnumType.STRING)
  private enDay day;

  @Column
  private BigDecimal percentCashBack;
}
