package com.example.librarymanagementsystem.entity;

import com.example.librarymanagementsystem.dto.BaseDoc;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author chandrika.g
 * user
 * @ProjectName library-management-system
 * @since 09-01-2024
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Library extends BaseDoc {
    private String name;
    private String location;
    private String adminEmail;

}
