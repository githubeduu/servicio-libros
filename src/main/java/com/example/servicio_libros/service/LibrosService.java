package com.example.servicio_libros.service;

import java.util.List;
import java.util.Optional;

import com.example.servicio_libros.model.Libro;

public interface LibrosService {
    List<Libro> getAllLibros();
    Optional<Libro> getLibroById(Long id); 
    Libro crearLibro(Libro libro);
    Libro actualizarLibro(Libro libro);
    void eliminarLibro(Long id);
    List<Libro> getLibrosByGenero(String genero);
}
