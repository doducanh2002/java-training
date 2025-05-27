package org.aibles.feature.customer.framework.repository;

import org.aibles.feature.customer.framework.datasource.CustomerAccountDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAccountDatabaseEntityRepository extends JpaRepository<CustomerAccountDatabaseEntity, Long> {
    List<CustomerAccountDatabaseEntity> findByCustomerId(String customerId);
}
