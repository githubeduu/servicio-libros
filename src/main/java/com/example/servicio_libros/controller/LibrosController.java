package com.example.servicio_libros.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicio_libros.model.Libro;
import com.example.servicio_libros.service.LibrosService;

@RestController
@RequestMapping("/libros")
public class LibrosController {

     private final LibrosService librosService;

    public LibrosController(LibrosService librosService) {
        this.librosService = librosService;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> getLibros() {
        List<Libro> libros = librosService.getAllLibros();
        return ResponseEntity.ok(libros);
    }    

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = librosService.crearLibro(libro);
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        Optional<Libro> libroExistente = librosService.getLibroById(id);
        
        if (libroExistente.isPresent()) {
            libro.setId(id);
            Libro libroActualizado = librosService.actualizarLibro(libro);
            return new ResponseEntity<>(libroActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        Optional<Libro> libroExistente = librosService.getLibroById(id);
        
        if (libroExistente.isPresent()) {
            librosService.eliminarLibro(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}