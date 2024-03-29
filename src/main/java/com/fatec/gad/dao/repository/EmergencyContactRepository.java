package com.fatec.gad.dao.repository;

import com.fatec.gad.model.entity.EmergencyContact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyContactRepository extends CrudRepository<EmergencyContact, Long> {
}
