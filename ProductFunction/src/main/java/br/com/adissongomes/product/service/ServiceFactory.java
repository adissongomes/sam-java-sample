package br.com.adissongomes.product.service;

public class ServiceFactory {

  private ProductPostService productPostService;

  public ServiceFactory() {
    // do nothing
  }

  public ProductPostService getProductPostService() {
    if (productPostService == null) {
      productPostService = new ProductPostService();
    }
    return productPostService;
  }

}
