package org.aibles.feature.booking.business.dto.response;

import org.aibles.feature.booking.entity.Benefits;

public class BenefitResponse {

    private String id;
    private String name;

    public BenefitResponse() {
    }

    public BenefitResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static BenefitResponse from(Benefits entity) {
        BenefitResponse response = new BenefitResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        return response;
    }
}