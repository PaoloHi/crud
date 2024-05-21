package com.example.demojwt.MSIAutho;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.example.demojwt.Bank.Bank;
import com.example.demojwt.Bank.BankRepository;
import com.example.demojwt.Franchise.Franchise;
import com.example.demojwt.Franchise.FranchiseRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path="api/v1/")
public class MSIAuthoController {

    @Autowired
    FranchiseRepository franchiserepository;

    @Autowired
    BankRepository bankrepository;

    @Autowired
    MSIAuthoRepository contractrepository;


    @PostMapping("/MSIAutho/{bank_Id}/{franchise_Id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MSIAutho> createMSIAutho (@PathVariable(value="bank_Id") Long bank_Id,   
        @PathVariable(value ="franchise_Id") Long franchise_Id,@RequestBody MSIAutho msiAutho){
        
        Bank bankRelated = bankrepository.findById(bank_Id)
                    .orElseThrow(() -> new EntityNotFoundException("Bank with id = Not found"));

        Franchise franchiseRelated = franchiserepository.findById(franchise_Id)
                    .orElseThrow(() -> new EntityNotFoundException("franchise with id = Not found")); 
            
        msiAutho.setBank(bankRelated);
        msiAutho.setFranchise(franchiseRelated);
        msiAutho.setStarDate(LocalDate.now());
        msiAutho.setEndDate(msiAutho.getStarDate().plusMonths(msiAutho.getAuthorizationMonths()));
                    MSIAutho savedMsiAutho = contractrepository.save(msiAutho);

                    return new ResponseEntity<>(savedMsiAutho,HttpStatus.CREATED);   
    }

    @GetMapping("/MSIAutho")
    @ResponseStatus(HttpStatus.OK)
    public Collection<MSIAutho> getAllAuthos() {
        Collection<MSIAutho> collection = contractrepository.findAll();
        return collection;
    }

    @GetMapping("/MSIAutho/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MSIAutho getAuthoById(@PathVariable Long id) {
        MSIAutho receivedContract = contractrepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Authorization with id  Not found"));
        return receivedContract;
    }


    @PutMapping("/MSIAutho/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MSIAutho refreshContract(@PathVariable("id") long id, @RequestBody MSIAutho contract) {
        MSIAutho updatedcontracts = contractrepository.findById(id)
                .map((MSIAutho entity) -> {

                    entity.setMaxAmountPerBuy(contract.getMaxAmountPerBuy());
                    entity.setAuthorizationMonths(contract.getAuthorizationMonths());
                    entity.setMsiMonths(contract.getMsiMonths());
                    entity.setBank(contract.getBank());                    
                    entity.setFranchise(contract.getFranchise());
                    
                    return contractrepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Book with id = Not found"));
        return updatedcontracts;
    }

    @DeleteMapping("/MSIAutho/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeContractkById(@PathVariable Long id) {
        contractrepository.deleteById(id);
    }




}
