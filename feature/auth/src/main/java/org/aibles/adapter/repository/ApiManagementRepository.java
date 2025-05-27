package org.aibles.adapter.repository;

import org.aibles.entity.ApiManagement;

import java.util.List;

public interface ApiManagementRepository {
    void saveAll(List<ApiManagement> apiManagements);

    void deleteAll();
}
