package com.example.demojwt.Franchise;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Description;

import com.example.demojwt.MSIAutho.MSIAutho;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Franchise",uniqueConstraints = {@UniqueConstraint(columnNames =  {"name"})})
public class Franchise {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long Id;


    @Column(nullable = false)
    private String name; 

    private String location;
    private Integer telephon;
    private String email;
    private String description; 

}
