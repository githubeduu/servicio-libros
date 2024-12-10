package com.example.servicio_libros.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.servicio_libros.model.Libro;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LibrosRepositoryTest {

    @Autowired
    private LibrosRepository librosRepository;

    @Test
    public void crearLibroTest() {
        Libro libro = new Libro();
        libro.setTitulo("El Principito");
        libro.setAutor("Antoine de Saint-Exupéry");
        libro.setAnioPublicacion(1943);
        libro.setGenero("Ficción");
        libro.setImagenurl("http://example.com/principito.jpg");
        libro.setPrecio(500);

        Libro resultado = librosRepository.save(libro);

        assertNotNull(resultado.getId());
        assertEquals("El Principito", resultado.getTitulo());
        assertEquals("Antoine de Saint-Exupéry", resultado.getAutor());
        assertEquals(1943, resultado.getAnioPublicacion());
        assertEquals("Ficción", resultado.getGenero());
        assertEquals("http://example.com/principito.jpg", resultado.getImagenurl());
        assertEquals(500, resultado.getPrecio());
    }

    @Test
    public void buscarLibroPorIdTest() {
        Libro libro = new Libro();
        libro.setTitulo("El Hobbit");
        libro.setAutor("J.R.R. Tolkien");
        libro.setAnioPublicacion(1937);
        libro.setGenero("Fantasía");
        libro.setImagenurl("http://example.com/hobbit.jpg");
        libro.setPrecio(800);

        libro = librosRepository.save(libro);
        Optional<Libro> resultado = librosRepository.findById(libro.getId());

        assertTrue(resultado.isPresent());
        assertEquals("El Hobbit", resultado.get().getTitulo());
        assertEquals("J.R.R. Tolkien", resultado.get().getAutor());
        assertEquals(1937, resultado.get().getAnioPublicacion());
        assertEquals("Fantasía", resultado.get().getGenero());
        assertEquals("http://example.com/hobbit.jpg", resultado.get().getImagenurl());
        assertEquals(800, resultado.get().getPrecio());
    }

    @Test
    public void buscarLibroPorKeywordTest() {
        Libro libro = new Libro();
        libro.setTitulo("El Hobbit");
        libro.setAutor("J.R.R. Tolkien");
        libro.setAnioPublicacion(1937);
        libro.setGenero("Fantasía");
        libro.setImagenurl("http://example.com/hobbit.jpg");
        libro.setPrecio(800);

        librosRepository.save(libro);
        var resultado = librosRepository.searchByKeyword("Hobbit");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("El Hobbit", resultado.get(0).getTitulo());
    }
}
