package br.com.adissongomes.product;

public final class PayloadFactory {

  public static final String PRODUCT = "{\"sku\": \"ANY-IDENTITY\", \"name\": \"Any product\", \"price\": 1.33}";
  public static final String PRODUCT_INVALID_PRICE = "{\"sku\": \"ANY-IDENTITY\", \"name\": \"Any product\", \"price\": -1.33}";
  public static final String PRODUCT_NO_SKU = "{\"sku\": \"  \", \"name\": \"Any product\", \"price\": -1.33}";
  public static final String PRODUCT_NO_NAME = "{\"sku\": \"ANY-IDENTITY\", \"name\": \"  \", \"price\": -1.33}";
  public static final String INVALID_JSON = "{";

}
