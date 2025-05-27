package org.aibles.feature.booking.business.usecase;

import java.io.InputStream;
import org.aibles.feature.booking.business.dto.request.UpdatePlaneUseCaseRequest;

public interface UpdatePlaneUseCase {
  void invoke(String planeId, UpdatePlaneUseCaseRequest request, InputStream inputStream);
}
