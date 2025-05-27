package org.aibles.feature.booking.framework.controller.paypal;

import org.aibles.business.dto.response.PaypalCaptureResponse;
import org.aibles.feature.booking.business.usecase.CapturePurchaseUseCase;
import org.aibles.feature.booking.business.usecase.paypal.PurchaseCancelUsecase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/paypal")
public class IPNController {

  private final CapturePurchaseUseCase capturePurchaseUseCase;
  private final PurchaseCancelUsecase purchaseCancelUsecase;

  public IPNController(CapturePurchaseUseCase capturePurchaseUseCase,
      PurchaseCancelUsecase purchaseCancelUsecase) {
    this.capturePurchaseUseCase = capturePurchaseUseCase;
    this.purchaseCancelUsecase = purchaseCancelUsecase;
  }

  @GetMapping(value = "/success")
  public PaypalCaptureResponse capture(@RequestParam(name = "token") String token) {
    return capturePurchaseUseCase.invoke(token);
  }

  @GetMapping("/cancel")
  public void handleCancel(@RequestParam("token") String paypalToken) {
    purchaseCancelUsecase.invoke(paypalToken);
  }
}
