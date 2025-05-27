package org.aibles.feature.customer.business.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.aibles.feature.customer.framework.controller.dto.request.RefreshAccessTokenRequest;

@Getter
@Setter
public class RefreshAccessTokenUseCaseRequest {
  private String refreshToken;
  public static RefreshAccessTokenUseCaseRequest from(RefreshAccessTokenRequest rq) {
    RefreshAccessTokenUseCaseRequest ucRequest = new RefreshAccessTokenUseCaseRequest();
    ucRequest.setRefreshToken(rq.getRefreshToken());
    return ucRequest;
  }
}
