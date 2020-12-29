package br.com.adissongomes.product.exception;

public class EmptyBodyException extends BadRequestException {

  public EmptyBodyException(String message) {
    super(message);
  }

  public EmptyBodyException(String message, Throwable cause) {
    super(message, cause);
  }
}
