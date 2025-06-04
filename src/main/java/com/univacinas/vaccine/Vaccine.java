package com.univacinas.vaccine;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String batch;
    private String manufacturer;

    private LocalDate expirationDate;

    private int amount;

    public boolean hasStock() {
        return amount > 0;
    }

    public void reduceStock() {
        amount--;
    }
}
