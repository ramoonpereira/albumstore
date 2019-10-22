package com.ramon.pereira.albumstore.business;

import com.ramon.pereira.albumstore.model.Disc;
import com.ramon.pereira.albumstore.model.enDiscGenre;

import lombok.NonNull;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DiscCatalogBusiness {

  Optional<List<Disc>> findByGenreOrderByNameAsc(@NonNull final enDiscGenre genre, @NonNull final Pageable pageable);

  Optional<Disc> findById(@NonNull final Integer id);

  void supplyDiskCatalog();

}
