package com.fatec.gad.dao.repository;

import com.fatec.gad.model.entity.User;
import com.fatec.gad.model.entity.UserIdentifierKeys;
import com.fatec.gad.model.entity.UserPersonalData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserIdentifierKeysRepository extends CrudRepository<UserIdentifierKeys, Long> {
    UserIdentifierKeys findByIdentifyKey(String identifyKey);

    List<UserIdentifierKeys> findByIdentifyKeyContains(String identifyKey);

    UserIdentifierKeys findByUser(User user);
}
