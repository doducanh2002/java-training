package org.aibles.feature.booking.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.feature.booking.business.dto.request.RoundTripFilterUseCaseRequest;
import org.aibles.feature.booking.constant.RoundTripDirection;
import org.aibles.feature.booking.framework.validation.ValidateLocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoundTripFilterRequest {

  @NotBlank
  private String departureAirportId;
  @NotBlank
  private String arrivalAirportId;
  @NotBlank
  @ValidateLocalDate
  private String departureDate;
  @NotBlank
  @ValidateLocalDate
  private String arrivalDate;
  @Pattern(regexp = "FORWARD|BACKWARD")
  private String direction;

  public static RoundTripFilterUseCaseRequest toUseCaseRequest(RoundTripFilterRequest request) {
    RoundTripFilterUseCaseRequest useCaseRequest = new RoundTripFilterUseCaseRequest();
    useCaseRequest.setDepartureAirportId(request.getDepartureAirportId());
    useCaseRequest.setArrivalAirportId(request.getArrivalAirportId());
    useCaseRequest.setDepartureDate(LocalDate.parse(request.getDepartureDate()));
    useCaseRequest.setArrivalDate(LocalDate.parse(request.getArrivalDate()));
    useCaseRequest.setDirection(RoundTripDirection.valueOf(request.getDirection()));
    return useCaseRequest;
  }
}
