package org.aibles.feature.booking.business.usecase;

import java.io.InputStream;
import org.aibles.feature.booking.business.dto.request.CreatePlaneUseCaseRequest;

public interface CreatePlaneUseCase {

  void invoke(CreatePlaneUseCaseRequest request, InputStream inputStream);
}
