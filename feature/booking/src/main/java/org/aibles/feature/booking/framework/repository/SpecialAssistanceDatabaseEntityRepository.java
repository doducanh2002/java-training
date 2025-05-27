package org.aibles.feature.booking.framework.repository;

import org.aibles.feature.booking.framework.datasource.SpecialAssistanceDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialAssistanceDatabaseEntityRepository
    extends JpaRepository<SpecialAssistanceDatabaseEntity, String> {}
