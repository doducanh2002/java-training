package org.aibles.framework.datasource;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account_role")
@Data
public class AccountRoleDatabaseEntity {

 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
 private String id;

 private String roleId;
 private String accountId;

 public AccountRoleDatabaseEntity() {

 }

 public AccountRoleDatabaseEntity(String roleId, String accountId) {
  this.roleId = roleId;
  this.accountId = accountId;
 }
}
