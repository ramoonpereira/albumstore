package com.ramon.pereira.albumstore.config;

import com.ramon.pereira.albumstore.business.DiscCatalogBusiness;
import com.ramon.pereira.albumstore.business.SalesBusiness;
import com.ramon.pereira.albumstore.business.impl.DiscCatalogBusinessImpl;
import com.ramon.pereira.albumstore.business.impl.SalesBusinessImpl;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
public class SpringTestConfiguration {

  @Bean
  @Profile("discCatalogBusiness")
  public DiscCatalogBusiness discCatalogBusiness() {
    return new DiscCatalogBusinessImpl();
  }

  @Bean
  @Profile("salesBusiness")
  public SalesBusiness salesBusiness() { return new SalesBusinessImpl(); }
}
