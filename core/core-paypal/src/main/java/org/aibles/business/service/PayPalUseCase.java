package org.aibles.business.service;

import java.util.List;
import org.aibles.business.dto.request.OrderRequest;
import org.aibles.business.dto.paypal.PaypalOrderDetail;
import org.aibles.business.dto.paypal.PaypalOrderSimple;
import org.aibles.business.dto.response.PaypalCaptureResponse;
import org.aibles.business.dto.response.PaypalRefundResponse;

public interface PayPalUseCase {

  PaypalCaptureResponse captureOrder(String paypalToken);
  PaypalOrderSimple createOrder(List<OrderRequest> orderRequests);
  PaypalRefundResponse refundPayment(String captureId, String orderId, double refundAmount);
  PaypalOrderDetail getOrderDetails(String paypalToken);
}
