package com.espe.micro_cursos.repositories;

import com.espe.micro_cursos.model.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {
}
