package com.example.servicio_libros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.servicio_libros.model.Libro;

public interface LibrosRepository extends JpaRepository<Libro, Long> {
    
}
