package com.espe.micro_cursos.clients;

import com.espe.micro_cursos.model.Estudiante;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@FeignClient(name = "micro-estudiantes", url = "${ESTUDIANTES_URL}")
public interface EstudianteClientRest {
    @GetMapping("/{id}")
    Estudiante buscarporId(@PathVariable Long id);
}

//    @PostMapping
//    Estudiante crear(@RequestBody Estudiante estudiante);

