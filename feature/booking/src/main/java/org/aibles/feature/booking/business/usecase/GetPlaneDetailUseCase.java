package org.aibles.feature.booking.business.usecase;

import org.aibles.feature.booking.business.dto.response.planedetail.PlaneDetailResponse;

public interface GetPlaneDetailUseCase {

  PlaneDetailResponse invoke(String planeId);
}
