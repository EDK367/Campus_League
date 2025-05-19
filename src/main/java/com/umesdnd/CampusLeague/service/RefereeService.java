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
        return refereeRepository.findById(id).orElseThrow(() -> new NewExceptionType("Referee not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Referee saveOne(Referee referee) {
        if (referee.getExperience_years() == null) {
            throw new NewExceptionType("Experience years cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (referee.getExperience_years() < 0 || referee.getExperience_years() > 50) {
            String message = (referee.getExperience_years() < 0)
                    ? "Experience years cannot be negative"
                    : "Experience years cannot be greater than 50";
            throw new NewExceptionType(message, HttpStatus.BAD_REQUEST);
        }
        if (referee.getName() == null || referee.getName().isBlank()) {
            throw new NewExceptionType("Name cannot be null or empty", HttpStatus.BAD_REQUEST);
        }
        referee.setStatus(statusService.getById(1L));
        return refereeRepository.save(referee);
    }

    @Override
    public Referee update(Long id, Referee referee) {
        Referee existingReferee = refereeRepository.findById(id).orElseThrow(() -> new NewExceptionType("Referee not found", HttpStatus.NOT_FOUND));

        if (existingReferee.getStatus().getId() != 1) {
            throw new NewExceptionType("Referee is not active", HttpStatus.BAD_REQUEST);
        }

        if (referee.getExperience_years() != null) {
            if (referee.getExperience_years() < 0 || referee.getExperience_years() > 50) {
                String message = (referee.getExperience_years() < 0)
                        ? "Experience years cannot be negative"
                        : "Experience years cannot be greater than 50";
                throw new NewExceptionType(message, HttpStatus.BAD_REQUEST);
            }
            existingReferee.setExperience_years(referee.getExperience_years());
        }

        if (referee.getName() != null && referee.getName().isBlank()) {
            throw new NewExceptionType("Name cannot be empty", HttpStatus.BAD_REQUEST);
        }

        if(referee.getName() != null) {
            existingReferee.setName(referee.getName());
        }

        return refereeRepository.save(existingReferee);
    }

    @Override
    public void delete(Long id) {
        Referee existingReferee = refereeRepository.findById(id).orElseThrow(() -> new NewExceptionType("Referee not found", HttpStatus.NOT_FOUND));
        existingReferee.setStatus(statusService.getById(2L));
        this.refereeRepository.save(existingReferee);
    }

    @Override
    public List<Referee> getAll() {
        return refereeRepository.findAll();
    }
}
