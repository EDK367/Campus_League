package com.umesdnd.CampusLeague.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "players")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "names", nullable = false)
    private String names;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "carnet", nullable = false, unique = false)
    private String carnet;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private PlayerPosition position;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    @JsonBackReference
    private Team team;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)", nullable = true, updatable = false)
    private LocalDateTime created_at;

}
