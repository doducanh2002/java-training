package org.aibles.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.collections4.MapUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class SortUtil {

  private SortUtil() {}

  private static final String SORT_PAIR_DELIMETER = ":";

  public static Pageable makePageable(int page, int size, String[] sortBy, Set<String> fieldList) {
    List<Sort.Order> orders = createOrders(createSortMap(sortBy), fieldList);
    return PageRequest.of(page -1, size, Sort.by(orders));
  }

  public static Map<String, Direction> createSortMap(String[] sortBy) {
    if (sortBy == null) {
      return getSortDefault();
    }
    Map<String, Direction> sortMap = new LinkedHashMap<>();
    for (String s : sortBy) {
      String[] sortPair = s.split(SORT_PAIR_DELIMETER);
      if (sortPair[0] == null || sortPair[1] == null) {
        continue;
      }
      Direction direction = Direction.valueOf(sortPair[1].toUpperCase());
      sortMap.put(sortPair[0], direction);
    }
    return sortMap;
  }

  public static Map<String, Direction> getSortDefault() {
    return Map.of("createdAt", Direction.DESC);
  }

  public static List<Sort.Order> createOrders(Map<String, Direction> sorts, Set<String> fieldList) {
    return Optional.ofNullable(sorts)
        .filter(MapUtils::isNotEmpty)
        .map(Map::entrySet)
        .map(entries -> entries.stream()
            .filter(e -> fieldList.contains(e.getKey()))
            .map(e -> new Sort.Order(e.getValue(), e.getKey()))
            .toList())
        .orElse(Collections.emptyList());
  }
}
