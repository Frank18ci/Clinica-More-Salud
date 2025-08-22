package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.TratamientoMedico;
@Repository
public interface ITratamientoMedicoRepository extends JpaRepository<TratamientoMedico, Integer> {

}
