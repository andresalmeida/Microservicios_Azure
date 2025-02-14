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
    public Optional<Estudiante> asignarEstudiante(Estudiante estudiante, Long id) {
        Optional<Curso> optional = repository.findById(id);
        if (optional.isPresent()) {
            Estudiante estudianteTemp = clientRest.buscarporId(estudiante.getId());

            Curso curso = optional.get();
            CursoEstudiante cursoEstudiante = new CursoEstudiante();

            cursoEstudiante.setEstudiante_id(estudianteTemp.getId());

            curso.addCursoEstudiante(cursoEstudiante);
            repository.save(curso);
            //return Optional.of(estudianteTemp);
        }
        return Optional.empty();
    }

//    @Override
//    public Optional<Estudiante> crearEstudiante(Estudiante estudiante, Long id) {
//        Optional<Curso> optional = repository.findById(id);
//        if (optional.isPresent()) {
//            Estudiante estudianteTemp = clientRest.crear(estudiante);
//            Curso curso = optional.get();
//            CursoEstudiante cursoEstudiante = new CursoEstudiante();
//            cursoEstudiante.setEstudiante_id(estudianteTemp.getId());
//            curso.addCursoEstudiante(cursoEstudiante);
//            repository.save(curso);
//        }
//        return Optional.empty();
//    }
}