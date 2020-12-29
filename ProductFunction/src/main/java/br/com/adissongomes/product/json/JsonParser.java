package br.com.adissongomes.product.json;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class JsonParser {

  private static final Gson GSON = new GsonBuilder()
      .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
      .create();

  private JsonParser() {
    // do nothing
  }

  public static <T> T fromJson(String json, Class<T> type) {
    return GSON.fromJson(json, type);
  }

  public static String toJson(Object object) {
    return GSON.toJson(object);
  }

}
