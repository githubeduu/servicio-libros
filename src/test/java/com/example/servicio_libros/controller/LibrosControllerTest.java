package com.example.servicio_libros.controller;

import com.example.servicio_libros.DTO.GeneroRequestDto;
import com.example.servicio_libros.model.Libro;
import com.example.servicio_libros.service.LibrosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LibrosControllerTest {

    @Mock
    private LibrosService librosService;

    @InjectMocks
    private LibrosController librosController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getLibro_ShouldReturnLibro_WhenLibroExists() {
        Long id = 1L;
        Libro libro = new Libro();
        libro.setId(id);
        when(librosService.getLibroById(id)).thenReturn(Optional.of(libro));

        ResponseEntity<Libro> response = librosController.getLibro(id);

        assertEquals(ResponseEntity.ok(libro), response);
        verify(librosService, times(1)).getLibroById(id);
    }

    @Test
    void getLibro_ShouldReturnNotFound_WhenLibroDoesNotExist() {
        Long id = 1L;
        when(librosService.getLibroById(id)).thenReturn(Optional.empty());

        ResponseEntity<Libro> response = librosController.getLibro(id);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(librosService, times(1)).getLibroById(id);
    }

    @Test
    void getLibros_ShouldReturnListOfLibros() {
        List<Libro> libros = Arrays.asList(new Libro(), new Libro());
        when(librosService.getAllLibros()).thenReturn(libros);

        ResponseEntity<List<Libro>> response = librosController.getLibros();

        assertEquals(ResponseEntity.ok(libros), response);
        verify(librosService, times(1)).getAllLibros();
    }

    @Test
    void crearLibro_ShouldReturnCreatedLibro() {
        Libro libro = new Libro();
        Libro nuevoLibro = new Libro();
        when(librosService.crearLibro(libro)).thenReturn(nuevoLibro);

        ResponseEntity<Libro> response = librosController.crearLibro(libro);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(nuevoLibro, response.getBody());
        verify(librosService, times(1)).crearLibro(libro);
    }

    @Test
    void actualizarLibro_ShouldReturnUpdatedLibro_WhenLibroExists() {
        Long id = 1L;
        Libro libro = new Libro();
        libro.setId(id);
        Libro libroActualizado = new Libro();
        when(librosService.getLibroById(id)).thenReturn(Optional.of(new Libro()));
        when(librosService.actualizarLibro(libro)).thenReturn(libroActualizado);

        ResponseEntity<Libro> response = librosController.actualizarLibro(id, libro);

        assertEquals(ResponseEntity.ok(libroActualizado), response);
        verify(librosService, times(1)).getLibroById(id);
        verify(librosService, times(1)).actualizarLibro(libro);
    }

    @Test
    void actualizarLibro_ShouldReturnNotFound_WhenLibroDoesNotExist() {
        Long id = 1L;
        Libro libro = new Libro();
        when(librosService.getLibroById(id)).thenReturn(Optional.empty());

        ResponseEntity<Libro> response = librosController.actualizarLibro(id, libro);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(librosService, times(1)).getLibroById(id);
    }

    @Test
    void eliminarLibro_ShouldReturnOk_WhenLibroExists() {
        Long id = 1L;
        when(librosService.getLibroById(id)).thenReturn(Optional.of(new Libro()));

        ResponseEntity<Void> response = librosController.eliminarLibro(id);

        assertEquals(200, response.getStatusCodeValue());
        verify(librosService, times(1)).getLibroById(id);
        verify(librosService, times(1)).eliminarLibro(id);
    }

    @Test
    void eliminarLibro_ShouldReturnNotFound_WhenLibroDoesNotExist() {
        Long id = 1L;
        when(librosService.getLibroById(id)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = librosController.eliminarLibro(id);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(librosService, times(1)).getLibroById(id);
    }

    @Test
    void getLibrosByGenero_ShouldReturnListOfLibros() {
        GeneroRequestDto generoRequest = new GeneroRequestDto();
        generoRequest.setGenero("Ficción");
        List<Libro> libros = Arrays.asList(new Libro(), new Libro());
        when(librosService.getLibrosByGenero("Ficción")).thenReturn(libros);

        ResponseEntity<List<Libro>> response = librosController.getLibrosByGenero(generoRequest);

        assertEquals(ResponseEntity.ok(libros), response);
        verify(librosService, times(1)).getLibrosByGenero("Ficción");
    }

    @Test
    void buscarLibros_ShouldReturnListOfLibros() {
        String keyword = "aventura";
        List<Libro> libros = Arrays.asList(new Libro(), new Libro());
        when(librosService.searchLibros(keyword)).thenReturn(libros);

        List<Libro> result = librosController.buscarLibros(keyword);

        assertEquals(libros, result);
        verify(librosService, times(1)).searchLibros(keyword);
    }
}
