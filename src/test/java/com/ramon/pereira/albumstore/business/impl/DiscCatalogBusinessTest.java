package com.ramon.pereira.albumstore.business.impl;

import com.github.javafaker.Faker;
import com.ramon.pereira.albumstore.business.DiscCatalogBusiness;
import com.ramon.pereira.albumstore.config.SpringTestConfiguration;
import com.ramon.pereira.albumstore.exception.DiscNotFoundException;
import com.ramon.pereira.albumstore.model.enDiscGenre;
import com.ramon.pereira.albumstore.repository.DiscCatalogRepository;
import com.ramon.pereira.albumstore.seed.DiscSeeder;
import com.ramon.pereira.albumstore.services.SpotifyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@ActiveProfiles(profiles = "discCatalogBusiness")
@ContextConfiguration(classes = SpringTestConfiguration.class)
@RunWith(SpringRunner.class)
public class DiscCatalogBusinessTest {

    @MockBean
    private DiscCatalogRepository discCatalogRepository;

    @MockBean
    private SpotifyService spotifyService;

    @Autowired
    private DiscCatalogBusiness discCatalogBusiness;

    private static final Faker faker = new Faker();


    @Test
    public void findByIdExist() {
        Mockito.when(this.discCatalogRepository.findById(1))
                .thenReturn(Optional.of(DiscSeeder.disc(1)));

        var disc = this.discCatalogBusiness.findById(1);

        Assert.assertTrue(disc.isPresent());
        Assert.assertNotNull(disc.get().getId());
        Assert.assertEquals(Integer.valueOf(1), disc.get().getId());
    }

    @Test(expected = DiscNotFoundException.class)
    public void findByIdNotExist() {
        Mockito.when(this.discCatalogRepository.findById(ArgumentMatchers.anyInt()))
                .thenThrow(new DiscNotFoundException());

        this.discCatalogBusiness.findById(1);
    }

    @Test
    public void findByGenreOrderByNameAscSearchResults() {
        Mockito.when(this.discCatalogRepository.findByGenreOrderByNameAsc(ArgumentMatchers.any(enDiscGenre.class),ArgumentMatchers.any(Pageable.class)))
                .thenReturn(Optional.of(Arrays.asList(DiscSeeder.disc(1),DiscSeeder.disc(2))));

        var disks = discCatalogRepository.findByGenreOrderByNameAsc(enDiscGenre.ROCK, PageRequest.of(1, 10));

        Assert.assertTrue(disks.isPresent());
        Assert.assertTrue(disks.get().size() > 0);
    }

    @Test
    public void findByGenreOrderByNameAscEmptyResult() {
        Mockito.when(this.discCatalogRepository.findByGenreOrderByNameAsc(ArgumentMatchers.any(enDiscGenre.class),ArgumentMatchers.any(Pageable.class)))
                .thenReturn(Optional.of(Arrays.asList()));

        var disks = discCatalogRepository.findByGenreOrderByNameAsc(enDiscGenre.POP, PageRequest.of(1, 10));

        Assert.assertTrue(disks.isPresent());
        Assert.assertEquals(0, disks.get().size());
    }

}
