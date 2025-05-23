package com.umesdnd.CampusLeague.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "team_players")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    @JsonBackReference(value = "team-teamPlayers")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    @JsonBackReference(value = "player-teamPlayers")
    private Player player;

}
