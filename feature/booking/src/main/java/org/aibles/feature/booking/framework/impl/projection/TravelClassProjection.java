package org.aibles.feature.booking.framework.impl.projection;

public interface TravelClassProjection {

  String getId();
  String getName();
  String getSeatId();
  Integer getSeatRow();
  String getSeatColumn();
  Integer getSeatPitch();
  Integer getSeatWidth();
}
