package org.aibles.feature.booking.adapter.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.aibles.feature.booking.entity.Promotion;

public interface MapHelper {

  String mapListPromotionToString(List<Promotion> ob) throws JsonProcessingException;

  List<Promotion>  mapListPromotionToList(String s) throws JsonProcessingException;

}
