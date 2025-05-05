package com.umesdnd.CampusLeague.model.DTO;

import com.umesdnd.CampusLeague.model.Coach;
import com.umesdnd.CampusLeague.model.Player;
import com.umesdnd.CampusLeague.model.Status;
import com.umesdnd.CampusLeague.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeamDTO {

    private Long id;
    private String name;
    private LocalDateTime inscription_date;
    private LocalDateTime approved_date;
    private User user;
    private Status status;
    private Coach coach;
    private List<Player> players;
}