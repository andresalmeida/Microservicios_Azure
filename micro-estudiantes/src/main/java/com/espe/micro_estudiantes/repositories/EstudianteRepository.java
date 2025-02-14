package com.espe.micro_estudiantes.repositories;

import com.espe.micro_estudiantes.model.entity.Estudiante;
import org.springframework.data.repository.CrudRepository;

public interface EstudianteRepository extends CrudRepository<Estudiante, Long> {
}