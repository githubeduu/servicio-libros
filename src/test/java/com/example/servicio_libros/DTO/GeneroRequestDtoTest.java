package com.example.servicio_libros.DTO;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GeneroRequestDtoTest {

    @Test
    void testGeneroGetterSetter() {
        GeneroRequestDto generoRequestDto = new GeneroRequestDto();

        // Test setter
        String genero = "Ficción";
        generoRequestDto.setGenero(genero);

        // Test getter
        assertEquals(genero, generoRequestDto.getGenero(), "El valor del género debería ser 'Ficción'");
    }

    @Test
    void testGeneroNullValue() {
        GeneroRequestDto generoRequestDto = new GeneroRequestDto();

        // Test default value (null)
        assertNull(generoRequestDto.getGenero(), "El valor inicial del género debería ser null");

        // Set to null explicitly and verify
        generoRequestDto.setGenero(null);
        assertNull(generoRequestDto.getGenero(), "El género debería permanecer null");
    }
}
