package com.umesdnd.CampusLeague.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "statuses")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Status {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "status_name", nullable = false, unique = true)
    private String status_name;
}
