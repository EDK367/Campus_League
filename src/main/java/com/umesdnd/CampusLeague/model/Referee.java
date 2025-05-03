package com.umesdnd.CampusLeague.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "referees")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Referee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "experience_years", nullable = false)
    private Long experience_years;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = true)
    private Status status;
}
