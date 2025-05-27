package org.aibles.business.usecase;

import org.aibles.business.dto.request.PagingRequest;
import org.aibles.business.dto.response.EmployeeDetailUseCaseResponse;
import org.aibles.business.dto.response.PagingResponse;

public interface GetListEmployeeUseCase {

  PagingResponse<EmployeeDetailUseCaseResponse> invoke(PagingRequest pagingRequest);
}
