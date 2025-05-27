package org.aibles.feature.booking.framework.repository;

import org.aibles.feature.booking.framework.datasource.ReservedMealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedMealDatabaseEntityRepository extends JpaRepository<ReservedMealEntity, Long> {

}
