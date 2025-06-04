package com.univacinas.appointment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CustomAppointmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Appointment> findByOptionalFilters(Optional<Long> patientId, Optional<AppointmentStatus> status) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Appointment> query = cb.createQuery(Appointment.class);
        Root<Appointment> root = query.from(Appointment.class);

        List<Predicate> predicates = new ArrayList<>();

        patientId.ifPresent(id -> predicates.add(cb.equal(root.get("patient").get("id"), id)));
        status.ifPresent(s -> predicates.add(cb.equal(root.get("status"), s)));

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
