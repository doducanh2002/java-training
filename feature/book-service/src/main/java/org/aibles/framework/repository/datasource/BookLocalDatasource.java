package org.aibles.framework.repository.datasource;

import org.aibles.framework.BookDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLocalDatasource extends JpaRepository<BookDatabaseEntity, Long> {
}
