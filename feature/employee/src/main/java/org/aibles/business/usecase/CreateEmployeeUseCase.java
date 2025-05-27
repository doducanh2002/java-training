package org.aibles.business.usecase;

import org.aibles.business.dto.request.CreateEmployeeUseCaseRequest;

public interface CreateEmployeeUseCase {

  void invoke(CreateEmployeeUseCaseRequest request);
}
