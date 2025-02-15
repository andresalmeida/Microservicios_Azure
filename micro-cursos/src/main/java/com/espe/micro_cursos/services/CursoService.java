package com.espe.micro_cursos.services;

import com.espe.micro_cursos.model.Estudiante;
import com.espe.micro_cursos.model.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> findAll();
    Optional<Curso> findById(Long id);
    Curso save(Curso curso);
    void deleteById(Long id);

    Optional<Estudiante> matricularEstudiante(Estudiante estudiante, Long cursoId);
    Optional<Estudiante> desmatricularEstudiante(Long estudianteId, Long cursoId);


}
