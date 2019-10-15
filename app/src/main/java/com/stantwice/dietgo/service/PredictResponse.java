package com.stantwice.dietgo.service;

import com.google.gson.annotations.SerializedName;

public class PredictResponse {
  @SerializedName("predict1")
  private String predict1;
  @SerializedName("predict2")
  private String predict2;
  @SerializedName("predict3")
  private String predict3;
  @SerializedName("cal1")
  private String cal1;
  @SerializedName("cal2")
  private String cal2;
  @SerializedName("cal3")
  private String cal3;

  public String getPredict1() {
    return predict1;
  }

  public void setPredict1(String predict1) {
    this.predict1 = predict1;
  }

  public String getPredict2() {
    return predict2;
  }

  public void setPredict2(String predict2) {
    this.predict2 = predict2;
  }

  public String getPredict3() {
    return predict3;
  }

  public void setPredict3(String predict3) {
    this.predict3 = predict3;
  }

  public String getCal1() {
    return cal1;
  }

  public void setCal1(String cal1) {
    this.cal1 = cal1;
  }

  public String getCal2() {
    return cal2;
  }

  public void setCal2(String cal2) {
    this.cal2 = cal2;
  }

  public String getCal3() {
    return cal3;
  }

  public void setCal3(String cal3) {
    this.cal3 = cal3;
  }
}
