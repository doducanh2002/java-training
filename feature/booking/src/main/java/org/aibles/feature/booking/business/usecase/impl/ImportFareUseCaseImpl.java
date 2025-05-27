package org.aibles.feature.booking.business.usecase.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.aibles.adapter.helper.ExcelHelper;
import org.aibles.bussiness.dto.ImportFareDTO;
import org.aibles.feature.booking.adapter.repository.BenefitRepository;
import org.aibles.feature.booking.adapter.repository.FareBenefitRepository;
import org.aibles.feature.booking.adapter.repository.FareRepository;
import org.aibles.feature.booking.adapter.repository.TravelClassRepository;
import org.aibles.feature.booking.business.exception.NameTravelClassNotFoundException;
import org.aibles.feature.booking.business.usecase.ImportFareUseCase;
import org.aibles.feature.booking.entity.Benefits;
import org.aibles.feature.booking.entity.Fare;
import org.aibles.feature.booking.entity.FareBenefit;
import org.aibles.feature.booking.entity.TravelClass;
import org.aibles.framework.error_handle.InvalidExcelFormatException;
import org.assertj.core.util.Strings;

public class ImportFareUseCaseImpl implements ImportFareUseCase {

  private final ExcelHelper excelHelper;
  private final TravelClassRepository travelClassRepository;
  private final FareRepository fareRepository;
  private final BenefitRepository benefitRepository;
  private final FareBenefitRepository fareBenefitRepository;

  public ImportFareUseCaseImpl(ExcelHelper excelHelper, TravelClassRepository travelClassRepository,
      FareRepository fareRepository, BenefitRepository benefitRepository,
      FareBenefitRepository fareBenefitRepository) {
    this.excelHelper = excelHelper;
    this.travelClassRepository = travelClassRepository;
    this.fareRepository = fareRepository;
    this.benefitRepository = benefitRepository;
    this.fareBenefitRepository = fareBenefitRepository;
  }

  @Override
  public void invoke(InputStream inputStream) {
    List<ImportFareDTO> importFares = excelHelper.importFare(inputStream);
    List<FareBenefit> fareBenefits = new ArrayList<>();
    Map<String, Fare> fareMap = new HashMap<>();
    Map<String, Benefits> benefitMap = new HashMap<>();

    for (ImportFareDTO importFareDTO : importFares) {
      if (Strings.isNullOrEmpty(importFareDTO.getNameTravelClass())
          || Strings.isNullOrEmpty(importFareDTO.getFare())
          || Strings.isNullOrEmpty(importFareDTO.getBenefit())) {
        throw new InvalidExcelFormatException();
      }
      String lowerCaseTravelClass = importFareDTO.getNameTravelClass().toLowerCase();
      String lowerCaseFare = importFareDTO.getFare().toLowerCase();
      String benefitName = importFareDTO.getBenefit().toLowerCase();

      Fare fareEntity = fareMap.get(lowerCaseFare);
      if (fareEntity == null) {
        Optional<Fare> existingFare = fareRepository.findByName(lowerCaseFare);
        if (existingFare.isPresent()) {
          fareEntity = existingFare.get();
        } else {
          TravelClass travelClass = travelClassRepository
              .findByName(lowerCaseTravelClass)
              .orElseThrow(() -> new NameTravelClassNotFoundException(lowerCaseTravelClass));
          Fare fare = new Fare();
          fare.setName(importFareDTO.getFare());
          fare.setTravelClass(travelClass);
          fare.setPrice(importFareDTO.getPrice());
          fareEntity = fareRepository.save(fare);
        }
        fareMap.put(lowerCaseFare, fareEntity);
      }

      Benefits benefitEntity = benefitMap.get(benefitName);
      if (benefitEntity == null) {
        Optional<Benefits> existingBenefit = benefitRepository.findByName(benefitName);
        if (existingBenefit.isPresent()) {
          benefitEntity = existingBenefit.get();
        } else {
          Benefits benefit = new Benefits();
          benefit.setName(importFareDTO.getBenefit());
          benefitEntity = benefitRepository.save(benefit);
        }
        benefitMap.put(benefitName, benefitEntity);
      }


        FareBenefit fareBenefit = new FareBenefit();
        fareBenefit.setFare(fareEntity);
        fareBenefit.setBenefit(benefitEntity);
        fareBenefits.add(fareBenefit);

    }


    fareBenefitRepository.saveAll(fareBenefits);
  }
}
