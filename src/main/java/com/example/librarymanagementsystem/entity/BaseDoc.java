package com.example.librarymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author chandrika.g
 * user
 * @ProjectName library-management-system
 * @since 09-01-2024
 */
@MappedSuperclass
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseDoc {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @CreationTimestamp
    @JsonIgnore
    private Timestamp createdOn =Timestamp.valueOf(LocalDateTime.now());

    private LocalDateTime updatedOn = LocalDateTime.now();
    private boolean isActive = Boolean.TRUE;
}
