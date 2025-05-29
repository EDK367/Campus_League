package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.Referee;
import com.umesdnd.CampusLeague.repository.RefereeRepository;
import com.umesdnd.CampusLeague.service.interfaces.RefereeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefereeService implements RefereeServiceInterface {

    @Autowired
    private RefereeRepository refereeRepository;

    @Autowired
    private StatusService statusService;

    @Override
    public Referee getById(Long id) {
        return refereeRepository.findById(id).orElseThrow(() -> new NewExceptionType("Arbitro no encontrado", HttpStatus.NOT_FOUND));
    }

    @Override
    public Referee saveOne(Referee referee) {
        if (referee.getExperience_years() == null) {
            throw new NewExceptionType("Los años de experiencia no pueden ser nulos", HttpStatus.BAD_REQUEST);
        }
        if (referee.getExperience_years() < 0 || referee.getExperience_years() > 50) {
            String message = (referee.getExperience_years() < 0)
                    ? "Los años de experiencia no pueden ser negativos"
                    : "Los años de experiencia no pueden ser mayores a 50";
            throw new NewExceptionType(message, HttpStatus.BAD_REQUEST);
        }
        if (referee.getName() == null || referee.getName().isBlank()) {
            throw new NewExceptionType("El nombre no puede ser nulo o vacio", HttpStatus.BAD_REQUEST);
        }
        referee.setStatus(statusService.getById(1L));
        return refereeRepository.save(referee);
    }

    @Override
    public Referee update(Long id, Referee referee) {
        Referee existingReferee = refereeRepository.findById(id).orElseThrow(() -> new NewExceptionType("Arbitro no encontrado", HttpStatus.NOT_FOUND));


        if (referee.getStatus() != null) {
            existingReferee.setStatus(referee.getStatus());
        }

        if (referee.getExperience_years() != null) {
            if (referee.getExperience_years() < 0 || referee.getExperience_years() > 50) {
                String message = (referee.getExperience_years() < 0)
                        ? "Los años de experiencia no pueden ser negativos"
                        : "Los años de experiencia no pueden ser mayores a 50";
                throw new NewExceptionType(message, HttpStatus.BAD_REQUEST);
            }
            existingReferee.setExperience_years(referee.getExperience_years());
        }

        if (referee.getName() != null && referee.getName().isBlank()) {
            throw new NewExceptionType("El nombre no puede estar vacio", HttpStatus.BAD_REQUEST);
        }

        if(referee.getName() != null) {
            existingReferee.setName(referee.getName());
        }

        return refereeRepository.save(existingReferee);
    }

    @Override
    public void delete(Long id) {
        Referee existingReferee = refereeRepository.findById(id).orElseThrow(() -> new NewExceptionType("Arbitro no encontrado", HttpStatus.NOT_FOUND));
        existingReferee.setStatus(statusService.getById(2L));
        this.refereeRepository.save(existingReferee);
    }

    @Override
    public List<Referee> getAll() {
        return refereeRepository.findAll();
    }
}
