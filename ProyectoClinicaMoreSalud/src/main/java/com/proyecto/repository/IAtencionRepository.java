package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.proyecto.model.Atencion;
@Repository
public interface IAtencionRepository extends JpaRepository<Atencion, Integer> {

}
