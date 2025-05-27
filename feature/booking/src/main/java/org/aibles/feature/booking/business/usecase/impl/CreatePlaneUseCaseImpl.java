package org.aibles.feature.booking.business.usecase.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.aibles.adapter.helper.ExcelHelper;
import org.aibles.bussiness.dto.ImportSeatDTO;
import org.aibles.feature.booking.adapter.repository.BenefitRepository;
import org.aibles.feature.booking.adapter.repository.PlaneBenefitRepository;
import org.aibles.feature.booking.adapter.repository.PlaneRepository;
import org.aibles.feature.booking.adapter.repository.PlaneTravelClassRepository;
import org.aibles.feature.booking.adapter.repository.SeatRepository;
import org.aibles.feature.booking.adapter.repository.TravelClassRepository;
import org.aibles.feature.booking.business.dto.request.CreatePlaneUseCaseRequest;
import org.aibles.feature.booking.business.exception.BenefitNotFoundException;
import org.aibles.feature.booking.business.usecase.CreatePlaneUseCase;
import org.aibles.feature.booking.entity.Plane;
import org.aibles.feature.booking.entity.PlaneBenefit;
import org.aibles.feature.booking.entity.PlaneTravelClass;
import org.aibles.feature.booking.entity.Seat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreatePlaneUseCaseImpl implements CreatePlaneUseCase {

  private final Logger log = LoggerFactory.getLogger(CreatePlaneUseCaseImpl.class);

  private final PlaneRepository planeRepository;
  private final PlaneBenefitRepository planeBenefitRepository;
  private final BenefitRepository benefitRepository;
  private final ExcelHelper excelHelper;
  private final TravelClassRepository travelClassRepository;
  private final PlaneTravelClassRepository planeTravelClassRepository;
  private final SeatRepository seatRepository;

  public CreatePlaneUseCaseImpl(PlaneRepository planeRepository,
      PlaneBenefitRepository planeBenefitRepository, BenefitRepository benefitRepository,
      ExcelHelper excelHelper, TravelClassRepository travelClassRepository,
      PlaneTravelClassRepository planeTravelClassRepository, SeatRepository seatRepository) {
    this.planeRepository = planeRepository;
    this.planeBenefitRepository = planeBenefitRepository;
    this.benefitRepository = benefitRepository;
    this.excelHelper = excelHelper;
    this.travelClassRepository = travelClassRepository;
    this.planeTravelClassRepository = planeTravelClassRepository;
    this.seatRepository = seatRepository;
  }

  @Override
  public void invoke(CreatePlaneUseCaseRequest request, InputStream inputStream) {

    if (benefitRepository.countExistingIds(request.getBenefitsId()) != request.getBenefitsId().size()) {
      log.error("(invoke)benefitId: {}", request.getBenefitsId());
      throw new BenefitNotFoundException(request.getBenefitsId().toString());
    }

    var plane = new Plane();
    plane.setModel(request.getModel());
    plane.setDescription(request.getDescription());
    plane = planeRepository.create(plane);

    List<PlaneBenefit> planeBenefits = new ArrayList<>();

    for (var benefit : benefitRepository.findAllByIds(request.getBenefitsId()))  {
      var planeBenefit = new PlaneBenefit();
      planeBenefit.setBenefit(benefit);
      planeBenefit.setPlane(plane);
      planeBenefits.add(planeBenefit);
    }

    planeBenefitRepository.saveAll(planeBenefits);

    List<ImportSeatDTO> importSeatDTOS = excelHelper.importSeat(inputStream);
    List<Seat> seats = new ArrayList<>();
    Set<String> travelClassSet = new HashSet<>();
    Map<String, PlaneTravelClass> planeTravelClassMap = new HashMap<>();
    String lowerCaseTravelClass;
    for (ImportSeatDTO importSeatDTO : importSeatDTOS) {
      lowerCaseTravelClass = importSeatDTO.getNameTravelClass().toLowerCase();
      if (!travelClassSet.contains(lowerCaseTravelClass)) {
        travelClassSet.add(lowerCaseTravelClass);
        var travelClass = travelClassRepository.findByName(lowerCaseTravelClass);
        var planeTravelClass = new PlaneTravelClass();
        planeTravelClass.setCapacity(importSeatDTO.getCapacity());
        planeTravelClass.setTravelClass(travelClass.get());
        planeTravelClass.setPlane(plane);
        PlaneTravelClass planeTravelClassEntity = planeTravelClassRepository.save(planeTravelClass);
        planeTravelClassMap.put(lowerCaseTravelClass,
            planeTravelClassEntity);
      }
      var seat = new Seat();
      seat.setSeatRow(importSeatDTO.getSeatRow());
      seat.setSeatColumn(importSeatDTO.getSeatColumn());
      seat.setSeatPitch(importSeatDTO.getSeatPitch());
      seat.setSeatWidth(importSeatDTO.getSeatWidth());
      seat.setPlaneTravelClass(planeTravelClassMap.get(lowerCaseTravelClass));
      seats.add(seat);
    }
    seatRepository.saveAll(seats);
  }
}
