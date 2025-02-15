package com.espe.micro_cursos.services;

import com.espe.micro_cursos.clients.EstudianteClientRest;
import com.espe.micro_cursos.model.Estudiante;
import com.espe.micro_cursos.model.entity.Curso;
import com.espe.micro_cursos.model.entity.CursoEstudiante;
import com.espe.micro_cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private EstudianteClientRest clientRest;

    @Override
    public List<Curso> findAll() {
        return (List<Curso>) repository.findAll();
    }

    @Override
    public Optional<Curso> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Curso save(Curso curso) {
        return repository.save(curso);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }

    @Override
    public Optional<Estudiante> matricularEstudiante(Estudiante estudiante, Long cursoId) {
        Optional<Curso> optional = repository.findById(cursoId);
        if (optional.isPresent()) {
            Estudiante estudianteTemp = clientRest.buscarporId(estudiante.getId());

            Curso curso = optional.get();
            CursoEstudiante cursoEstudiante = new CursoEstudiante();
            cursoEstudiante.setEstudiante_id(estudianteTemp.getId());

            curso.addCursoEstudiante(cursoEstudiante);
            repository.save(curso);

            return Optional.of(estudianteTemp);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Estudiante> desmatricularEstudiante(Long estudianteId, Long cursoId) {
        Optional<Curso> optional = repository.findById(cursoId);
        if (optional.isPresent()) {
            Curso curso = optional.get();
            CursoEstudiante cursoEstudiante = curso.getCursoEstudiantes()
                    .stream()
                    .filter(ce -> ce.getEstudiante_id().equals(estudianteId))
                    .findFirst()
                    .orElse(null);

            if (cursoEstudiante != null) {
                curso.removeCursoEstudiante(cursoEstudiante);
                repository.save(curso);
                return Optional.of(clientRest.buscarporId(estudianteId));
            }
        }
        return Optional.empty();
    }
}