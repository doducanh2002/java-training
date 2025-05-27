package org.aibles.framework.helper;

import static org.aibles.framework.constant.ExcelConstant.ImportAirport.CODE;
import static org.aibles.framework.constant.ExcelConstant.ImportAirport.COUNTRY;
import static org.aibles.framework.constant.ExcelConstant.ImportAirport.NAME;
import static org.aibles.framework.constant.ExcelConstant.ImportAirport.REGION;
import static org.aibles.framework.constant.ExcelConstant.ImportFare.BENEFITS;
import static org.aibles.framework.constant.ExcelConstant.ImportFare.FARE;
import static org.aibles.framework.constant.ExcelConstant.ImportFare.PRICE;
import static org.aibles.framework.constant.ExcelConstant.ImportPlane.CAPACITY;
import static org.aibles.framework.constant.ExcelConstant.ImportPlane.COLUMN;
import static org.aibles.framework.constant.ExcelConstant.ImportPlane.ROW;
import static org.aibles.framework.constant.ExcelConstant.ImportPlane.SEAT_PITCH;
import static org.aibles.framework.constant.ExcelConstant.ImportPlane.SEAT_WIDTH;
import static org.aibles.framework.constant.ExcelConstant.ImportPlane.TRAVEL_CLASS;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.AIRLINE;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.ARRIVAL;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.ARRIVAL_AIRPORT_CODE;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.ARRIVAL_TIME;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.DEPARTURE;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.DEPARTURE_AIRPORT_CODE;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.DEPARTURE_TIME;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.DISH;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.END_DATE;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.FLIGHTS;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.FLIGHT_NUMBER;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.ID;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.IS_STOP_STATION;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.MEAL;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.MEAL_TIME;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.MILES;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.PLANE;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.START_DATE;
import static org.aibles.framework.constant.ExcelConstant.ImportTrip.STATUS;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.aibles.adapter.helper.ExcelHelper;
import org.aibles.bussiness.dto.ImportAirportDTO;
import org.aibles.bussiness.dto.ImportFareDTO;
import org.aibles.bussiness.dto.ImportPermissionDTO;
import org.aibles.bussiness.dto.ImportPromotionDTO;
import org.aibles.bussiness.dto.ImportSeatDTO;
import org.aibles.bussiness.dto.ImportTripDTO;
import org.aibles.framework.constant.ExcelConstant.ImportPromotion;
import org.aibles.framework.error_handle.ImportFileExcelErrorException;
import org.aibles.framework.error_handle.InvalidHeaderExcelException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class ExcelHelperImpl implements ExcelHelper {

  @Override
  public List<ImportPermissionDTO> importPermission(InputStream inputStream) {

    List<ImportPermissionDTO> invoiceRecords = new ArrayList<>();

    try (Workbook workbook = new XSSFWorkbook(inputStream)) {
      Sheet sheet = workbook.getSheetAt(0);
      for (Row row : sheet) {
        if (row.getRowNum() == 0) {
          continue;
        }

        ImportPermissionDTO invoiceRecord = new ImportPermissionDTO();
        invoiceRecord.setId(String.valueOf(row.getRowNum()));
        invoiceRecord.setEndpoint(row.getCell(0).getStringCellValue());
        invoiceRecord.setMethod(row.getCell(1).getStringCellValue());
        invoiceRecord.setPermission(row.getCell(2).getStringCellValue());
        invoiceRecord.setRole(row.getCell(3).getStringCellValue());
        invoiceRecord.setDescription(row.getCell(4).getStringCellValue());

        invoiceRecords.add(invoiceRecord);
      }
    } catch (NullPointerException | IOException e) {
      log.error("Error while reading input stream", e);
      throw new ImportFileExcelErrorException();
    }
    return invoiceRecords;
  }

  @Override
  public List<ImportAirportDTO> importAirport(InputStream inputStream) {

    List<ImportAirportDTO> invoiceRecords = new ArrayList<>();
    Map<String, Integer> headerMap = new HashMap<>();

    try (Workbook workbook = new XSSFWorkbook(inputStream)) {
      Sheet sheet = workbook.getSheetAt(0);

      Row headerRow = sheet.getRow(0);
      for (Cell cell : headerRow) {
        headerMap.put(cell.getStringCellValue().trim().toLowerCase(), cell.getColumnIndex());
      }

      for (Row row : sheet) {
        if (row.getRowNum() == 0) {
          continue;
        }

        ImportAirportDTO invoiceRecord = new ImportAirportDTO();
        invoiceRecord.setId(UUID.randomUUID().toString());
        invoiceRecord.setName(getCellValueAsString(row.getCell(headerMap.get(NAME))));
        invoiceRecord.setCode(getCellValueAsString(row.getCell(headerMap.get(CODE))));
        invoiceRecord.setCountry(getCellValueAsString(row.getCell(headerMap.get(COUNTRY))));
        invoiceRecord.setRegion(getCellValueAsString(row.getCell(headerMap.get(REGION))));

        invoiceRecords.add(invoiceRecord);
      }
    } catch (NullPointerException | IOException e) {
      log.error("Error while reading input stream", e);
      throw new ImportFileExcelErrorException();
    }
    return invoiceRecords;
  }

  @Override
  public List<ImportSeatDTO> importSeat(InputStream inputStream) {
    List<ImportSeatDTO> invoiceRecords = new ArrayList<>();
    Map<String, Integer> headerMap = new HashMap<>();

    try (Workbook workbook = new XSSFWorkbook(inputStream)) {
      Sheet sheet = workbook.getSheetAt(0);

      Row headerRow = sheet.getRow(0);
      if (headerRow != null) {
        for (Cell cell : headerRow) {
          headerMap.put(cell.getStringCellValue().trim().toLowerCase(), cell.getColumnIndex());
        }
      } else {
        throw new InvalidHeaderExcelException();
      }

      String[] requiredHeaders = {TRAVEL_CLASS, CAPACITY, ROW, COLUMN, SEAT_PITCH, SEAT_WIDTH};
      if (headerMap.size() < requiredHeaders.length) {
        throw new InvalidHeaderExcelException();
      }
      for (String header : requiredHeaders) {
        if (!headerMap.containsKey(header.toLowerCase())) {
          throw new InvalidHeaderExcelException();
        }
      }

      String lastTravelClass = null;
      int lastCapacity = 0;

      for (Row row : sheet) {
        if (row.getRowNum() == 0) {
          continue;
        }

        ImportSeatDTO invoiceRecord = new ImportSeatDTO();
        invoiceRecord.setId(UUID.randomUUID().toString());

        String travelClass = getCellValueAsString(row.getCell(headerMap.get(TRAVEL_CLASS)));
        if (travelClass != null && !travelClass.isEmpty()) {
          lastTravelClass = travelClass;
        } else if (lastTravelClass == null) {
          throw new ImportFileExcelErrorException();
        }
        invoiceRecord.setNameTravelClass(lastTravelClass);

        int capacity = getCellValueAsInt(row.getCell(headerMap.get(CAPACITY)));
        if (capacity != 0) {
          lastCapacity = capacity;
        } else if (lastCapacity == 0) {
          throw new ImportFileExcelErrorException();
        }
        invoiceRecord.setCapacity(lastCapacity);

        invoiceRecord.setSeatRow(validateAndGetInt(row, headerMap.get(ROW), "Row"));
        invoiceRecord.setSeatColumn(validateAndGetString(row, headerMap.get(COLUMN), "Column"));
        invoiceRecord.setSeatPitch(validateAndGetInt(row, headerMap.get(SEAT_PITCH), "Seat Pitch"));
        invoiceRecord.setSeatWidth(validateAndGetInt(row, headerMap.get(SEAT_WIDTH), "Seat Width"));

        invoiceRecords.add(invoiceRecord);
      }
    } catch (NullPointerException | IOException e) {
      log.error("Error while reading input stream", e);
      throw new ImportFileExcelErrorException();
    }
    return invoiceRecords;
  }

  private String validateAndGetString(Row row, int cellIndex, String fieldName) {
    String value = getCellValueAsString(row.getCell(cellIndex));
    if (value == null || value.trim().isEmpty()) {
      throw new ImportFileExcelErrorException();
    }
    return value;
  }

  private int validateAndGetInt(Row row, int cellIndex, String fieldName) {
    int value = getCellValueAsInt(row.getCell(cellIndex));
    if (value == 0) {
      throw new ImportFileExcelErrorException();
    }
    return value;
  }

  private boolean isValidStringField(String value) {
    return value != null && !value.trim().isEmpty();
  }

  private boolean isValidIntField(int value) {
    return value != 0;
  }

  @Override
  public List<ImportFareDTO> importFare(InputStream inputStream) {
    List<ImportFareDTO> invoiceRecords = new ArrayList<>();
    Map<String, Integer> headerMap = new HashMap<>();

    try (Workbook workbook = new XSSFWorkbook(inputStream)) {
      Sheet sheet = workbook.getSheetAt(0);

      Row headerRow = sheet.getRow(0);
      if (headerRow != null) {
        for (Cell cell : headerRow) {
          headerMap.put(cell.getStringCellValue().trim().toLowerCase(), cell.getColumnIndex());
        }
      } else {
        throw new InvalidHeaderExcelException();
      }

      if (headerMap.size() != 4) {
        throw new InvalidHeaderExcelException();
      }
      String lastTravelClass = null;
      long lastPrice = 0;
      String lastFace = null;
      for (Row row : sheet) {
        if (row.getRowNum() == 0) {
          continue;
        }

        ImportFareDTO invoiceRecord = new ImportFareDTO();
        invoiceRecord.setId(UUID.randomUUID().toString());

        String travelClass = getCellValueAsString(row.getCell(headerMap.get(TRAVEL_CLASS)));
        if (travelClass != null && !travelClass.isEmpty()) {
          lastTravelClass = travelClass;
        } else {
          travelClass = lastTravelClass;
        }
        invoiceRecord.setNameTravelClass(travelClass);

        long price = getCellValueAsLong(row.getCell(headerMap.get(PRICE)));
        if (price != 0) {
          lastPrice = price;
        } else {
          price = lastPrice;
        }
        invoiceRecord.setPrice(price);

        String face = getCellValueAsString(row.getCell(headerMap.get(FARE)));
        if (face != null && !face.isEmpty()) {
          lastFace = face;
        } else {
          face = lastFace;
        }
        invoiceRecord.setFare(face);

        invoiceRecord.setBenefit(getCellValueAsString(row.getCell(headerMap.get(BENEFITS))));

        invoiceRecords.add(invoiceRecord);
      }
    } catch (NullPointerException | IOException e) {
      log.error("Error while reading input stream", e);
      throw new ImportFileExcelErrorException();
    }
    return invoiceRecords;
  }

  @Override
  @Transactional
  public List<ImportTripDTO> importTrip(InputStream inputStream) {
    List<ImportTripDTO> invoiceRecords = new ArrayList<>();
    Map<String, Integer> headerMap = new HashMap<>();

    try (Workbook workbook = new XSSFWorkbook(inputStream)) {
      Sheet sheet = workbook.getSheetAt(0);

      for (int headerRowIndex = 0; headerRowIndex < 3; headerRowIndex++) {
        Row headerRow = sheet.getRow(headerRowIndex);
        for (Cell cell : headerRow) {
          String headerName = cell.getStringCellValue().trim().toLowerCase();
          if (!headerMap.containsKey(headerName)) {
            headerMap.put(headerName, cell.getColumnIndex());
          }
        }
      }
      String lastId = null;
      LocalDateTime lastStartDate = null;
      LocalDateTime lastEndDate = null;
      String lastDeparture = null;
      String lastArrival = null;
      String lastFlights = null;
      String lastFlightNumber = null;
      String lastAirline = null;
      String lastDepartureAirportCode = null;
      String lastArrivalAirportCode = null;
      LocalDateTime lastDepartureTime = null;
      LocalDateTime lastArrivalTime = null;
      long lastMiles = 0;
      String lastStatus = null;
      String lastPlane = null;

      for (int rowIndex = 3; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
          continue;
        }

        ImportTripDTO invoiceRecord = new ImportTripDTO();

        String id = getCellValueAsString(row.getCell(headerMap.get(ID)));
        if (id != null) {
          lastId = id;
        } else {
          id = lastId;
        }
        invoiceRecord.setId(id);

        LocalDateTime startDate = getCellValueAsLocalDateTime(
            row.getCell(headerMap.get(START_DATE)));
        if (startDate != null) {
          lastStartDate = startDate;
        } else {
          startDate = lastStartDate;
        }
        invoiceRecord.setStartDate(startDate);

        LocalDateTime endDate = getCellValueAsLocalDateTime(row.getCell(headerMap.get(END_DATE)));
        if (endDate != null) {
          lastEndDate = endDate;
        } else {
          endDate = lastEndDate;
        }
        invoiceRecord.setEndDate(endDate);

        String departure = getCellValueAsString(row.getCell(headerMap.get(DEPARTURE)));
        if (departure != null && !departure.isEmpty()) {
          lastDeparture = departure;
        } else {
          departure = lastDeparture;
        }
        invoiceRecord.setDeparture(departure);

        String arrival = getCellValueAsString(row.getCell(headerMap.get(ARRIVAL)));
        if (arrival != null && !arrival.isEmpty()) {
          lastArrival = arrival;
        } else {
          arrival = lastArrival;
        }
        invoiceRecord.setArrival(arrival);

        String flights = getCellValueAsString(row.getCell(headerMap.get(FLIGHTS)));
        if (flights != null && !flights.isEmpty()) {
          lastFlights = flights;
        } else {
          flights = lastFlights;
        }
        invoiceRecord.setFlights(flights);

        String flightNumber = getCellValueAsString(row.getCell(headerMap.get(FLIGHT_NUMBER)));
        if (flightNumber != null && !flightNumber.isEmpty()) {
          lastFlightNumber = flightNumber;
        } else {
          flightNumber = lastFlightNumber;
        }
        invoiceRecord.setFlightNumber(flightNumber);

        String airline = getCellValueAsString(row.getCell(headerMap.get(AIRLINE)));
        if (airline != null && !airline.isEmpty()) {
          lastAirline = airline;
        } else {
          airline = lastAirline;
        }
        invoiceRecord.setAirline(airline);

        String departureAirportCode = getCellValueAsString(
            row.getCell(headerMap.get(DEPARTURE_AIRPORT_CODE)));
        if (departureAirportCode != null && !departureAirportCode.isEmpty()) {
          lastDepartureAirportCode = departureAirportCode;
        } else {
          departureAirportCode = lastDepartureAirportCode;
        }
        invoiceRecord.setDepartureAirportCode(departureAirportCode);

        String arrivalAirportCode = getCellValueAsString(
            row.getCell(headerMap.get(ARRIVAL_AIRPORT_CODE)));
        if (arrivalAirportCode != null && !arrivalAirportCode.isEmpty()) {
          lastArrivalAirportCode = arrivalAirportCode;
        } else {
          arrivalAirportCode = lastArrivalAirportCode;
        }
        invoiceRecord.setArrivalAirportCode(arrivalAirportCode);

        LocalDateTime departureTime = getCellValueAsLocalDateTime(
            row.getCell(headerMap.get(DEPARTURE_TIME)));
        if (departureTime != null) {
          lastDepartureTime = departureTime;
        } else {
          departureTime = lastDepartureTime;
        }
        invoiceRecord.setDepartureTime(departureTime);

        LocalDateTime arrivalTime = getCellValueAsLocalDateTime(
            row.getCell(headerMap.get(ARRIVAL_TIME)));
        if (arrivalTime != null) {
          lastArrivalTime = arrivalTime;
        } else {
          arrivalTime = lastArrivalTime;
        }
        invoiceRecord.setArrivalTime(arrivalTime);

        int isStopStation = getCellValueAsInt(row.getCell(headerMap.get(IS_STOP_STATION)));
        invoiceRecord.setIsStopStation(isStopStation);

        long miles = getCellValueAsLong(row.getCell(headerMap.get(MILES)));
        if (miles != 0) {
          lastMiles = miles;
        } else {
          miles = lastMiles;
        }
        invoiceRecord.setMiles(miles);

        String status = getCellValueAsString(row.getCell(headerMap.get(STATUS)));
        if (status != null && !status.isEmpty()) {
          lastStatus = status;
        } else {
          status = lastStatus;
        }
        invoiceRecord.setStatus(status);

        String plane = getCellValueAsString(row.getCell(headerMap.get(PLANE)));
        if (plane != null && !plane.isEmpty()) {
          lastPlane = plane;
        } else {
          plane = lastPlane;
        }
        invoiceRecord.setPlane(plane);

        invoiceRecord.setMeal(getCellValueAsString(row.getCell(headerMap.get(MEAL))));
        invoiceRecord.setDish(getCellValueAsString(row.getCell(headerMap.get(DISH))));
        invoiceRecord.setMealTime(getCellValueAsLocalTime(row.getCell(headerMap.get(MEAL_TIME))));
        invoiceRecord.setPrice(getCellValueAsLong(row.getCell(headerMap.get(PRICE))));

        invoiceRecords.add(invoiceRecord);
      }
    } catch (NullPointerException | IOException e) {
      log.error("Error while reading input stream", e);
      throw new ImportFileExcelErrorException();
    }
    return invoiceRecords;
  }

  @Override
  public List<ImportPromotionDTO> importPromotion(InputStream inputStream) {
    List<ImportPromotionDTO> promotionRecords = new ArrayList<>();

    try (Workbook workbook = new XSSFWorkbook(inputStream)) {
      Sheet sheet = workbook.getSheetAt(0);
      Map<String, Integer> headerMap = new HashMap<>();
      Row headerRow = sheet.getRow(0);
      for (Cell cell : headerRow) {
        headerMap.put(getCellValueAsString(cell).toLowerCase().trim(), cell.getColumnIndex());
      }
      for (Row row : sheet) {
        if (row.getRowNum() == 0) {
          continue;
        }
        ImportPromotionDTO promotionRecord = convertMapToDTO(row, headerMap);
        promotionRecords.add(promotionRecord);
      }
    } catch (NullPointerException | IOException e) {
      log.error("Error while reading input stream", e);
      throw new ImportFileExcelErrorException();
    }
    return promotionRecords;
  }


  private String getCellValueAsString(Cell cell) {
    if (cell == null) {
      return null;
    }

    switch (cell.getCellType()) {
      case STRING:
        return cell.getStringCellValue();
      case NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) {
          return cell.getDateCellValue().toString();
        } else {
          return String.valueOf(cell.getNumericCellValue());
        }
      case BOOLEAN:
        return String.valueOf(cell.getBooleanCellValue());
      case FORMULA:
        return cell.getCellFormula();
      default:
        return null;
    }
  }

  private int getCellValueAsInt(Cell cell) {
    if (cell == null) {
      return 0;
    }
    switch (cell.getCellType()) {
      case NUMERIC:
        return (int) cell.getNumericCellValue();
      case STRING:
        try {
          return Integer.parseInt(cell.getStringCellValue());
        } catch (NumberFormatException e) {
          log.error("Error converting string to int", e);
          return 0;
        }
      default:
        return 0;
    }
  }

  private long getCellValueAsLong(Cell cell) {
    if (cell == null) {
      return 0;
    }
    switch (cell.getCellType()) {
      case NUMERIC:
        return (long) cell.getNumericCellValue();
      case STRING:
        try {
          return Long.parseLong(cell.getStringCellValue());
        } catch (NumberFormatException e) {
          log.error("Error converting string to long", e);
          return 0;
        }
      default:
        return 0;
    }
  }

  private LocalDateTime getCellValueAsLocalDateTime(Cell cell) {
    if (cell == null) {
      return null;
    }

    switch (cell.getCellType()) {
      case NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) {
          Date date = cell.getDateCellValue();
          if (date != null) {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
          } else {
            log.error("The date retrieved from the cell is null.");
            return null;
          }
        }
        break;
      case STRING:
        try {
          DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
          DateTimeFormatter formatterWithoutTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");
          String cellValue = cell.getStringCellValue();

          if (cellValue != null) {
            if (cellValue.contains(" ")) {
              return LocalDateTime.parse(cellValue, formatterWithTime);
            } else {
              return LocalDate.parse(cellValue, formatterWithoutTime).atStartOfDay();
            }
          } else {
            log.error("The string value retrieved from the cell is null.");
            return null;
          }
        } catch (Exception e) {
          log.error("Error converting string to LocalDateTime: ", e);
          return null;
        }
      default:
        log.warn("Unsupported cell type: {}", cell.getCellType());
        break;
    }
    return null;
  }

  private LocalTime getCellValueAsLocalTime(Cell cell) {
    if (cell == null) {
      return null;
    }
    switch (cell.getCellType()) {
      case NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) {
          LocalDateTime dateTime = cell.getLocalDateTimeCellValue();
          return dateTime.toLocalTime();
        }
        break;
      case STRING:
        try {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
          return LocalTime.parse(cell.getStringCellValue(), formatter);
        } catch (Exception e) {
          log.error("Error converting string to LocalTime: " + e.getMessage());
          return null;
        }
      default:
        break;
    }
    return null;
  }

  private ImportPromotionDTO convertMapToDTO(Row row, Map<String, Integer> headerMap) {
    ImportPromotionDTO promotionRecord = new ImportPromotionDTO();
    promotionRecord.setCode(getCellValueAsString(row.getCell(headerMap.get(ImportPromotion.CODE))));
    promotionRecord.setDescription(
        getCellValueAsString(row.getCell(headerMap.get(ImportPromotion.DESCRIPTION))));
    promotionRecord.setDiscountPercent(
        getCellValueAsInt(row.getCell(headerMap.get(ImportPromotion.DISCOUNT_PERCENT))));
    promotionRecord.setPlusMiles(
        getCellValueAsLong(row.getCell(headerMap.get(ImportPromotion.PLUS_MILES))));
    promotionRecord.setArchivedMiles(
        getCellValueAsLong(row.getCell(headerMap.get(ImportPromotion.ARCHIVED_MILES))));
    promotionRecord.setGiveAwayDay(
        getCellValueAsLocalDateTime(row.getCell(headerMap.get(ImportPromotion.GIVE_AWAY_DAY))));
    promotionRecord.setExpiredDate(
        getCellValueAsLocalDateTime(row.getCell(headerMap.get(ImportPromotion.EXPIRED_DATE))));
    promotionRecord.setQuantity(
        getCellValueAsLong(row.getCell(headerMap.get(ImportPromotion.QUANTITY))));

    return promotionRecord;
  }

}
