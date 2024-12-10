package com.example.servicio_libros.service;

import com.example.servicio_libros.model.Libro;
import com.example.servicio_libros.repository.LibrosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LibrosServicelpmplTest {

    @Mock
    private LibrosRepository librosRepository;

    @InjectMocks
    private LibrosServiceLmpl librosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllLibros_ShouldReturnAllLibros() {
        List<Libro> libros = Arrays.asList(new Libro(), new Libro());
        when(librosRepository.findAll()).thenReturn(libros);

        List<Libro> resultado = librosService.getAllLibros();

        assertEquals(2, resultado.size());
        verify(librosRepository, times(1)).findAll();
    }

    @Test
    void getLibroById_ShouldReturnLibro_WhenLibroExists() {
        Long id = 1L;
        Libro libro = new Libro();
        libro.setId(id);
        when(librosRepository.findById(id)).thenReturn(Optional.of(libro));

        Optional<Libro> resultado = librosService.getLibroById(id);

        assertTrue(resultado.isPresent());
        assertEquals(id, resultado.get().getId());
        verify(librosRepository, times(1)).findById(id);
    }

    @Test
    void getLibroById_ShouldReturnEmpty_WhenLibroDoesNotExist() {
        Long id = 1L;
        when(librosRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Libro> resultado = librosService.getLibroById(id);

        assertFalse(resultado.isPresent());
        verify(librosRepository, times(1)).findById(id);
    }

    @Test
    void crearLibro_ShouldSaveAndReturnLibro() {
        Libro libro = new Libro();
        when(librosRepository.save(libro)).thenReturn(libro);

        Libro resultado = librosService.crearLibro(libro);

        assertNotNull(resultado);
        verify(librosRepository, times(1)).save(libro);
    }

    @Test
    void actualizarLibro_ShouldUpdateAndReturnLibro() {
        Libro libro = new Libro();
        libro.setId(1L);
        when(librosRepository.save(libro)).thenReturn(libro);

        Libro resultado = librosService.actualizarLibro(libro);

        assertNotNull(resultado);
        verify(librosRepository, times(1)).save(libro);
    }

    @Test
    void eliminarLibro_ShouldDeleteLibro_WhenLibroExists() {
        Long id = 1L;
        doNothing().when(librosRepository).deleteById(id);

        librosService.eliminarLibro(id);

        verify(librosRepository, times(1)).deleteById(id);
    }

    @Test
    void getLibrosByGenero_ShouldReturnLibrosByGenero() {
        String genero = "Ficción";
        List<Libro> libros = Arrays.asList(new Libro(), new Libro());
        when(librosRepository.findByGenero(genero)).thenReturn(libros);

        List<Libro> resultado = librosService.getLibrosByGenero(genero);

        assertEquals(2, resultado.size());
        verify(librosRepository, times(1)).findByGenero(genero);
    }

    @Test
    void searchLibros_ShouldReturnLibrosByKeyword() {
        String keyword = "aventura";
        List<Libro> libros = Arrays.asList(new Libro(), new Libro());
        when(librosRepository.searchByKeyword(keyword)).thenReturn(libros);

        List<Libro> resultado = librosService.searchLibros(keyword);

        assertEquals(2, resultado.size());
        verify(librosRepository, times(1)).searchByKeyword(keyword);
    }
}