package org.aibles.framework.impl;

import org.aibles.adapter.repository.ApiManagementRepository;
import org.aibles.entity.ApiManagement;
import org.aibles.framework.datasource.ApiManagementDatabaseEntity;
import org.aibles.framework.repository.ApiManagementDatabaseEntityRepository;
import org.aibles.util.ApiManagementMapping;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApiManagementDatasourceImpl implements ApiManagementRepository {

    private final ApiManagementDatabaseEntityRepository apiManagementDatabaseEntityRepository;

    public ApiManagementDatasourceImpl(ApiManagementDatabaseEntityRepository apiManagementDatabaseEntityRepository) {
        this.apiManagementDatabaseEntityRepository = apiManagementDatabaseEntityRepository;
    }

    @Override
    public void saveAll(List<ApiManagement> apiManagements) {
        List<ApiManagementDatabaseEntity> apiManagementDatabaseEntities = apiManagements.stream()
                .map(ApiManagementMapping::mapDataSourceToEntity)
                .toList();
        apiManagementDatabaseEntityRepository.saveAll(apiManagementDatabaseEntities);
    }

    @Override
    public void deleteAll() {
        apiManagementDatabaseEntityRepository.deleteAll();
    }
}
