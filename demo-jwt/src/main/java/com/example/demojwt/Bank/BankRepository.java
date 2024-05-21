package com.example.demojwt.Bank;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface BankRepository extends JpaRepository<Bank,Long> {

   List<Bank> findByName(String name); 
} 