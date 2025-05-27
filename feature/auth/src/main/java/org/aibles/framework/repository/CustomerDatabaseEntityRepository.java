package org.aibles.framework.repository;

import java.util.List;
import java.util.Optional;
import org.aibles.framework.datasource.CustomerDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDatabaseEntityRepository extends
    JpaRepository<CustomerDatabaseEntity, Long> {

  Optional<CustomerDatabaseEntity> findByEmail(String email);

  boolean existsByEmail(String email);

  @Query("""
        SELECT c from CustomerDatabaseEntity c left join CustomerAccountDatabaseEntity ca
        on c.id = ca.customerId where ca.accountId = :accountId
        """)
  Optional<CustomerDatabaseEntity> findByAccountId(String accountId);

  @Query("""
    SELECT new org.aibles.framework.repository.CustomerMilesAvailableProjection(c.id, c.milesAvailable)
    FROM CustomerDatabaseEntity c
    JOIN CustomerAccountDatabaseEntity ca ON c.id = ca.customerId
    WHERE ca.accountId = :id
  """)
  CustomerMilesAvailableProjection findMilesAvailableByAccountId(String id);

  CustomerDatabaseEntity findById(String accountId);

  @Query(
      """
SELECT c.email FROM CustomerDatabaseEntity c JOIN TicketDatabaseEntity t ON c.id = t.customerId
WHERE t.id = :ticketId
""")
  String getCustomerEmailByTicketId(String ticketId);

  @Query(
      """
SELECT c FROM CustomerDatabaseEntity c where c.id IN :ids
"""
  )
  List<CustomerDatabaseEntity> findAllByListId(List<String> ids);
}
