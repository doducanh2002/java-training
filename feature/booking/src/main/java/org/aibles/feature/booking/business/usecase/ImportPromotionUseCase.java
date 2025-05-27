package org.aibles.feature.booking.business.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.InputStream;

public interface ImportPromotionUseCase {

  void invoke(InputStream inputStream) throws JsonProcessingException;

}
