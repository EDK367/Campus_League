package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.SanctionType;
import com.umesdnd.CampusLeague.service.SanctionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-sancion")
public class SanctionTypeController {

    @Autowired
    private SanctionTypeService sanctionTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<SanctionType> getSanctionTypeById(@PathVariable Long id) {
        SanctionType sanctionType = sanctionTypeService.getById(id);
        return new ResponseEntity<>(sanctionType, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<SanctionType> saveSanctionType(@RequestBody SanctionType sanctionType) {
        SanctionType saveSanctionType = sanctionTypeService.saveOne(sanctionType);
        return new ResponseEntity<>(saveSanctionType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SanctionType> updateSanctionType(@PathVariable Long id, @RequestBody SanctionType sanctionType) {
        SanctionType updatedSanctionType = sanctionTypeService.update(id, sanctionType);
        return new ResponseEntity<>(updatedSanctionType, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<SanctionType>> getAllSanctionTypes() {
        List<SanctionType> sanctionTypes = sanctionTypeService.getAll();
        return new ResponseEntity<>(sanctionTypes, HttpStatus.OK);
    }
}
