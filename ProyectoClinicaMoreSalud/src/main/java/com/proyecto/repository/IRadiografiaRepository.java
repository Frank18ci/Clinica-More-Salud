package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Radiografia;
@Repository
public interface IRadiografiaRepository extends JpaRepository<Radiografia, Integer> {

}
