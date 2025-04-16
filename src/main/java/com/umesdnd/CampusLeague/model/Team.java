package com.umesdnd.CampusLeague.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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

    @CreationTimestamp
    @Column(name = "approved_date", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)", nullable = true, updatable = false)
    private LocalDateTime approved_date;

    @ManyToOne
    @JoinColumn(name = "approved_by", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

}
