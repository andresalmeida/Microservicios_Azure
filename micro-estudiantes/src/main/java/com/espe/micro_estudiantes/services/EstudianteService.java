package com.espe.micro_estudiantes.services;

import com.espe.micro_estudiantes.model.entity.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {
    List<Estudiante> findAll();
    Optional<Estudiante> findById(Long id);
    Estudiante save(Estudiante estudiante);
    void deleteById(Long id);
}