package com.espe.micro_cursos.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

@Entity
@Table(name="cursos_estudiantes")
public class CursoEstudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estudiante_id", unique = true)
    private Long estudiante_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstudiante_id() {
        return estudiante_id;
    }

    public void setEstudiante_id(Long estudiante_id) {
        this.estudiante_id = estudiante_id;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof CursoEstudiante)){
            return false;
        }

        CursoEstudiante cursoEstudiante = (CursoEstudiante) obj;
        return this.estudiante_id != null && this.estudiante_id.equals(cursoEstudiante.estudiante_id);
    }
}
