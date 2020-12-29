package br.com.adissongomes.product.emitter;

public interface Emitter<T> {
  void emit(T item);
}
