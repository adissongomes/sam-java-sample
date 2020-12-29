package br.com.adissongomes.product.domain;

import java.util.Objects;

public class Product {

  private String sku;
  private String name;
  private String description;
  private double purchasePrice;
  private double price;

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(double purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(sku, product.sku);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sku);
  }

  @Override
  public String toString() {
    return "Product{" +
        "sku='" + sku + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
