package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.DTO.TeamDTO;
import com.umesdnd.CampusLeague.model.Team;
import com.umesdnd.CampusLeague.repository.TeamRepository;
import com.umesdnd.CampusLeague.service.interfaces.MyTeamInfoServiceInterface;
import com.umesdnd.CampusLeague.service.interfaces.TeamServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyTeamInfoService implements MyTeamInfoServiceInterface {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamServiceInterface teamService;

    @Override
    @Transactional
    public TeamDTO getMyTeamInfo(String code) {
        if (code.isBlank() || code.isEmpty()) {
            throw new NewExceptionType("El codigo del equipo no puede ser nulo o vacio", HttpStatus.BAD_REQUEST);
        }
        if (code.length() <= 5) {
            throw new NewExceptionType("El codigo del equipo debe tener mas de 5 caracteres", HttpStatus.BAD_REQUEST);
        }
        Team team = teamRepository.findByTeamCode(code).orElseThrow(() -> new NewExceptionType("No existe un equipo registrado con el codigo: "
        + code, HttpStatus.NOT_FOUND));

        TeamDTO teamFull = teamService.getWithPlayers(team.getId());
        return teamFull;
    }
}
