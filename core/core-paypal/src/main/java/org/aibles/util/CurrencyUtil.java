package org.aibles.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.aibles.framework.dto.exchangeRate.ExchangeRateUSD;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class CurrencyUtil {

  private final RestTemplate restTemplate;

  @Value("${exchange-rate-api.url}")
  private String exchangeApiUrl;

  @Value("${exchange-rate-api.api-key}")
  private String apiKey;

  public CurrencyUtil(ApplicationContext applicationContext) {
    this.restTemplate = applicationContext.getBean("restTemplate", RestTemplate.class);
  }

  public double convertToUSD(double amount, String originCurrency) {
    log.info("convertToUSD: {}", amount);
    String currency = "USD";

    BigDecimal amountInVND = BigDecimal.valueOf(amount);

    String exchangeRateUrl = String.format(exchangeApiUrl, apiKey, currency);

    ResponseEntity<ExchangeRateUSD> response =
        restTemplate.getForEntity(exchangeRateUrl, ExchangeRateUSD.class);

    Map<String, Double> rates = Objects.requireNonNull(response.getBody()).getConversionRates();
    Double rate = rates.get(originCurrency);

    if (Objects.nonNull(rate)) {
      BigDecimal exchangeRate = BigDecimal.valueOf(rate);
      BigDecimal amountInUSD = amountInVND.divide(exchangeRate, 2, RoundingMode.HALF_UP);
      return amountInUSD.doubleValue();
    }
    return amount;
  }
}
