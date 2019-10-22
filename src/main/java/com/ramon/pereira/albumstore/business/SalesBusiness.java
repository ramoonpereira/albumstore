package com.ramon.pereira.albumstore.business;

import com.ramon.pereira.albumstore.model.Sale;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface SalesBusiness {

  Optional<List<Sale>> findByCreatedAtBetweenOrderByCreatedAtDesc(@Param("created_at") @NonNull final Date startDate,
                                                                  @Param("created_at") @NonNull final Date endDate,
                                                                  @NonNull final Pageable pageable);

  Optional<Sale> findById(@NonNull final Integer id);

  Optional<Sale> create(@NonNull final Sale sale);
}
