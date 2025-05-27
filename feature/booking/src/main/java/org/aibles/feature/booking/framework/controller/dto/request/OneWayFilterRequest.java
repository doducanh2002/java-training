package org.aibles.feature.booking.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.feature.booking.business.dto.request.OneWayFilterUseCaseRequest;
import org.aibles.feature.booking.framework.validation.ValidateLocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OneWayFilterRequest {

  @NotBlank
  private String departureAirportId;
  @NotBlank
  private String arrivalAirportId;
  @NotBlank
  @ValidateLocalDate
  private String date;

  public static OneWayFilterUseCaseRequest toUseCaseRequest(OneWayFilterRequest request) {
    OneWayFilterUseCaseRequest useCaseRequest = new OneWayFilterUseCaseRequest();
    useCaseRequest.setDepartureAirportId(request.getDepartureAirportId());
    useCaseRequest.setArrivalAirportId(request.getArrivalAirportId());
    useCaseRequest.setDate(LocalDate.parse(request.getDate()));
    return useCaseRequest;
  }
}
