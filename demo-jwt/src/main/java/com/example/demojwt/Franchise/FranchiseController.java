package com.example.demojwt.Franchise;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path="api/v1")
public class FranchiseController {

    @Autowired
    FranchiseRepository franrepository ;
    
    @PostMapping("/franchises")
    @ResponseStatus(HttpStatus.CREATED)
    public Franchise saveFranchise(@RequestBody Franchise fran) {
        Franchise savedfFranchise = franrepository.save(fran);
        return savedfFranchise;
    }

    @GetMapping("/franchises")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Franchise> getAllFranchises() {
        Collection<Franchise> collection = franrepository.findAll();
        return collection;
    }

    @GetMapping("/franchises/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Franchise getFrankById(@PathVariable Long id) {
        Franchise receivedFran = franrepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bank with id = Not found"));
        return receivedFran;
    }

    @GetMapping(value = "/franchises", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    public Collection<Franchise> findFranByName(@RequestParam(value = "name") String name) {
        Collection<Franchise> collection = franrepository.findByName(name);
        return collection;
    }

    @PutMapping("/franchises/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Franchise updatFranchise(@PathVariable("id") long id, @RequestBody Franchise fran) {
        Franchise updatedBank = franrepository.findById(id)
                .map((Franchise entity) -> {
                    entity.setName(fran.getName());
                    entity.setTelephon(fran.getTelephon());
                    entity.setEmail(fran.getEmail());
                    entity.setLocation(fran.getLocation());
                    entity.setDescription(fran.getDescription());

                    return franrepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException(" Not found franchise with that id" ));
        return updatedBank;
    }

    @DeleteMapping("/franchises/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFranchiseById(@PathVariable Long id) {
        franrepository.deleteById(id);
    }
}
