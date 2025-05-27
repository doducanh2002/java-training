package org.aibles.bussiness.dto;

import org.aibles.entity.Role;

public class GetAllRoleResponse {
    private String id;
    private String name;

    public GetAllRoleResponse() {
    }

    public GetAllRoleResponse(String id, String name) {
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


    public static GetAllRoleResponse from(Role role) {
        GetAllRoleResponse response = new GetAllRoleResponse();
        response.setId(role.getId());
        response.setName(role.getName());
        return response;
    }
}
