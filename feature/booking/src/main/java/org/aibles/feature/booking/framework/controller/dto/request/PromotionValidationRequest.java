package org.aibles.feature.booking.framework.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public class PromotionValidationRequest {

    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PromotionValidationRequest(String code) {
        this.code = code;
    }

    public PromotionValidationRequest() {
    }
}
