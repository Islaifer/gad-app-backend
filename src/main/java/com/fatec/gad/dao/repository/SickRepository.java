package com.fatec.gad.dao.repository;

import com.fatec.gad.model.entity.Sick;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SickRepository extends CrudRepository<Sick, Long> {
}
