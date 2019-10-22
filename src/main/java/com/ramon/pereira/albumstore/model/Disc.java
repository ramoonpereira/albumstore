package com.ramon.pereira.albumstore.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "catalog_disks", schema = "albumstore")
public class Disc implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String name;

  @Column
  @Enumerated(EnumType.STRING)
  private enDiscGenre genre;

  @Column
  private BigDecimal price;


  @Column
  private Date createdAt;

  @PrePersist
  protected void prePersist() {
    createdAt = new Date();
  }
}
