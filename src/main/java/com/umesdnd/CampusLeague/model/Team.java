package com.umesdnd.CampusLeague.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "teams")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @CreationTimestamp
    @Column(name = "inscription_date", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)", nullable = true, updatable = false)
    private LocalDateTime inscription_date;

    @Column(name = "approved_date", nullable = true)
    private LocalDateTime approved_date;

    @Column(name = "contact_email", nullable = false)
    private String contact_email;

    @Column(name = "contact_phone", nullable = false)
    private String contact_phone;

    //@Column(name = "logo", nullable = true, updatable = false)
    //private String logo;

    @ManyToOne
    @JoinColumn(name = "approved_by", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = true)
    private Coach coach;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference(value = "team-teamPlayers")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<TeamPlayer> teamPlayers;

    // solo para recibir
    @Transient
    private List<Player> players;

    @Column(name = "captain", nullable = false)
    private String captain;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @Column(name = "team_code", nullable = true, unique = true)
    private String teamCode;

}
