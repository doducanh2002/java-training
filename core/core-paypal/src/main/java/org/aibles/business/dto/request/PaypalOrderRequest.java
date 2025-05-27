package org.aibles.business.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.business.dto.paypal.PaymentSource;
import org.aibles.business.dto.paypal.PurchaseUnit;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaypalOrderRequest {

  private String intent;
  private List<PurchaseUnit> purchaseUnits;
  private PaymentSource paymentSource;

}
