package com.espe.micro_cursos.model.entity;

import com.espe.micro_cursos.model.Estudiante;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //@Column(name="name")
    @Column(nullable = true)
    private String nombre;

    @Column(nullable = true)
    private String descripcion;

    @Column(nullable = true)
    private int creditos;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private List<CursoEstudiante> cursoEstudiantes;

    @Transient
    private List<Estudiante> estudiantes;

    public Curso() {
        cursoEstudiantes = new ArrayList<>();
        estudiantes = new ArrayList<>();
    }

    public void addCursoEstudiante(CursoEstudiante cursoEstudiante) {
        cursoEstudiantes.add(cursoEstudiante);
    }

    public void removeCursoEstudiante(CursoEstudiante cursoEstudiante) {
        cursoEstudiantes.remove(cursoEstudiante);
    }

    //GETTERS & SETTERS

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public List<CursoEstudiante> getCursoEstudiantes() {
        return cursoEstudiantes;
    }

    public void setCursoEstudiantes(List<CursoEstudiante> cursoEstudiantes) {
        this.cursoEstudiantes = cursoEstudiantes;
    }

    //METODO QUE INICIALIZA creadoEn AUTOMATICAMENTE
    @PrePersist
    public void prePersist() {
        this.creadoEn = LocalDateTime.now();
    }
}
