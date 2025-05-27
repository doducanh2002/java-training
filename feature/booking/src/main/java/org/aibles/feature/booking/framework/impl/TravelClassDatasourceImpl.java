package org.aibles.feature.booking.framework.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.adapter.repository.TravelClassRepository;
import org.aibles.feature.booking.business.dto.response.planedetail.SeatResponse;
import org.aibles.feature.booking.business.dto.response.planedetail.TravelClassDetailResponse;
import org.aibles.feature.booking.entity.Benefits;
import org.aibles.feature.booking.entity.Fare;
import org.aibles.feature.booking.entity.Seat;
import org.aibles.feature.booking.entity.TravelClass;
import org.aibles.feature.booking.framework.datasource.TravelClassDatabaseEntity;
import org.aibles.feature.booking.framework.impl.projection.FareBenefitProjection;
import org.aibles.feature.booking.framework.impl.projection.TravelClassProjection;
import org.aibles.feature.booking.framework.repository.FareDatabaseEntityRepository;
import org.aibles.feature.booking.framework.repository.TravelClassDatabaseEntityRepository;
import org.aibles.feature.booking.util.TravelClassMapping;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TravelClassDatasourceImpl implements TravelClassRepository {

  private final TravelClassDatabaseEntityRepository travelClassDatabaseEntityRepository;

  private final FareDatabaseEntityRepository fareDatabaseEntityRepository;

  @Override
  public List<TravelClass> getTravelClassList() {
    List<TravelClassDatabaseEntity> travelClassDatabaseEntities
        = travelClassDatabaseEntityRepository.findAll();
    return TravelClassMapping.convertDatabaseToEntity(travelClassDatabaseEntities);
  }

  @Override
  public List<TravelClass> findDetailByPlaneId(String planeId) {
    List<TravelClassDatabaseEntity> travelClassEntities = travelClassDatabaseEntityRepository
        .findAllByPlaneId(planeId);
    List<TravelClass> travelClasses = new ArrayList<>();
    for (TravelClassDatabaseEntity travelClassDatabaseEntity : travelClassEntities) {
      TravelClass travelClass = TravelClassMapping.convertDatabaseToEntity(
          travelClassDatabaseEntity);
      List<FareBenefitProjection> fareBenefits = fareDatabaseEntityRepository
          .findDetailByTravelClassId(travelClass.getId());
      List<Fare> fares = convertFareProjectionToEntity(fareBenefits);
      travelClass.setFares(fares);
      travelClasses.add(travelClass);
    }
    return travelClasses;
  }

  @Override
  public Optional<TravelClass> findByName(String name) {
    Optional<TravelClassDatabaseEntity> travelClassDatabaseEntity = travelClassDatabaseEntityRepository.findByName(name);
    return travelClassDatabaseEntity.map(TravelClassMapping::convertDatabaseToEntity);
  }

  @Override
  public List<TravelClassDetailResponse> findByPlaneId(String planeId) {
    List<TravelClassProjection> projections = travelClassDatabaseEntityRepository.findByPlaneId(
        planeId);
    return convertTravelClassProjectionToEntity(projections);
  }

  private List<Fare> convertFareProjectionToEntity(List<FareBenefitProjection> projections) {
    Map<String, Fare> fareMap = new HashMap<>();
    Map<String, List<Benefits>> fareBenefitMap = new HashMap<>();
    for (FareBenefitProjection projection : projections) {
      String fareId = projection.getFareId();
      fareMap.computeIfAbsent(fareId, id -> {
        Fare fare = new Fare();
        fare.setId(id);
        fare.setName(projection.getFareName());
        fare.setPrice(projection.getPrice());
        return fare;
      });
      fareBenefitMap.putIfAbsent(fareId, new ArrayList<>());
      Benefits benefits = new Benefits();
      benefits.setId(projection.getBenefitId());
      benefits.setName(projection.getBenefitName());
      fareBenefitMap.get(fareId).add(benefits);
    }
    List<Fare> fares = new ArrayList<>();
    fareMap.keySet().stream().filter(
        fareBenefitMap::containsKey
    ).forEach(fareId -> {
      Fare fare = fareMap.get(fareId);
      List<Benefits> benefits = fareBenefitMap.get(fareId);
      fare.setBenefits(benefits);
      fares.add(fare);
    });
    return fares;
  }

  private List<TravelClassDetailResponse> convertTravelClassProjectionToEntity(
      List<TravelClassProjection> projections) {
    Map<String, TravelClassDetailResponse> trevelClassMap = new HashMap<>();
    Map<String, List<SeatResponse>> trevelClassSeatMap = new HashMap<>();
    for (TravelClassProjection projection : projections) {
      String travelClassId = projection.getId();
      trevelClassMap.computeIfAbsent(travelClassId, id -> {
        TravelClassDetailResponse travelClass = new TravelClassDetailResponse();
        travelClass.setId(id);
        travelClass.setName(projection.getName());
        return travelClass;
      });
      trevelClassSeatMap.putIfAbsent(travelClassId, new ArrayList<>());
      SeatResponse seat = new SeatResponse();
      seat.setId(projection.getSeatId());
      seat.setSeatRow(projection.getSeatRow());
      seat.setSeatColumn(projection.getSeatColumn());
      seat.setSeatPitch(projection.getSeatPitch());
      seat.setSeatWidth(projection.getSeatWidth());
      trevelClassSeatMap.get(travelClassId).add(seat);
    }

    List<TravelClassDetailResponse> travelClasses = new ArrayList<>();
    trevelClassSeatMap.keySet().stream().filter(
        trevelClassMap::containsKey
    ).forEach(travelClassId -> {
      TravelClassDetailResponse travelClass = trevelClassMap.get(travelClassId);
      List<SeatResponse> seats = trevelClassSeatMap.get(travelClassId);
      travelClass.setSeats(seats);
      travelClasses.add(travelClass);
    });
    return travelClasses;
  }
}
