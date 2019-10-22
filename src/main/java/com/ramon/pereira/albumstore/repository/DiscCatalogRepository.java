package com.ramon.pereira.albumstore.repository;


import com.ramon.pereira.albumstore.model.Disc;
import com.ramon.pereira.albumstore.model.enDiscGenre;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscCatalogRepository extends JpaRepository<Disc, Integer> {

    Optional<List<Disc>> findByGenreOrderByNameAsc(@NonNull final enDiscGenre genre, @NonNull final Pageable pageable);
}
