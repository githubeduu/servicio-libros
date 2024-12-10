package com.example.servicio_libros.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibroTest {

    @Test
    void testGettersAndSetters() {
        // Crear instancia de Libro
        Libro libro = new Libro();

        // Definir valores de prueba
        Long id = 1L;
        String titulo = "El Principito";
        String autor = "Antoine de Saint-Exupéry";
        int anioPublicacion = 1943;
        String genero = "Ficción";
        String imagenurl = "http://example.com/imagen.jpg";
        int precio = 500;

        // Establecer valores usando setters
        libro.setId(id);
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setAnioPublicacion(anioPublicacion);
        libro.setGenero(genero);
        libro.setImagenurl(imagenurl);
        libro.setPrecio(precio);

        // Verificar valores usando getters
        assertEquals(id, libro.getId(), "El ID debe coincidir");
        assertEquals(titulo, libro.getTitulo(), "El título debe coincidir");
        assertEquals(autor, libro.getAutor(), "El autor debe coincidir");
        assertEquals(anioPublicacion, libro.getAnioPublicacion(), "El año de publicación debe coincidir");
        assertEquals(genero, libro.getGenero(), "El género debe coincidir");
        assertEquals(imagenurl, libro.getImagenurl(), "La URL de la imagen debe coincidir");
        assertEquals(precio, libro.getPrecio(), "El precio debe coincidir");
    }

    @Test
    void testDefaultValues() {
        // Crear instancia de Libro sin valores
        Libro libro = new Libro();

        // Verificar valores predeterminados
        assertNull(libro.getId(), "El ID predeterminado debe ser null");
        assertNull(libro.getTitulo(), "El título predeterminado debe ser null");
        assertNull(libro.getAutor(), "El autor predeterminado debe ser null");
        assertEquals(0, libro.getAnioPublicacion(), "El año de publicación predeterminado debe ser 0");
        assertNull(libro.getGenero(), "El género predeterminado debe ser null");
        assertNull(libro.getImagenurl(), "La URL de la imagen predeterminada debe ser null");
        assertEquals(0, libro.getPrecio(), "El precio predeterminado debe ser 0");
    }
}
