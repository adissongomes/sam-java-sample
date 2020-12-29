package br.com.adissongomes.product.service;

import br.com.adissongomes.product.domain.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductPostService {

  private static final Logger log = LogManager.getLogger(ProductPostService.class);

  public void handle(Product product) {
    log.info("Product received: {}", product);
  }

}
