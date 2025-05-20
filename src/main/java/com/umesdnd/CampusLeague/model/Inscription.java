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

    @Column(name = "open_date", nullable = true)
    private LocalDateTime open_date;

    @Column(name = "close_date", nullable = true)
    private LocalDateTime close_date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
