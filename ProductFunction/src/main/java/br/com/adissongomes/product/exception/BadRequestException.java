package br.com.adissongomes.product.exception;

public class BadRequestException extends BaseException {

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  public int status() {
    return 400;
  }
}
