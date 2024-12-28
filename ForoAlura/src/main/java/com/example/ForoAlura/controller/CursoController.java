package com.example.ForoAlura.controller;

import com.example.ForoAlura.domain.curso.Curso;
import com.example.ForoAlura.domain.curso.CursoRepository;
import com.example.ForoAlura.domain.curso.DatosRegistroCurso;
import com.example.ForoAlura.domain.curso.DatosRespuestaCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registrarTopico(@RequestBody DatosRegistroCurso datos,
                                                               UriComponentsBuilder uriComponentsBuilder){
        Curso curso = cursoRepository.save(new Curso(datos));
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(curso);
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCurso);
    }

    @GetMapping ResponseEntity<Page<DatosRespuestaCurso>> listarCursos(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(cursoRepository.findAll(pageable)
                .map(DatosRespuestaCurso::new));
    }
}
