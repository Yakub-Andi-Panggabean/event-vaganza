package com.special.gift.app.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.special.gift.app.domain.User;

public class CommonUtil {

  public static final String AUTH_HEADER = "isigunyaziso";
  public static final String SALT = "y4ku8s4n9pujan994";
  public static final int PAGING_NUMBER = 5;
  public static final String PACKAGE_VENUE = "venue";
  public static final String PACKAGE_VENDOR = "vendor";
  public static final String VENUE_WIZARD_PACKAGE_CATEGORY = "000";

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

}
