package org.aibles.feature.booking.framework.impl.projection;

public interface SeatStatusProjection {

  String getSeatId();

  Integer getSeatRow();

  String getSeatColumn();

  Integer getSeatPitch();

  Integer getSeatWidth();

  boolean getIsReserved();
}