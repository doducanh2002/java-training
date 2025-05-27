package org.aibles.feature.booking.business.dto.response;

import java.time.LocalTime;
import org.aibles.feature.booking.entity.Meal;

public class MealResponse {

  private String id;
  private String dish;
  private Long price;
  private LocalTime mealTime;

  public MealResponse() {
  }

  public MealResponse(String id, String dish, Long price, LocalTime mealTime) {
    this.id = id;
    this.dish = dish;
    this.price = price;
    this.mealTime = mealTime;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDish() {
    return dish;
  }

  public void setDish(String dish) {
    this.dish = dish;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public LocalTime getMealTime() {
    return mealTime;
  }

  public void setMealTime(LocalTime mealTime) {
    this.mealTime = mealTime;
  }

  public static MealResponse from(Meal meal) {
    MealResponse response = new MealResponse();
    response.setId(meal.getId());
    response.setDish(meal.getDish());
    response.setPrice(meal.getPrice());
    response.setMealTime(meal.getMealTime());
    return response;
  }
}
