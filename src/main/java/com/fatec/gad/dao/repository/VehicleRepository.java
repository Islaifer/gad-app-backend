package com.fatec.gad.dao.repository;

import com.fatec.gad.model.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    Vehicle findByPlate(String plate);
}
