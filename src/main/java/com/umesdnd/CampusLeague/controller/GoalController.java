package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.Goal;
import com.umesdnd.CampusLeague.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meta")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @GetMapping("/{id}")
    public ResponseEntity<Goal> getGoal(@PathVariable Long id) {
        Goal goal = goalService.getById(id);
        return new ResponseEntity<>(goal, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Goal> createGoal(@RequestBody Goal goal) {
        Goal saveGoal = goalService.saveOne(goal);
        return new ResponseEntity<>(saveGoal, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Goal> updateGoal(@PathVariable Long id, @RequestBody Goal goal) {
        Goal updateGoal = goalService.update(id, goal);
        return new ResponseEntity<>(updateGoal, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Goal>> getAllGoals() {
        List<Goal> goals = goalService.getAll();
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }
}
