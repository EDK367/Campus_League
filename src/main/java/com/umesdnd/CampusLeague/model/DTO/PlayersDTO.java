package com.umesdnd.CampusLeague.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umesdnd.CampusLeague.model.PlayerPosition;
import com.umesdnd.CampusLeague.model.Status;
import com.umesdnd.CampusLeague.model.TeamPlayer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlayersDTO {

    private Long id;
    private String names;
    private int age;
    private String carnet;
    private PlayerPosition position;
    private Status status;
    private LocalDateTime created_at;

}
