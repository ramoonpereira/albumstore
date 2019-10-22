package com.ramon.pereira.albumstore.repository;

import com.ramon.pereira.albumstore.model.CashbackByGenreAndDay;
import com.ramon.pereira.albumstore.model.enDay;
import com.ramon.pereira.albumstore.model.enDiscGenre;


import java.util.Optional;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashbackByGenreAndDayRepository extends JpaRepository<CashbackByGenreAndDay, Integer> {

  Optional<CashbackByGenreAndDay> findByGenreAndDay(@NonNull final enDiscGenre genre, @NonNull final enDay day);
}
