package com.fatec.gad.dao.repository;

import com.fatec.gad.model.entity.User;
import com.fatec.gad.model.entity.UserIdentifierKeys;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIdentifierKeysRepository extends CrudRepository<UserIdentifierKeys, Long> {
    UserIdentifierKeys findByIdentifyKey(String identifyKey);

    UserIdentifierKeys findByUser(User user);
}
