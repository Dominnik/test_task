package com.haulmont.testtask.rest;

import com.haulmont.testtask.model.Patient;
import com.haulmont.testtask.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping(value = "/rest/patient")
public class PatientController {

    private PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public List<Patient> getAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(@RequestBody Patient item) {
        service.save(item);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @GetMapping(value = "/can-delete/{id}")
    public ResponseEntity<Boolean> canDelete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.canDelete(id));
    }
}
