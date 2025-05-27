package org.aibles.feature.booking.adapter.repository;

import org.aibles.feature.booking.entity.PassengerInfo;

import java.util.List;

public interface PassengerInfoRepository {

  boolean existsByTicketIdAndLastName(String ticketId, String lastName);

  List<PassengerInfo> getPassengerInfoByTicketId(String ticketId);

  List<PassengerInfo> saveAll(List<PassengerInfo> passengerInfos);
}
