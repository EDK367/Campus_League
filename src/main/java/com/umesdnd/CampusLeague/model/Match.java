package com.umesdnd.CampusLeague.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "team1_id", nullable = false)
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id", nullable = false)
    private Team team2;

    @Column(name = "match_date", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)", nullable = true)
    @JsonProperty("match_date")
    private LocalDateTime matchDate;

    @Column(name = "team1_score", columnDefinition = "INT DEFAULT 0", nullable = true)
    private Long team1_score;

    @Column(name = "team2_score", columnDefinition = "INT DEFAULT 0", nullable = true)
    private Long team2_score;

    @ManyToOne
    @JoinColumn(name = "field_id",  nullable = false)
    private Field field;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = true)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "referee_id", nullable = false)
    private Referee referee;
}
