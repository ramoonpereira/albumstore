package com.ramon.pereira.albumstore.repository;

import com.ramon.pereira.albumstore.model.Sale;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sale, Integer> {
  @EntityGraph(value = "sale.detail", type = EntityGraph.EntityGraphType.LOAD)
  Optional<List<Sale>> findByCreatedAtBetweenOrderByCreatedAtDesc(@NonNull final Date startDate,
                                                                  @NonNull final Date endDate,
                                                                  @NonNull final Pageable pageable);

  @EntityGraph(value = "sale.detail", type = EntityGraph.EntityGraphType.LOAD)
  Optional<Sale> findById(@NonNull final Integer id);
}
