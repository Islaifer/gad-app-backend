package com.fatec.gad.dao.repository;

import com.fatec.gad.model.entity.UserPersonalData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPersonalDataRepository extends CrudRepository<UserPersonalData, Long> {
    UserPersonalData findByCpf(String cpf);

    UserPersonalData findByRg(String rg);

    List<UserPersonalData> findByCpfContains(String cpf);

    UserPersonalData findByCrm(String crm);
}
