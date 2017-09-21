package com.special.gift.app.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class HttpUtil {

  private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

  public static String API_DOMAIN;

  private static String API_LOGIN = "api/user/login";
  private static String API_SINGLE_BOOKING = "api/booking-transaction/booking-request";
  private static String API_GROUP_BOOKING = "api/booking-transaction/group-booking-request";
  private static String API_TRANSACTION_HISTORY = "api/booking-transaction";
  private static String API_TRANSACTION_HISTORY_DETAIL = "api/vendor/get-history-detail";

  public static JsonNode getApiToken(String username, String password) {

    final Map<String, String> requestBody = new HashMap<>();
    requestBody.put("user_name", username);
    requestBody.put("user_password", password);

    final Map<String, String> header = new HashMap<>();
    header.put("Content-type", "application/json");

    try {
      final String json = new ObjectMapper().writeValueAsString(requestBody);

      return createPostRequest(API_DOMAIN + API_LOGIN, header, json);
    } catch (final Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }

  }


  public static JsonNode createSingleBookingRequest(String token, String id, String body) {

    final Map<String, String> headers = new HashMap<>();
    headers.put("Content-type", "application/json");
    headers.put("Access-Token", token);
    headers.put("User-Id", id);

    log.debug("request body: {}", body);

    try {
      return createPostRequest(API_DOMAIN + API_SINGLE_BOOKING, headers, body);
    } catch (final UnirestException e) {
      e.printStackTrace();
      return null;
    }
  }



  public static JsonNode createGroupBookingRequest(String token, String id, String body) {

    final Map<String, String> headers = new HashMap<>();
    headers.put("Content-type", "application/json");
    headers.put("Access-Token", token);
    headers.put("User-Id", id);

    log.debug("request body: {}", body);

    try {
      return createPostRequest(API_DOMAIN + API_GROUP_BOOKING, headers, body);
    } catch (final UnirestException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static JsonNode findTransactionHistory(String token, String id) {
    final Map<String, String> headers = new HashMap<>();
    headers.put("Content-type", "application/json");
    headers.put("Access-Token", token);
    headers.put("User-Id", id);

    final String body = "{}";

    try

    {
      return createPostRequest(API_DOMAIN + API_TRANSACTION_HISTORY, headers, body);
    } catch (final UnirestException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static JsonNode findHistoryDetail(String token, String id, String eventId) {
    final Map<String, String> headers = new HashMap<>();
    headers.put("Content-type", "application/json");
    headers.put("Access-Token", token);
    headers.put("User-Id", id);

    try

    {
      return createGetRequest(API_DOMAIN + API_TRANSACTION_HISTORY_DETAIL + "/" + eventId, headers);
    } catch (final UnirestException e) {
      e.printStackTrace();
      return null;
    }
  }



  private static JsonNode createPostRequest(String url, Map<String, String> headers, String body)
      throws UnirestException {
    return Unirest.post(url).headers(headers).body(body).asJson().getBody();
  }


  private static JsonNode createGetRequest(String url, Map<String, String> headers)
      throws UnirestException {
    return Unirest.get(url).headers(headers).asJson().getBody();
  }

  // public static void main(String[] args) {
  // final JsonNode json = new JsonNode(
  // getApiToken("fery_dirgan@yahoo.com", "fery123").getObject().get("data").toString());
  // System.out.println(json.getObject().get("access_token"));
  // }

}
