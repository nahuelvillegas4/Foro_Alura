package com.example.ForoAlura.domain.topico;

import com.example.ForoAlura.domain.ValidacionException;
import com.example.ForoAlura.domain.curso.CursoRepository;
import com.example.ForoAlura.domain.curso.DatosRespuestaCurso;
import com.example.ForoAlura.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreacionDeTopicos {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public Topico crear(DatosRegistroTopico datos) {

        if(!usuarioRepository.existsById(datos.idUsuario())){
            throw new ValidacionException("Usuario no existe");
        }

        if(!cursoRepository.existsByNombre(datos.nombreCurso())){
            throw new ValidacionException("Curso no existe");
        }

        if(topicoRepository.existsByTitulo(datos.titulo()) && topicoRepository.existsByMensaje(datos.mensaje())){
            throw new ValidacionException("Topico duplicado");
        }

        var curso = cursoRepository.findByNombre(datos.nombreCurso()).get();
        var usuario = usuarioRepository.findById(datos.idUsuario()).get();

        var topico = new Topico(datos, curso, usuario);

        topicoRepository.save(topico);
        return topico;
    }
}
