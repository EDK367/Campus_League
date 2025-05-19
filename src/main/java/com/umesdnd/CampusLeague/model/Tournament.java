package com.umesdnd.CampusLeague.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tournaments")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String tournament_name;

    @ManyToOne
    @JoinColumn(name = "sport_id", nullable = false)
    private Sport sport;

    @CreationTimestamp
    @Column(name = "start_date", nullable = false)
    private LocalDateTime start_date;

    @CreationTimestamp
    @Column(name = "end_date", nullable = false)
    private LocalDateTime end_date;

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
