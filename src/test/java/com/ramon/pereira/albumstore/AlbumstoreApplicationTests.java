package com.ramon.pereira.albumstore;

import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlbumstoreApplicationTests {

  @Test
  public void main() {
    AlbumstoreApplication.main(new String[] {"--spring.config.location=classpath:application-test.yml"});
  }
}
