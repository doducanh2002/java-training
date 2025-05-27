package org.aibles.student.framework.repository.datasource;

import org.aibles.student.framework.repository.domain.StudentDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentLocalDatasource extends JpaRepository<StudentDatabaseEntity, Long> {
  @Query("SELECT s FROM StudentDatabaseEntity s WHERE LOWER(s.name) LIKE LOWER(concat('%', ?1, '%'))")
  List<StudentDatabaseEntity> searchForStudents(String name);
}
