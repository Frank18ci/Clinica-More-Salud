package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Alergia;
@Repository
public interface IAlergiaRepository extends JpaRepository<Alergia, Integer> {

}
