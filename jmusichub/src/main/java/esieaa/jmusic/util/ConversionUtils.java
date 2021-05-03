package esieaa.jmusic.util;

import esieaa.jmusic.Categorie;
import esieaa.jmusic.Genre;
import esieaa.jmusic.Langues;
import esieaa.jmusic.ResourceType;
import esieaa.jmusic.exception.FormatInconnuException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * utils class for handling type conversion
 */
public class ConversionUtils {
  public static final String DATE_FORMAT = "dd/MM/yyyy";

  public static <T> Object convertToEnumVal(Class<T> clazz, String value) {
    if (ResourceType.class.equals(clazz)) {
      return enumVal(ResourceType.values(), value);
    }
    else if (Genre.class.equals(clazz)) {
      return enumVal(Genre.values(), value);
    }
    else if (Langues.class.equals(clazz)) {
      return enumVal(Langues.values(), value);
    }
    else if (Categorie.class.equals(clazz)) {
      return enumVal(Categorie.values(), value);
    }
    return null;
  }

  public static Object toEnumFromVal(Enum[] values, String val) {
    return Arrays.stream(values).filter(v -> v.toString().equals(val)).findFirst().orElse(null);
  }

  public static Date tryConvertDate(String date) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
    try {
      return simpleDateFormat.parse(date);
    } catch (ParseException e) {
      throw new FormatInconnuException(String.format("le format de la date est inconnu %1$s", date));
    }
  }

  public static Integer tryParseToInteger(String someInt) {
    try {
      return Integer.parseInt(someInt);

    } catch (NumberFormatException e) {
      return null;
    }
  }

  public static String formatDate(Date date) {
    return new SimpleDateFormat(DATE_FORMAT).format(date);
  }

  private static Object enumVal(Enum[] values, String val) {
    if (val.startsWith("F")) {
      System.out.println(val);
    }
    return Arrays.stream(values).filter(v -> v.toString().equals(val)).findFirst().orElse(null);
  }
}
