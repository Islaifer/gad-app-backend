package com.fatec.gad.dao.repository;

import com.fatec.gad.model.entity.UserContact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactRepository extends CrudRepository<UserContact, Long> {
}
