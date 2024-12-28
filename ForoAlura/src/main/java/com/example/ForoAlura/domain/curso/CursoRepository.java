package com.example.ForoAlura.domain.curso;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Page<Curso> findAll(Pageable pageable);

    Optional<Curso> findByNombre(String nombre);

    boolean existsByNombre(@NotBlank String nombre);
}
