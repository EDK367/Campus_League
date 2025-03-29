package com.umesdnd.CampusLeague.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private String email;
    private String createdAt;
    // para tener null
    private Long createdBy;

}
