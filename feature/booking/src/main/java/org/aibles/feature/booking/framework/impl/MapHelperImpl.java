package org.aibles.feature.booking.framework.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.aibles.feature.booking.adapter.repository.MapHelper;
import org.aibles.feature.booking.entity.Promotion;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MapHelperImpl implements MapHelper {

  private final ObjectMapper mapper;


  @Override
  public String mapListPromotionToString(List<Promotion> ob) throws JsonProcessingException {
    return mapper.writeValueAsString(ob);
  }

  @Override
  public List<Promotion> mapListPromotionToList(String s) throws JsonProcessingException {
    if (s == null || s.trim().isEmpty()) {
      throw new IllegalArgumentException("Input string cannot be null or empty");
    }
    if (s.startsWith("\"") && s.endsWith("\"")) {
      s = s.substring(1, s.length() - 1).replace("\\\"", "\"");
    }

    return mapper.readValue(s, new TypeReference<List<Promotion>>() {});
  }

}
