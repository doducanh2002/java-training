package org.aibles.business.service.impl;

import static org.aibles.business.constant.ApiPayPalConstant.AMOUNT_MOONEY;
import static org.aibles.business.constant.ApiPayPalConstant.FACE_VALUE_USD;
import static org.aibles.business.constant.ApiPayPalConstant.INTENT_CAPTURE;
import static org.aibles.business.constant.ApiPayPalConstant.PATH_CAPTURE;
import static org.aibles.business.constant.ApiPayPalConstant.PATH_CHECKOUT_ORDER;
import static org.aibles.business.constant.ApiPayPalConstant.PATH_PAYMENTS_CAPTURES;
import static org.aibles.business.constant.ApiPayPalConstant.PATH_REFUND;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.aibles.framework.configuration.PaypalConfiguration;
import org.aibles.business.dto.request.OrderRequest;
import org.aibles.business.dto.payment.PaymentExperienceContext;
import org.aibles.business.dto.payment.PaypalDetail;
import org.aibles.business.dto.paypal.PaymentSource;
import org.aibles.business.dto.paypal.PaypalAmount;
import org.aibles.business.dto.paypal.PaypalOrderDetail;
import org.aibles.business.dto.request.PaypalOrderRequest;
import org.aibles.business.dto.paypal.PaypalOrderSimple;
import org.aibles.business.dto.request.PaypalRefundRequest;
import org.aibles.business.dto.paypal.PurchaseUnit;
import org.aibles.business.dto.response.PaypalCaptureResponse;
import org.aibles.business.dto.response.PaypalRefundResponse;
import org.aibles.business.service.PayPalUseCase;
import org.aibles.util.AccessTokenUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PayPalUseCaseImpl implements PayPalUseCase {

  private final RestTemplate restTemplate;

  private final PaypalConfiguration paypalConfiguration;

  private final AccessTokenUtil accessTokenUtil;

  public PayPalUseCaseImpl(ApplicationContext applicationContext, PaypalConfiguration paypalConfiguration,
      AccessTokenUtil accessTokenUtil) {
    this.restTemplate = applicationContext.getBean("paypalRestTemplate", RestTemplate.class);
    this.paypalConfiguration = paypalConfiguration;
    this.accessTokenUtil = accessTokenUtil;
  }

  @Override
  public PaypalCaptureResponse captureOrder(String paypalToken) {
    log.info("(captureOrder)paypalToken: {}", paypalToken);
    String accessToken = accessTokenUtil.getAccessToken();

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(accessToken);
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Void> entity = new HttpEntity<>(headers);

    ResponseEntity<PaypalCaptureResponse> response = restTemplate.postForEntity(
        paypalConfiguration.getBaseUrl() + PATH_CHECKOUT_ORDER + "/" + paypalToken + PATH_CAPTURE,
        entity,
        PaypalCaptureResponse.class
    );
    return response.getBody();
  }

  @Override
  public PaypalOrderSimple createOrder(List<OrderRequest> orderRequests) {
    log.info("(createOrder)orderRequests: {}", orderRequests);
    String accessToken = accessTokenUtil.getAccessToken();
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(accessToken);
    headers.setContentType(MediaType.APPLICATION_JSON);

    List<PurchaseUnit> purchaseUnits = getPurchaseUnits(orderRequests);

    PaymentSource paymentSource = getPaymentSource();


    PaypalOrderRequest paypalOrderRequest = new PaypalOrderRequest(INTENT_CAPTURE,
        purchaseUnits,
        paymentSource);
    HttpEntity<PaypalOrderRequest> entity = new HttpEntity<>(paypalOrderRequest, headers);

    ResponseEntity<PaypalOrderSimple> response = restTemplate.postForEntity(
        paypalConfiguration.getBaseUrl() + PATH_CHECKOUT_ORDER,

        entity,
        PaypalOrderSimple.class
    );

    return response.getBody();
  }

  private List<PurchaseUnit> getPurchaseUnits(List<OrderRequest> orderRequests) {
    log.info("(getPurchaseUnits)orderRequests: {}", orderRequests);
    return orderRequests.stream().map(orderReq -> {
      PaypalAmount amount = new PaypalAmount(FACE_VALUE_USD, String.format(AMOUNT_MOONEY, orderReq.getAmount()));
      PurchaseUnit purchaseUnit = new PurchaseUnit();
      purchaseUnit.setAmount(amount);
      purchaseUnit.setCustomId(orderReq.getOrderId());
      return purchaseUnit;
    }).toList();
  }

  private PaymentSource getPaymentSource() {
    log.info("(getPaymentSource)");
    String successUrl = paypalConfiguration.getTunnelUrl() + paypalConfiguration.getSuccessPath();
    String cancelUrl = paypalConfiguration.getTunnelUrl() + paypalConfiguration.getCancelPath();

    PaymentSource paymentSource = new PaymentSource();

    PaypalDetail paypalDetail = new PaypalDetail();
    PaymentExperienceContext paypalContext = new PaymentExperienceContext();
    paypalContext.setReturnUrl(successUrl);
    paypalContext.setCancelUrl(cancelUrl);
    paypalDetail.setExperienceContext(paypalContext);

    paymentSource.setPaypal(paypalDetail);
    return paymentSource;
  }


  @Override
  public PaypalRefundResponse refundPayment(String captureId, String orderId, double refundAmount) {
    log.info("(refundPayment)captureId: {}, captureId: {}, refundAmount: {}", captureId, orderId,
        refundAmount);
    String accessToken = accessTokenUtil.getAccessToken();

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(accessToken);
    headers.setContentType(MediaType.APPLICATION_JSON);

    PaypalRefundRequest refundRequest = getPaypalRefundRequest(orderId, refundAmount);

    HttpEntity<PaypalRefundRequest> entity = new HttpEntity<>(refundRequest, headers);

    ResponseEntity<PaypalRefundResponse> response = restTemplate.postForEntity(
        paypalConfiguration.getBaseUrl() + PATH_PAYMENTS_CAPTURES + "/" + captureId + PATH_REFUND,
        entity,
        PaypalRefundResponse.class
    );

    return response.getBody();
  }

  private PaypalRefundRequest getPaypalRefundRequest(String orderId, double refundAmount) {
    log.info("(getPaypalRefundRequest)orderId: {}, refundAmount: {}", orderId, refundAmount);
    PaypalRefundRequest refundRequest = new PaypalRefundRequest();
    PaypalAmount amount = new PaypalAmount(FACE_VALUE_USD,
        String.format(AMOUNT_MOONEY, refundAmount));
    refundRequest.setAmount(amount);
    refundRequest.setCustomId(orderId);
    return refundRequest;
  }

  @Override
  public PaypalOrderDetail getOrderDetails(String paypalToken) {
    log.info("(getOrderDetails)paypalToken: {}", paypalToken);
    String accessToken = accessTokenUtil.getAccessToken();

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(accessToken);
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Void> entity = new HttpEntity<>(headers);

    ResponseEntity<PaypalOrderDetail> response = restTemplate.exchange(
        paypalConfiguration.getBaseUrl() + PATH_CHECKOUT_ORDER + "/" + paypalToken,
        HttpMethod.GET,
        entity,
        PaypalOrderDetail.class
    );

    return response.getBody();
  }
}
