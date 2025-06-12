package com.univacinas.vaccine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    long countByCreationDateBetween(LocalDate from, LocalDate to);

    @Query("""
          SELECT COUNT(DISTINCT v.name)
          FROM Vaccine v
          WHERE v.creationDate BETWEEN :from AND :to
        """)
    long countDistinctNamesByCreationDateBetween(LocalDate from, LocalDate to);

    long countByExpirationDateBeforeAndCreationDateBetween(LocalDate date, LocalDate from, LocalDate to);

    long countByExpirationDateBetweenAndCreationDateBetween(LocalDate startDate, LocalDate endDate, LocalDate from, LocalDate to);

    long countByAmountLessThanEqualAndCreationDateBetween(int amount, LocalDate from, LocalDate to);

    List<Vaccine> findAllByManufacturerAndCreationDateBetween(String manufacturer, LocalDate from, LocalDate to);

    long countByCreationDateBetweenAndManufacturer(LocalDate creationDateAfter, LocalDate creationDateBefore, String manufacturer);

    long countDistinctNamesByCreationDateBetweenAndManufacturer(LocalDate creationDateAfter, LocalDate creationDateBefore, String manufacturer);

    long countByExpirationDateBeforeAndCreationDateBetweenAndManufacturer(LocalDate expirationDateBefore, LocalDate creationDateAfter, LocalDate creationDateBefore, String manufacturer);

    long countByExpirationDateBetweenAndCreationDateBetweenAndManufacturer(LocalDate expirationDateAfter, LocalDate expirationDateBefore, LocalDate creationDateAfter, LocalDate creationDateBefore, String manufacturer);

    long countByAmountLessThanEqualAndCreationDateBetweenAndManufacturer(int amountIsLessThan, LocalDate creationDateAfter, LocalDate creationDateBefore, String manufacturer);

    List<Vaccine> findAllByCreationDateBetween(LocalDate creationDateAfter, LocalDate creationDateBefore);
}
