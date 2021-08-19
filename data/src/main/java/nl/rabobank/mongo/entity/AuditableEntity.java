package nl.rabobank.mongo.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class AuditableEntity {

    @Id
    String id = UUID.randomUUID().toString();

    @Version
    int version;

    @CreatedDate
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime lastUpdatedAt;
}
