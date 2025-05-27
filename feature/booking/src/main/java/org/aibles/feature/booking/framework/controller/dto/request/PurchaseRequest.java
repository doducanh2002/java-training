package org.aibles.feature.booking.framework.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.feature.booking.framework.validation.ValidateTrip;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {

  @NotBlank(message = "ticketId is required")
  private String ticketId;
  @NotNull(message = "totalPrice is required")
  private Long totalPrice;
  @ValidateTrip
  @NotBlank(message = "tripType is required")
  private String tripType;
  @NotNull(message = "trips is required")
  @Size(min = 1, message = "The list of trips must contain at least one element.")
  @Valid
  private List<TripPurchaseRequest> trips;
  @NotNull(message = "passengerInfo is required")
  @Size(min = 1, message = "The list of passengerInfo must contain at least one element.")
  @Valid
  private List<PassengerInfoPurchaseRequest> passengerInfo;
  @NotNull(message = "tripContact is required")
  @Valid
  private TripContactPurchaseRequest tripContact;
  @NotNull(message = "specialAssistances is required")
  @Size(min = 1, message = "The list of specialAssistances must contain at least one element.")
  private List<String> specialAssistances;
  private String promotion;


}
