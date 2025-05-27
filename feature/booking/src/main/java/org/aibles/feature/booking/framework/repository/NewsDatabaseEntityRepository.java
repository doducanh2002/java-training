package org.aibles.feature.booking.framework.repository;

import org.aibles.feature.booking.framework.datasource.NewsDatabaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsDatabaseEntityRepository extends JpaRepository<NewsDatabaseEntity, String> {

  Page<NewsDatabaseEntity> findAllByStatus(Boolean status, Pageable pageable);
}
