package com.example.demojwt.Bank;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping(path="api/v1")
public class BankController {

    @Autowired
    BankRepository bankrepository;

    @PostMapping("/banks")
    @ResponseStatus(HttpStatus.CREATED)
    public Bank saveBank(@RequestBody Bank bank) {
        Bank savedBook = bankrepository.save(bank);
        return savedBook;
    }

    @GetMapping("/banks")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Bank> getAllBanks() {
        Collection<Bank> collection = bankrepository.findAll();
        return collection;
    }

    @GetMapping("/banks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Bank getBankById(@PathVariable Long id) {
        Bank receivedBank = bankrepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bank with id = Not found"));
        return receivedBank;
    }

    @GetMapping(value = "/banks", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    public Collection<Bank> findBankByName(@RequestParam(value = "name") String name) {
        Collection<Bank> collection = bankrepository.findByName(name);
        return collection;
    }

    @PutMapping("/banks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Bank refreshBank(@PathVariable("id") long id, @RequestBody Bank bank) {
        Bank updatedBank = bankrepository.findById(id)
                .map((Bank entity) -> {
                    entity.setName(bank.getName());
                    entity.setTelephon(bank.getTelephon());
                    entity.setEmail(bank.getEmail());
                    return bankrepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Book with id = Not found"));
        return updatedBank;
    }

    @DeleteMapping("/banks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBankById(@PathVariable Long id) {
        bankrepository.deleteById(id);
    }

}
