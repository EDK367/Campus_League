package com.umesdnd.CampusLeague.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "inscriptions")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @CreationTimestamp
    @Column(name = "open_date", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)", nullable = false)
    private LocalDateTime open_date;

    @CreationTimestamp
    @Column(name = "close_date", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)", nullable = false)
    private LocalDateTime close_date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
