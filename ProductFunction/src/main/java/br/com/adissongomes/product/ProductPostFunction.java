package br.com.adissongomes.product;

import static br.com.adissongomes.product.json.JsonParser.fromJson;

import br.com.adissongomes.product.domain.Product;
import br.com.adissongomes.product.exception.BadRequestException;
import br.com.adissongomes.product.exception.BaseException;
import br.com.adissongomes.product.exception.EmptyBodyException;
import br.com.adissongomes.product.exception.InternalException;
import br.com.adissongomes.product.service.ServiceFactory;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handler for POST API gateway event
 */
public class ProductPostFunction implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  private static final Logger log = LogManager.getLogger(ProductPostFunction.class);
  private ServiceFactory serviceFactory;

  public ProductPostFunction() {
    serviceFactory = new ServiceFactory();
  }

  public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
    var response = new APIGatewayProxyResponseEvent();
    try {

      Product product = validate(input.getBody());
      serviceFactory.getProductPostService().handle(product);
      response.setStatusCode(200);

    } catch (BaseException e) {
      response.withBody(e.getMessage())
        .withStatusCode(e.status());
      log.error("Failure to process product: {}", e.getMessage(), e);
    } catch (Exception e) {
      response.withBody(e.getMessage())
          .withStatusCode(500);
      log.error("Failure to process product: {}", e.getMessage(), e);
    }
    return response;
  }

  private Product validate(String body) {
    try {
      Product product = fromJson(body, Product.class);
      if (product == null) {
        throw new EmptyBodyException("Product not defined");
      }
      if (StringUtils.isBlank(product.getSku())) {
        throw new BadRequestException("Missing sku to identify the product");
      }
      if (StringUtils.isBlank(product.getName())) {
        throw new BadRequestException("Missing name");
      }
      if (product.getPrice() < 0.01) {
        throw new BadRequestException("Price should be greather than 0.01");
      }
      return product;
    } catch (JsonSyntaxException e) {
      throw new InternalException("Fail to read product", e);
    }
  }

}
