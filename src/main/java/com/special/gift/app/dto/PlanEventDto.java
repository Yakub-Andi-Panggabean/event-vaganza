package com.special.gift.app.dto;

import java.io.Serializable;

public class PlanEventDto implements Serializable {


  private static final long serialVersionUID = 4846136954521510368L;

  private String date;
  private String venue;
  private String catering;
  private String decoration;
  private String makeUp;
  private String photography;
  private String eo;
  private String others;
  private String transport;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getVenue() {
    return venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  public String getDecoration() {
    return decoration;
  }

  public void setDecoration(String decoration) {
    this.decoration = decoration;
  }

  public String getMakeUp() {
    return makeUp;
  }

  public void setMakeUp(String makeUp) {
    this.makeUp = makeUp;
  }

  public String getPhotography() {
    return photography;
  }

  public void setPhotography(String photography) {
    this.photography = photography;
  }

  public String getEo() {
    return eo;
  }

  public void setEo(String eo) {
    this.eo = eo;
  }

  public String getOthers() {
    return others;
  }

  public void setOthers(String others) {
    this.others = others;
  }

  public String getTransport() {
    return transport;
  }

  public void setTransport(String transport) {
    this.transport = transport;
  }

  public String getCatering() {
    return catering;
  }

  public void setCatering(String catering) {
    this.catering = catering;
  }

  @Override
  public String toString() {
    return "PlanEventDto [date=" + date + ", venue=" + venue + ", catering=" + catering
        + ", decoration=" + decoration + ", makeUp=" + makeUp + ", photography=" + photography
        + ", eo=" + eo + ", others=" + others + ", transport=" + transport + "]";
  }



}
