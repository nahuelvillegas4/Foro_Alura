package com.example.ForoAlura.controller;


import com.example.ForoAlura.domain.ValidacionException;
import com.example.ForoAlura.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    public TopicoRepository topicoRepository;

    @Autowired
    private CreacionDeTopicos creador;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos,
                                                                UriComponentsBuilder uriComponentsBuilder){
        var topico = creador.crear(datos);
        DatosRespuestaTopico respuesta = new DatosRespuestaTopico(topico);

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> listarTopicos(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(topicoRepository.findAll(pageable).map(DatosRespuestaTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> obtenerTopico(@PathVariable Long id){

        if(!topicoRepository.existsById(id)){
            throw new ValidacionException("El topico no existe");
        }

        Topico topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){

        if(!topicoRepository.existsById(id)){
            throw new ValidacionException("El topico no existe");
        }

        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody DatosActualizacionTopico datos){
        if(!topicoRepository.existsById(id)){
            throw new ValidacionException("El topico no existe");
        }

        if(topicoRepository.existsByTitulo(datos.titulo()) && topicoRepository.existsByMensaje(datos.mensaje())){
            throw new ValidacionException("Topico duplicado");
        }

        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizar(datos);

        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }
}
