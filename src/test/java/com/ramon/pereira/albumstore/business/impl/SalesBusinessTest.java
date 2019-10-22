package com.ramon.pereira.albumstore.business.impl;

import com.github.javafaker.Faker;
import com.ramon.pereira.albumstore.business.SalesBusiness;
import com.ramon.pereira.albumstore.config.SpringTestConfiguration;
import com.ramon.pereira.albumstore.exception.IncorrectItemTotalValueException;
import com.ramon.pereira.albumstore.exception.IncorrectTotalValueException;
import com.ramon.pereira.albumstore.exception.SaleNotFoundException;
import com.ramon.pereira.albumstore.model.Sale;
import com.ramon.pereira.albumstore.model.enDay;
import com.ramon.pereira.albumstore.model.enDiscGenre;
import com.ramon.pereira.albumstore.repository.CashbackByGenreAndDayRepository;
import com.ramon.pereira.albumstore.repository.SalesRepository;
import com.ramon.pereira.albumstore.seed.CashbackByGenreAndDaySeeder;
import com.ramon.pereira.albumstore.seed.SaleSeeder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

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

@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@ActiveProfiles(profiles = "salesBusiness")
@ContextConfiguration(classes = SpringTestConfiguration.class)
@RunWith(SpringRunner.class)
public class SalesBusinessTest {

  @MockBean
  private SalesRepository salesRepository;

  @MockBean
  private CashbackByGenreAndDayRepository cashbackByGenreAndDayRepository;

  @Autowired
  private SalesBusiness salesBusiness;

  private static final Faker faker = new Faker();


  @Test
  public void findByIdExist() {
    Mockito.when(this.salesRepository.findById(1))
        .thenReturn(Optional.of(SaleSeeder.sale(1)));

    var sale = this.salesBusiness.findById(1);

    Assert.assertTrue(sale.isPresent());
    Assert.assertNotNull(sale.get().getId());
    Assert.assertEquals(Integer.valueOf(1), sale.get().getId());
  }

  @Test(expected = SaleNotFoundException.class)
  public void findByIdNotExist() {
    Mockito.when(this.salesRepository.findById(ArgumentMatchers.anyInt()))
        .thenThrow(new SaleNotFoundException());

    this.salesBusiness.findById(1);
  }

  @Test
  public void findByCreatedAtBetweenOrderByCreatedAtDescSearchResults() {
    Mockito.when(this.salesRepository.findByCreatedAtBetweenOrderByCreatedAtDesc(ArgumentMatchers.any(Date.class), ArgumentMatchers.any(Date.class), ArgumentMatchers.any(Pageable.class)))
        .thenReturn(Optional.of(Arrays.asList(SaleSeeder.sale(1), SaleSeeder.sale(2))));

    var sales = salesBusiness.findByCreatedAtBetweenOrderByCreatedAtDesc(new Date(), new Date(), PageRequest.of(1, 10));

    Assert.assertTrue(sales.isPresent());
    Assert.assertTrue(sales.get().size() > 0);
  }

  @Test
  public void findByCreatedAtBetweenOrderByCreatedAtDescEmptyResult() {
    Mockito.when(this.salesRepository.findByCreatedAtBetweenOrderByCreatedAtDesc(ArgumentMatchers.any(Date.class), ArgumentMatchers.any(Date.class), ArgumentMatchers.any(Pageable.class)))
        .thenReturn(Optional.of(Arrays.asList()));

    var sales = salesBusiness.findByCreatedAtBetweenOrderByCreatedAtDesc(new Date(), new Date(), PageRequest.of(1, 10));

    Assert.assertTrue(sales.isPresent());
    Assert.assertEquals(0, sales.get().size());
  }

  @Test(expected = IncorrectItemTotalValueException.class)
  public void createIncorrectItemTotalValue() {
    salesBusiness.create(SaleSeeder.sale(1));
  }

  @Test(expected = IncorrectTotalValueException.class)
  public void valideItemTotalValueIncorrect() {
    salesBusiness.create(SaleSeeder.sale(1, new BigDecimal(15.00), 1, new BigDecimal(10.00), new BigDecimal(10.00)));
  }

  @Test
  public void createSale() {
    var sale =
        SaleSeeder.sale(1, new BigDecimal(30.00), 2, new BigDecimal(15.00), new BigDecimal(30.00),
            enDiscGenre.ROCK,new BigDecimal(3.00),new BigDecimal(3.00));

    Mockito.when(this.cashbackByGenreAndDayRepository.findByGenreAndDay(ArgumentMatchers.any(enDiscGenre.class), ArgumentMatchers.any(enDay.class)))
        .thenReturn(Optional.of(CashbackByGenreAndDaySeeder.cashbackByGenreAndDay()));

    Mockito.when(this.salesRepository.saveAndFlush(ArgumentMatchers.any(Sale.class)))
        .thenReturn(sale);

    var saleCreated = salesBusiness.create(sale);

    Assert.assertTrue(saleCreated.isPresent());
    Assert.assertNotNull(saleCreated.get().getId());
    Assert.assertNotNull(saleCreated.get().getTotalPrice());
    Assert.assertNotNull(saleCreated.get().getCashBackTotalValue());
    Assert.assertFalse(saleCreated.get().getItems().isEmpty());

  }

}
