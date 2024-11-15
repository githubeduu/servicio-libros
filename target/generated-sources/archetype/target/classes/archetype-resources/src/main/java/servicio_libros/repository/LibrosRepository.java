#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.servicio_libros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ${package}.servicio_libros.model.Libro;

public interface LibrosRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE l.genero = :genero")
    List<Libro> findByGenero(@Param("genero") String genero);
}

