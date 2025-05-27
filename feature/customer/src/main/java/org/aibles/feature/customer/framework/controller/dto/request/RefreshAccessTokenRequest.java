package org.aibles.feature.customer.framework.controller.dto.request;

import lombok.Getter;

@Getter
public class RefreshAccessTokenRequest {
  private String refreshToken;
}
