package com.example.demojwt.MSIAutho;




import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.cglib.core.Local;

import com.example.demojwt.Bank.Bank;
import com.example.demojwt.Franchise.Franchise;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="MSIAuthorization")
public class MSIAutho {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(nullable = false)
    private Integer maxAmountPerBuy;  
    private Integer msiMonths;
    private Integer authorizationMonths;    

    private LocalDate starDate; 
    private LocalDate endDate;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "bank_Id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Bank bank;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "franchise_Id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Franchise franchise;



}
