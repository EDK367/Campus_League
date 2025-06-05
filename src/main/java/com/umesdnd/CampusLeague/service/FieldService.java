package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.Field;
import com.umesdnd.CampusLeague.repository.FieldRepository;
import com.umesdnd.CampusLeague.repository.StatusRepository;
import com.umesdnd.CampusLeague.service.interfaces.FieldServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService implements FieldServiceInterface {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private StatusService statusService;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Field getById(Long id) {
        return fieldRepository.findById(id).orElseThrow(() -> new NewExceptionType("Campo no encontrado", HttpStatus.NOT_FOUND));
    }

    @Override
    public Field saveOne(Field field) {
        if (field.getName() == null || field.getName().isBlank()) {
            throw new NewExceptionType("El nombre del campo es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (field.getLocation() == null || field.getLocation().isBlank()) {
            throw new NewExceptionType("Se requiere la ubicacion del campo", HttpStatus.BAD_REQUEST);
        }
        if (field.getCapacity() == null || field.getCapacity() <= 0) {
            throw new NewExceptionType("La capacidad del campo debe de ser mayor a 0", HttpStatus.BAD_REQUEST);
        }
        if (fieldRepository.findByName(field.getName()).isPresent()) {
            throw new NewExceptionType("El nombre del campo ya existe", HttpStatus.BAD_REQUEST);
        }

        field.setStatus(statusService.getById(1L));
        return fieldRepository.save(field);
    }

    @Override
    public Field update(Long id, Field field) {
        Field existingField = fieldRepository.findById(id).orElseThrow(() -> new NewExceptionType("Campo no encontrado", HttpStatus.NOT_FOUND));

        if (fieldRepository.existsByNameAndIdNot(field.getName(), id)) {
            throw new NewExceptionType("El nombre del campo ya existe", HttpStatus.BAD_REQUEST);
        }
        if (field.getName() != null && field.getName().isBlank()) {
            throw new NewExceptionType("El nombre del campo no puede estar vacio", HttpStatus.BAD_REQUEST);
        }

        if (field.getLocation() != null && field.getLocation().isBlank()) {
            throw new NewExceptionType("La ubicacion del campo no puede estar vacia", HttpStatus.BAD_REQUEST);
        }

        if (field.getCapacity() != null && field.getCapacity() <= 0) {
            throw new NewExceptionType("La capacidad del campo debe ser mayor que 0", HttpStatus.BAD_REQUEST);
        }

        if (field.getStatus() != null) {
            if (field.getStatus().getId() == null) {
                throw new NewExceptionType("Se requiere el id del estado", HttpStatus.BAD_REQUEST);
            }
            if (!statusRepository.findById(field.getStatus().getId()).isPresent()) {
                throw new NewExceptionType("Estado no encontrado para el campo", HttpStatus.BAD_REQUEST);
            }
            /*
            if (existingField.getStatus().getId() == 2) {
                throw new NewExceptionType("Field cannot be uppdate", HttpStatus.BAD_REQUEST);
            }
             */
            existingField.setStatus(statusService.getById(field.getStatus().getId()));
        }

        if (field.getName() != null) {
            existingField.setName(field.getName());
        }
        if (field.getLocation() != null) {
            existingField.setLocation(field.getLocation());
        }
        if (field.getCapacity() != null) {
            existingField.setCapacity(field.getCapacity());
        }

        return fieldRepository.save(existingField);
    }

    @Override
    public void delete(Long id) {
        Field existingField = fieldRepository.findById(id).orElseThrow(() -> new NewExceptionType("Campo no encontrado", HttpStatus.NOT_FOUND));
        existingField.setStatus(statusService.getById(2L));
        fieldRepository.save(existingField);
    }

    @Override
    public List<Field> getAll() {
        return fieldRepository.findAll();
    }
}
