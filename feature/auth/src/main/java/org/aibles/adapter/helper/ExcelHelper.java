package org.aibles.adapter.helper;

import java.io.InputStream;
import java.util.List;
import org.aibles.bussiness.dto.ImportAirportDTO;
import org.aibles.bussiness.dto.ImportFareDTO;
import org.aibles.bussiness.dto.ImportPermissionDTO;
import org.aibles.bussiness.dto.ImportPromotionDTO;
import org.aibles.bussiness.dto.ImportSeatDTO;
import org.aibles.bussiness.dto.ImportTripDTO;

public interface ExcelHelper {

  List<ImportPermissionDTO> importPermission(InputStream inputStream);
  List<ImportAirportDTO> importAirport(InputStream inputStream);
  List<ImportSeatDTO> importSeat(InputStream inputStream);
  List<ImportFareDTO> importFare(InputStream inputStream);
  List<ImportTripDTO> importTrip(InputStream inputStream);
  List<ImportPromotionDTO> importPromotion(InputStream inputStream);
}
