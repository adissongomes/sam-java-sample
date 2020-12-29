package br.com.adissongomes.product.exception;

public abstract class BaseException extends RuntimeException {

  public BaseException(String message) {
    super(message);
  }

  public BaseException(String message, Throwable cause) {
    super(message, cause);
  }

  public abstract int status();
}
