package com.ramon.pereira.albumstore.business.impl;

import com.github.javafaker.Faker;
import com.ramon.pereira.albumstore.business.DiscCatalogBusiness;
import com.ramon.pereira.albumstore.exception.DiscNotFoundException;
import com.ramon.pereira.albumstore.exception.SpotifyAuthenticateException;
import com.ramon.pereira.albumstore.exception.SpotifyIntegrationException;
import com.ramon.pereira.albumstore.model.Disc;
import com.ramon.pereira.albumstore.model.enDiscGenre;
import com.ramon.pereira.albumstore.repository.DiscCatalogRepository;
import com.ramon.pereira.albumstore.services.SpotifyService;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class DiscCatalogBusinessImpl implements DiscCatalogBusiness {

    private static final Faker faker = new Faker();

    @Autowired
    private DiscCatalogRepository discCatalogRepository;

    @Autowired
    private SpotifyService spotifyService;

    @Override
    public Optional<List<Disc>> findByGenreOrderByNameAsc(@NonNull final enDiscGenre genre,
                                                          @NonNull final Pageable pageable) {

        return this.discCatalogRepository.findByGenreOrderByNameAsc(genre, pageable);
    }

    @Override
    public Optional<Disc> findById(@NonNull final Integer id) {
        return Optional.of(this.discCatalogRepository.findById(id).orElseThrow(DiscNotFoundException::new));
    }

    @Override
    public void supplyDiskCatalog() {
        this.authenticateSpotifyService();
        this.createDisksRock();
        this.createDisksMpb();
        this.createDisksClassic();
        this.createDisksPop();
    }

    private void authenticateSpotifyService() {
        try {

            this.spotifyService.spotifyAuthenticate();

        } catch (Exception ex) {

            throw new SpotifyAuthenticateException();

        }
    }

    private void createDisksRock() {
        try {
            var albuns = spotifyService.getRockAlbuns();

            albuns.ifPresent(albumSimplifieds -> albumSimplifieds.forEach(item -> {
                discCatalogRepository.saveAndFlush(Disc.builder()
                        .genre(enDiscGenre.ROCK)
                        .name(item.getName())
                        .price(BigDecimal.valueOf(faker.number().numberBetween(1, 1000)))
                        .build());
            }));

        } catch (Exception ex) {

            throw new SpotifyIntegrationException();
        }
    }

    private void createDisksMpb() {
        try {
            var albuns = spotifyService.getMpbAlbuns();

            albuns.ifPresent(albumSimplifieds -> albumSimplifieds.forEach(item -> {
                discCatalogRepository.saveAndFlush(Disc.builder()
                        .genre(enDiscGenre.MPB)
                        .name(item.getName())
                        .price(BigDecimal.valueOf(faker.number().numberBetween(1, 1000)))
                        .build());
            }));

        } catch (Exception ex) {

            throw new SpotifyIntegrationException();

        }
    }

    private void createDisksClassic() {
        try {
            var albuns = spotifyService.getClassicAlbuns();

            albuns.ifPresent(albumSimplifieds -> albumSimplifieds.forEach(item -> {
                discCatalogRepository.saveAndFlush(Disc.builder()
                        .genre(enDiscGenre.CLASSIC)
                        .name(item.getName())
                        .price(BigDecimal.valueOf(faker.number().numberBetween(1, 1000)))
                        .build());
            }));

        } catch (Exception ex) {

            throw new SpotifyIntegrationException();

        }
    }

    private void createDisksPop() {
        try {
            var albuns = spotifyService.getPopAlbuns();

            albuns.ifPresent(albumSimplifieds -> albumSimplifieds.forEach(item -> {
                discCatalogRepository.saveAndFlush(Disc.builder()
                        .genre(enDiscGenre.POP)
                        .name(item.getName())
                        .price(BigDecimal.valueOf(faker.number().numberBetween(1, 1000)))
                        .build());
            }));

        } catch (Exception ex) {

            throw new SpotifyIntegrationException();

        }
    }

}
