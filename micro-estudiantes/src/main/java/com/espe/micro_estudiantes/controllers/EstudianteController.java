package com.espe.micro_estudiantes.controllers;

import com.espe.micro_estudiantes.model.entity.Estudiante;
import com.espe.micro_estudiantes.services.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin
public class EstudianteController {

    @Autowired
    private EstudianteService service;

//    @PostMapping
//    public ResponseEntity<?> crear(@Valid @RequestBody Estudiante estudiante) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(estudiante));
//    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Estudiante estudiante, BindingResult result) {
        // Map para almacenar los errores de validación
        Map<String, String> errores = new HashMap<>();

        // Verificar si hay errores de validación
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), err.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores); // Retornar errores como respuesta
        }

        // Si no hay errores, guardar el estudiante
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(estudiante));
    }


    @GetMapping
    public List<Estudiante> listar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Estudiante> estudianteOptional = service.findById(id);
        if (estudianteOptional.isPresent()) {
            return ResponseEntity.ok().body(estudianteOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Estudiante estudiante) {
//        Optional<Estudiante> estudianteOptional = service.findById(id);
//        if (estudianteOptional.isPresent()) {
//            Estudiante estudianteDB = estudianteOptional.get();
//            estudianteDB.setNombre(estudiante.getNombre());
//            estudianteDB.setApellido(estudiante.getApellido());
//            estudianteDB.setEmail(estudiante.getEmail());
//            estudianteDB.setFechaNacimiento(estudiante.getFechaNacimiento());
//            estudianteDB.setTelefono(estudiante.getTelefono());
//            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(estudianteDB));
//        }
//        return ResponseEntity.notFound().build();
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @Valid @RequestBody Estudiante estudiante, BindingResult result) {
        // Map para almacenar los errores de validación
        Map<String, String> errores = new HashMap<>();

        // Verificar si hay errores de validación
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), err.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores); // Retornar errores como respuesta
        }

        // Buscar si existe el estudiante por ID
        Optional<Estudiante> estudianteOptional = service.findById(id);
        if (estudianteOptional.isPresent()) {
            Estudiante estudianteDB = estudianteOptional.get();
            // Actualizar los campos del estudiante existente
            estudianteDB.setNombre(estudiante.getNombre());
            estudianteDB.setApellido(estudiante.getApellido());
            estudianteDB.setEmail(estudiante.getEmail());
            estudianteDB.setFechaNacimiento(estudiante.getFechaNacimiento());
            estudianteDB.setTelefono(estudiante.getTelefono());
            // Guardar y retornar el estudiante actualizado
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(estudianteDB));
        }

        // Si no se encuentra el estudiante, retornar 404
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Estudiante> estudianteOptional = service.findById(id);
        if (estudianteOptional.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}