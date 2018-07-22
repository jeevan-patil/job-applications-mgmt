package xyz.jeevan.jobs.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

  private static DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

  public static Date convertFromString(final String stringDate) {
    try {
      return df.parse(stringDate);
    } catch (ParseException e) {
      return null;
    }
  }

  public static String convertToString(final Date date) {
    return df.format(date);
  }

}
