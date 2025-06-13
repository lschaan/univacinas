package com.univacinas.vaccine;

import com.univacinas.report.ManufacturerBreakdown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    @Query("""
          SELECT new com.univacinas.vaccine.VaccineStats(
            COUNT(v),
            COUNT(DISTINCT v.name),
            COALESCE(SUM(CASE WHEN v.expirationDate < :today THEN 1 ELSE 0 END), 0),
            COALESCE(SUM(CASE WHEN v.expirationDate BETWEEN :today AND :todayPlus7 THEN 1 ELSE 0 END), 0),
            COALESCE(SUM(CASE WHEN v.amount <= :lowThreshold THEN 1 ELSE 0 END), 0)
          )
          FROM Vaccine v
          WHERE v.creationDate BETWEEN :from AND :to
            AND (:manufacturer IS NULL OR v.manufacturer = :manufacturer)
        """)
    VaccineStats fetchStats(LocalDate from, LocalDate to, String manufacturer, LocalDate today, LocalDate todayPlus7, int lowThreshold);

    @Query("""
          SELECT new com.univacinas.report.ManufacturerBreakdown(
            v.manufacturer,
            SUM(v.amount),
            COUNT(v)
          )
          FROM Vaccine v
          WHERE v.creationDate BETWEEN :from AND :to
            AND (:manufacturer IS NULL OR v.manufacturer = :manufacturer)
          GROUP BY v.manufacturer
        """)
    List<ManufacturerBreakdown> fetchBreakdown(LocalDate from, LocalDate to, String manufacturer);
}
