package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.model.Goal;
import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.repository.GoalRepository;
import com.umesdnd.CampusLeague.service.interfaces.GoalServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService implements GoalServiceInterface {

    @Autowired
    private GoalRepository goalRepository;

    @Override
    public Goal getById(Long id){
        return goalRepository.findById(id).orElseThrow(() -> new NewExceptionType("Meta no encontrada", HttpStatus.NOT_FOUND));
    }

    @Override
    public Goal saveOne(Goal goal){return goalRepository.save(goal);}

    @Override
    public Goal update(Long id, Goal goal){
        Goal existingGoal = goalRepository.findById(id).orElseThrow(() -> new RuntimeException("Meta no encontrada"));

        existingGoal.setPlayer(goal.getPlayer());
        existingGoal.setTeam(goal.getTeam());
        existingGoal.setMatch(goal.getMatch());
        existingGoal.setGoal_time(goal.getGoal_time());
        existingGoal.setPoints(goal.getPoints());

        return goalRepository.save(existingGoal);
    }

    @Override
    public void delete(Long id){
        if (!goalRepository.existsById(id)) {
            throw new RuntimeException("Meta con ID " + id + " no  encontrada");
        }
        goalRepository.deleteById(id);
    }

    @Override
    public List<Goal> getAll(){return goalRepository.findAll();}
}
