package org.aibles.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {

  public static LocalDateTime convertStringToLocalDateTime(String dateTimeString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
    return LocalDateTime.parse(dateTimeString, formatter);
  }
}
