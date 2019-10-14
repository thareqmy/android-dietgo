package com.stantwice.dietgo.service;

import com.google.gson.annotations.SerializedName;

public class PredictResponse {
  @SerializedName("predict")
  private String predict;

  public PredictResponse(String predict) {
    this.predict = predict;
  }

  public String getPredict() {
    return predict;
  }

  public void setPredict(String predict) {
    this.predict = predict;
  }

}
