package org.aibles.business.usecase;

import org.aibles.business.dto.request.ChangePasswordFirstLoginUseCaseRequest;

public interface ChangePasswordFirstTimeLoginUseCase {

  void invoke(ChangePasswordFirstLoginUseCaseRequest request);
}
