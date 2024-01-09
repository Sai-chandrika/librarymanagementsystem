package com.example.librarymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

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
public class BaseDoc {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @CreationTimestamp
    @JsonIgnore
    private Timestamp createdOn;
    private LocalDateTime updatedOn;
    private String createdBy;
    private String updatedBy;
    private boolean isActive = Boolean.TRUE;
}
