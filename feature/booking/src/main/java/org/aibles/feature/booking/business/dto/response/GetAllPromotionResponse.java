package org.aibles.feature.booking.business.dto.response;

import java.time.LocalDateTime;
import org.aibles.feature.booking.entity.Promotion;

public class GetAllPromotionResponse {

    private String id;
    private String code;
    private String description;
    private LocalDateTime expiredDate;
    private LocalDateTime giveAwayDay;
    private Integer discountPercent;
    private Long archivedMiles;
    private Long plusMiles;
    private Long quantity;

    public GetAllPromotionResponse() {
    }

    public static GetAllPromotionResponse from(Promotion promotion) {
        GetAllPromotionResponse response  = new GetAllPromotionResponse();
        response.setId(promotion.getId());
        response.setCode(promotion.getCode());
        response.setDescription(promotion.getDescription());
        response.setExpiredDate(promotion.getExpiredDate());
        response.setGiveAwayDay(promotion.getGiveAwayDay());
        response.setDiscountPercent(promotion.getDiscountPercent());
        response.setArchivedMiles(promotion.getArchivedMiles());
        response.setPlusMiles(promotion.getPlusMiles());
        response.setQuantity(promotion.getQuantity());
        return response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public LocalDateTime getGiveAwayDay() {
        return giveAwayDay;
    }

    public void setGiveAwayDay(LocalDateTime giveAwayDay) {
        this.giveAwayDay = giveAwayDay;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Long getArchivedMiles() {
        return archivedMiles;
    }

    public void setArchivedMiles(Long archivedMiles) {
        this.archivedMiles = archivedMiles;
    }

    public Long getPlusMiles() {
        return plusMiles;
    }

    public void setPlusMiles(Long plusMiles) {
        this.plusMiles = plusMiles;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}
