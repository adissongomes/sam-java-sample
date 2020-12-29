package br.com.adissongomes.product;

import static br.com.adissongomes.product.PayloadFactory.INVALID_JSON;
import static br.com.adissongomes.product.PayloadFactory.PRODUCT;
import static br.com.adissongomes.product.PayloadFactory.PRODUCT_INVALID_PRICE;
import static br.com.adissongomes.product.PayloadFactory.PRODUCT_NO_NAME;
import static br.com.adissongomes.product.PayloadFactory.PRODUCT_NO_SKU;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.adissongomes.product.service.ProductPostService;
import br.com.adissongomes.product.service.ServiceFactory;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ProductPostFunctionTest {

  static ProductPostFunction function;
  static ProductPostService service;

  @BeforeAll
  static void beforeAll() throws IllegalAccessException {
    function = new ProductPostFunction();
    service = mock(ProductPostService.class);
    setupMocks();
  }

  @Test
  void testHandle() {
    APIGatewayProxyRequestEvent event = createApiEventWithBody(PRODUCT);
    APIGatewayProxyResponseEvent response = function.handleRequest(event, null);
    verify(service).handle(any());
    assertEquals(200, response.getStatusCode());
  }

  @Test
  void testHandleWithInvalidJson() {
    APIGatewayProxyRequestEvent event = createApiEventWithBody(INVALID_JSON);
    APIGatewayProxyResponseEvent response = function.handleRequest(event, null);
    verify(service, never()).handle(any());
    assertEquals(500, response.getStatusCode());
  }

  @Test
  void testHandleWithoutProductName() {
    APIGatewayProxyRequestEvent event = createApiEventWithBody(PRODUCT_NO_NAME);
    APIGatewayProxyResponseEvent response = function.handleRequest(event, null);
    verify(service, never()).handle(any());
    assertEquals(400, response.getStatusCode());
  }

  @Test
  void testHandleWithoutProductSku() {
    APIGatewayProxyRequestEvent event = createApiEventWithBody(PRODUCT_NO_SKU);
    APIGatewayProxyResponseEvent response = function.handleRequest(event, null);
    verify(service, never()).handle(any());
    assertEquals(400, response.getStatusCode());
  }

  @Test
  void testHandleWithWrongProductPrice() {
    APIGatewayProxyRequestEvent event = createApiEventWithBody(PRODUCT_INVALID_PRICE);
    APIGatewayProxyResponseEvent response = function.handleRequest(event, null);
    verify(service, never()).handle(any());
    assertEquals(400, response.getStatusCode());
  }

  private APIGatewayProxyRequestEvent createApiEventWithBody(String body) {
    APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
    event.setBody(body);
    return event;
  }

  private static void setupMocks() throws IllegalAccessException {
    Field[] fields = function.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (field.getType().equals(ServiceFactory.class)) {
        ServiceFactory factory = mock(ServiceFactory.class);
        field.setAccessible(true);
        field.set(function, factory);
        when(factory.getProductPostService()).thenReturn(service);
      }
    }
  }

}