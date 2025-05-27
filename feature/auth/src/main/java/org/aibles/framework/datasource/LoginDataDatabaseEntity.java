package org.aibles.framework.datasource;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "login_data")
@Data
public class LoginDataDatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String accountId;
    private LocalDateTime loginTime;
    private String ipAddress;
    private String browserId;
    private String deviceId;
    private String platform;
    private String operatingSystem;
    private Boolean success;
    private String createdBy;
    private String createdAt;
    private String lastUpdatedBy;
    private String lastUpdatedAt;
}