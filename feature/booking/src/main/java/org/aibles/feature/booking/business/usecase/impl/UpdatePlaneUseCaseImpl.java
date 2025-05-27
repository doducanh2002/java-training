package org.aibles.feature.booking.business.usecase.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.aibles.adapter.helper.ExcelHelper;
import org.aibles.bussiness.dto.ImportSeatDTO;
import org.aibles.feature.booking.adapter.repository.BenefitRepository;
import org.aibles.feature.booking.adapter.repository.PlaneBenefitRepository;
import org.aibles.feature.booking.adapter.repository.PlaneRepository;
import org.aibles.feature.booking.adapter.repository.PlaneTravelClassRepository;
import org.aibles.feature.booking.adapter.repository.SeatRepository;
import org.aibles.feature.booking.adapter.repository.TravelClassRepository;
import org.aibles.feature.booking.business.dto.request.UpdatePlaneUseCaseRequest;
import org.aibles.feature.booking.business.exception.PlaneNotFoundException;
import org.aibles.feature.booking.business.usecase.UpdatePlaneUseCase;
import org.aibles.feature.booking.entity.Plane;
import org.aibles.feature.booking.entity.PlaneBenefit;
import org.aibles.feature.booking.entity.PlaneTravelClass;
import org.aibles.feature.booking.entity.Seat;
import org.aibles.feature.booking.entity.TravelClass;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class UpdatePlaneUseCaseImpl implements UpdatePlaneUseCase {

  private static final Logger log = LoggerFactory.getLogger(UpdatePlaneUseCaseImpl.class);

  private final PlaneRepository planeRepository;
  private final BenefitRepository benefitRepository;
  private final PlaneBenefitRepository planeBenefitRepository;
  private final SeatRepository seatRepository;
  private final ExcelHelper excelHelper;
  private final TravelClassRepository travelClassRepository;
  private final PlaneTravelClassRepository planeTravelClassRepository;

  public UpdatePlaneUseCaseImpl(
      PlaneRepository planeRepository,
      BenefitRepository benefitRepository,
      PlaneBenefitRepository planeBenefitRepository,
      SeatRepository seatRepository,
      ExcelHelper excelHelper,
      TravelClassRepository travelClassRepository,
      PlaneTravelClassRepository planeTravelClassRepository) {
    this.planeRepository = planeRepository;
    this.benefitRepository = benefitRepository;
    this.planeBenefitRepository = planeBenefitRepository;
    this.seatRepository = seatRepository;
    this.excelHelper = excelHelper;
    this.travelClassRepository = travelClassRepository;
    this.planeTravelClassRepository = planeTravelClassRepository;
  }

  public void invoke(String planeId, UpdatePlaneUseCaseRequest request, InputStream inputStream) {

    Plane plane = planeRepository.findByPlaneId(planeId);

    if (Objects.isNull(plane)) {
      throw new PlaneNotFoundException(planeId);
    }

    plane.setModel(request.getModel());
    plane.setDescription(request.getDescription());

    plane = planeRepository.update(plane);

    Optional<Plane> optionalPlane = planeRepository.findById(planeId);
    if (optionalPlane.isEmpty()) {
      List<PlaneBenefit> planeBenefits = new ArrayList<>();
      for (var benefit : benefitRepository.findAllByIds(request.getBenefitsId())) {
        var planeBenefit = new PlaneBenefit();
        planeBenefit.setBenefit(benefit);
        planeBenefit.setPlane(plane);
        planeBenefits.add(planeBenefit);
      }
      planeBenefitRepository.saveAll(planeBenefits);
    }

    List<ImportSeatDTO> importSeatDTOS = excelHelper.importSeat(inputStream);
    Map<String, PlaneTravelClass> planeTravelClassMap = new HashMap<>();
    String lowerCaseTravelClass;

    for (ImportSeatDTO importSeatDTO : importSeatDTOS) {
      lowerCaseTravelClass = importSeatDTO.getNameTravelClass().toLowerCase();

      PlaneTravelClass planeTravelClass;

      if (!planeTravelClassMap.containsKey(lowerCaseTravelClass)) {
        Optional<PlaneTravelClass> planeTravelClassOptional =
            planeTravelClassRepository.findByNameAndPlane(lowerCaseTravelClass, planeId);

        if (planeTravelClassOptional.isPresent()) {
          planeTravelClass = planeTravelClassOptional.get();
          planeTravelClass.setCapacity(importSeatDTO.getCapacity());
          planeTravelClass = planeTravelClassRepository.save(planeTravelClass); // Update the entity
        } else {
          Optional<TravelClass> travelClass =
              travelClassRepository.findByName(lowerCaseTravelClass);
          planeTravelClass = new PlaneTravelClass();
          planeTravelClass.setCapacity(importSeatDTO.getCapacity());
          planeTravelClass.setTravelClass(travelClass.orElse(null));
          planeTravelClass.setPlane(plane);
          planeTravelClass = planeTravelClassRepository.save(planeTravelClass);
        }

        planeTravelClassMap.put(lowerCaseTravelClass, planeTravelClass);
      } else {
        planeTravelClass = planeTravelClassMap.get(lowerCaseTravelClass);
        planeTravelClass.setCapacity(importSeatDTO.getCapacity());
        planeTravelClass = planeTravelClassRepository.save(planeTravelClass);
      }

      Optional<Seat> seatOptional =
          seatRepository.findByPlaneTravelClassAndSeatRowAndSeatColumn(
              planeTravelClass.getId(), importSeatDTO.getSeatRow(), importSeatDTO.getSeatColumn());

      if (seatOptional.isPresent()) {
        Seat seat = seatOptional.get();
        seat.setSeatPitch(importSeatDTO.getSeatPitch());
        seat.setSeatWidth(importSeatDTO.getSeatWidth());
        seat.setPlaneTravelClass(planeTravelClass);
        seatRepository.save(seat);
      } else {
        Seat newSeat = new Seat();
        newSeat.setSeatRow(importSeatDTO.getSeatRow());
        newSeat.setSeatColumn(importSeatDTO.getSeatColumn());
        newSeat.setSeatPitch(importSeatDTO.getSeatPitch());
        newSeat.setSeatWidth(importSeatDTO.getSeatWidth());
        newSeat.setPlaneTravelClass(planeTravelClass);
        seatRepository.save(newSeat);
      }
    }
  }
}
