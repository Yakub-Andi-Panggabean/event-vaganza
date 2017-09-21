package com.special.gift.app.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Base64;

import com.special.gift.app.domain.User;

public class CommonUtil {

  private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);

  public static final String AUTH_HEADER = "isigunyaziso";
  public static final String SALT = "y4ku8s4n9pujan994";
  public static final int PAGING_NUMBER = 5;
  public static final String PACKAGE_VENUE = "venue";
  public static final String PACKAGE_VENDOR = "vendor";
  public static final String VENUE_WIZARD_PACKAGE_CATEGORY = "000";
  public static int IMAGE_REQUEST_TIMEOUT;

  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  public static String generateFakeId(Iterable<User> list) {
    long id = 0;
    for (final User item : list) {
      if (Long.parseLong(item.getUserId()) > id) {
        id = Long.parseLong(item.getUserId());
      }
    }
    return String.valueOf(id + 1);
  }


  public static void findNullOrEmptyValue(String object, String displayField)
      throws RuntimeException {
    if (object == null || object.isEmpty() || object.equals("")) {
      throw new RuntimeException(
          new StringBuilder("field ").append(displayField).append(" may not be empty").toString());
    }
  }

  public static void isValidEmail(String email) throws RuntimeException {
    final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    final Matcher matcher = pattern.matcher(email);
    if (!matcher.matches())
      throw new RuntimeException(new StringBuilder(email).append(" is not valid email").toString());
  }

  public static String convertIntoCurrencyFormat(int price) {
    final double dPrice = price;
    final NumberFormat formatter = NumberFormat.getCurrencyInstance();
    final DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    dfs.setCurrencySymbol("Rp ");
    ((DecimalFormat) formatter).setDecimalFormatSymbols(dfs);
    if (Math.round(dPrice * 100) % 100 == 0) {
      formatter.setMaximumFractionDigits(0);
    }
    return formatter.format(dPrice);
  }


  public static String encodeToBase64(String token) {
    final StringBuilder generatedToken = new StringBuilder();
    final byte[] encodedValue = Base64.encode(token.getBytes());
    return generatedToken.append(new String(encodedValue)).toString();
  }

  public static String decodeToBase64(String token) {
    final StringBuilder generatedToken = new StringBuilder();
    final byte[] decodedValue = Base64.decode(token.getBytes());
    return generatedToken.append(new String(decodedValue)).toString();
  }

  public static boolean httpResponseChecker(String url) {

    boolean result = false;

    try {

      final URL targetUrl = new URL(url);
      final HttpURLConnection connection = (HttpURLConnection) targetUrl.openConnection();
      connection.setConnectTimeout(IMAGE_REQUEST_TIMEOUT);
      final int response = connection.getResponseCode();
      result = response == 200 || response == 201;
    } catch (final Exception ex) {

      log.debug("an exception occured with when trying to connect to {} with message message : {}",
          url, ex.getMessage());

      result = false;
    }

    log.debug(" check url : {} and response : {}", url, result);
    return result;

  }



  public static String encryptHashSHA(String password) {
    StringBuffer hexString = null;
    try {
      if (password != null) {
        if (isValidSHA256(password))
          return password;

        final MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        final byte byteData[] = md.digest();

        // convert the byte to hex format
        hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
          final String hex = Integer.toHexString(0xff & byteData[i]);
          if (hex.length() == 1)
            hexString.append('0');
          hexString.append(hex);
        }
      }
    } catch (final Exception e) {
      e.printStackTrace();
      System.out.println("Error generate encrypt SHA-256 : " + e.getMessage());
    }
    return (hexString != null) ? hexString.toString() : null;
  }


  public static boolean isValidSHA256(String s) {
    return s.matches("^[a-fA-F0-9]{64}$");
  }

}
