package org.aibles.feature.booking.business.usecase.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.aibles.adapter.helper.ExcelHelper;
import org.aibles.feature.booking.adapter.repository.AirportRepository;
import org.aibles.bussiness.dto.ImportAirportDTO;
import org.aibles.feature.booking.business.usecase.ImportAirportUseCase;
import org.aibles.feature.booking.entity.Airport;

public class ImportAirportUseCaseImpl implements ImportAirportUseCase {

  private final ExcelHelper excelHelper;
  private final AirportRepository airportRepository;

  public ImportAirportUseCaseImpl(ExcelHelper excelHelper,
      AirportRepository airportRepository) {
    this.excelHelper = excelHelper;
    this.airportRepository = airportRepository;
  }

  @Override
  public void invoke(InputStream inputStream) {
    List<ImportAirportDTO> importAirportDTOS = excelHelper.importAirport(inputStream);

    List<Airport> airports = new ArrayList<>();
    for (ImportAirportDTO importAirportDTO : importAirportDTOS) {
      Optional<Airport> existingAirport = airportRepository.findByCode(importAirportDTO.getCode());

      if (existingAirport.isPresent()) {
        var airport = existingAirport.get();
        airport.setName(importAirportDTO.getName());
        airport.setCode(importAirportDTO.getCode());
        airport.setCountry(importAirportDTO.getCountry());
        airport.setRegion(importAirportDTO.getRegion());
        airports.add(airport);
      } else {
        var airport = new Airport();
        airport.setName(importAirportDTO.getName());
        airport.setCode(importAirportDTO.getCode());
        airport.setCountry(importAirportDTO.getCountry());
        airport.setRegion(importAirportDTO.getRegion());
        airports.add(airport);
      }
    }

    airportRepository.saveAll(airports);

  }
}
