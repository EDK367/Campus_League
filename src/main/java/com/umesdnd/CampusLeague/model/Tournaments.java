package com.umesdnd.CampusLeague.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tournaments")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Tournaments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "sport_id", nullable = false)
    private Sports sports;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date start_date;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", nullable = false)
    private Date end_date;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "max_team_members", nullable = false)
    private int max_team_members;

    @Column(name = "min_team_members", nullable = false)
    private int min_team_members;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;
}
