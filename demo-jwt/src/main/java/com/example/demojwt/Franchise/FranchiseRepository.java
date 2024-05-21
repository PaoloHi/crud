package com.example.demojwt.Franchise;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise,Long>{

      List<Franchise> findByName(String name); 
 
}
