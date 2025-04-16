package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.model.Status;
import com.umesdnd.CampusLeague.model.Team;
import com.umesdnd.CampusLeague.repository.StatusRepository;
import com.umesdnd.CampusLeague.service.interfaces.StatusServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService implements StatusServiceInterface {

    @Autowired
    private StatusRepository repository;

    @Override
    public Status getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public Status saveOne(Status status) {
        return repository.save(status);
    }

    @Override
    public Status update(Long id, Status status) {
        Status existingStatus = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existingStatus.setStatus_name(status.getStatus_name());

        return repository.save(existingStatus);
    }

    @Override
    public void delete(Long id) {
        Status status = repository.findById(id).orElseThrow(() -> new RuntimeException("Team not found with id " + id));
        repository.delete(status);
    }

    @Override
    public List<Status> getAll() {
        return repository.findAll();
    }
}
