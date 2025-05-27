package org.aibles.feature.booking.business.usecase;

import java.util.List;
import org.aibles.feature.booking.business.dto.response.PlaneResponse;

public interface GetListPlaneUsecase {

  List<PlaneResponse> invoke();

}
