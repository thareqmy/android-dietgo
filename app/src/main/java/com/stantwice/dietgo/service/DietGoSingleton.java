package com.stantwice.dietgo.service;

import com.google.gson.Gson;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DietGoSingleton {
  private static DietGoService service;

  public static DietGoService getInstance() {
    if (service == null) {
      service = createServiceInstance();
    }
    return service;
  }

  public static DietGoService createServiceInstance() {
    return createServiceInstance(DietGoService.baseUrl);
  }

  public static DietGoService createServiceInstance(String baseUrl) {
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.MINUTES)
        .build();

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(new Gson()))
        .build();

    return retrofit.create(DietGoService.class);
  }
}
