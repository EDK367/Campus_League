package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.SanctionType;
import com.umesdnd.CampusLeague.repository.SanctionTypeRepository;
import com.umesdnd.CampusLeague.service.interfaces.SanctionTypeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanctionTypeService implements SanctionTypeServiceInterface {

    @Autowired
    private SanctionTypeRepository sanctionTypeRepository;

    @Override
    public SanctionType getById(Long id) {
        return sanctionTypeRepository.findById(id).orElseThrow(() -> new NewExceptionType("Tipo de sancion no encontrado", HttpStatus.NOT_FOUND));
    }

    @Override
    public SanctionType saveOne(SanctionType sanctionType) {

        if (sanctionTypeRepository.existsByTypeName(sanctionType.getType_name())) {
            throw new NewExceptionType("El nombre del tipo ya existe", HttpStatus.BAD_REQUEST);
        }

        if (sanctionType.getType_name() == null || sanctionType.getType_name().isBlank()) {
            throw new NewExceptionType("El nombre del tipo no puede estar vacio", HttpStatus.BAD_REQUEST);
        }

        return sanctionTypeRepository.save(sanctionType);
    }

    @Override
    public SanctionType update(Long id, SanctionType sanctionType) {
        SanctionType existingSanctionType = sanctionTypeRepository.findById(id).orElseThrow(() -> new NewExceptionType("Tipo de sancion no encontrado", HttpStatus.NOT_FOUND));

        if (sanctionTypeRepository.existsByTypeName(sanctionType.getType_name())) {
            throw new NewExceptionType("El nombre del tipo ya existe", HttpStatus.BAD_REQUEST);
        }
        if (sanctionType.getType_name() == null || sanctionType.getType_name().isBlank()) {
            throw new NewExceptionType("El nombre del tipo no puede estar vacio", HttpStatus.BAD_REQUEST);
        }

        existingSanctionType.setType_name(sanctionType.getType_name());

        return sanctionTypeRepository.save(existingSanctionType);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<SanctionType> getAll() {
        return sanctionTypeRepository.findAll();
    }
}
