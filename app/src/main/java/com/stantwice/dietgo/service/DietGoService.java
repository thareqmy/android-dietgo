package com.stantwice.dietgo.service;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DietGoService {
  String baseUrl = "http://35.247.133.78/";

  @Multipart
  @POST("/predict")
  Call<PredictResponse> predict(
      @Part MultipartBody.Part file
  );

}


