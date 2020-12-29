package br.com.adissongomes.product.exception;

public class InternalException extends BaseException {

  public InternalException(String message) {
    super(message);
  }

  public InternalException(String message, Throwable cause) {
    super(message, cause);
  }

  public int status() {
    return 500;
  }
}
