package com.example.librarymanagementsystem.entity;

import jakarta.persistence.Column;
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
//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Library extends BaseDoc {

    private String name;
    private String location;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String adminEmail;

    public Library(String name, String location, String email, String adminEmail) {
        this.name = name;
        this.location = location;
        this.email = email;
        this.adminEmail = adminEmail;
    }

    @Override
    public String toString() {
        return "Library{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                '}';
    }
}
