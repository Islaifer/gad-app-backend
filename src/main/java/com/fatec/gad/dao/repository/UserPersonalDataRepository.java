package com.fatec.gad.dao.repository;

import com.fatec.gad.model.entity.UserPersonalData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPersonalDataRepository extends CrudRepository<UserPersonalData, Long> {
}
