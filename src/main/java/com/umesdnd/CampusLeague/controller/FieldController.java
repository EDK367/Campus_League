package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.Field;
import com.umesdnd.CampusLeague.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campo")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @GetMapping("/{id}")
    public ResponseEntity<Field> getFieldById(@PathVariable Long id){
        Field field = fieldService.getById(id);
        return new ResponseEntity<>(field, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Field> saveField(@RequestBody Field field){
        Field saveField = fieldService.saveOne(field);
        return new ResponseEntity<>(saveField, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Field> updateField(@PathVariable Long id, @RequestBody Field field){
        Field updatedField = fieldService.update(id, field);
        return new ResponseEntity<>(updatedField, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Long id){
        fieldService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public ResponseEntity<List<Field>> getAllFields(){
        List<Field> fields = fieldService.getAll();
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }
}
