#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${package}.model.Libro;
import ${package}.repository.LibrosRepository;

@Service
public class LibrosServiceLmpl implements LibrosService {
    
    @Autowired
    private LibrosRepository librosRepository;

    @Override
    public List<Libro> getAllLibros(){
        return librosRepository.findAll();
    }

    @Override
    public Optional<Libro> getLibroById(Long id){
        return librosRepository.findById(id);
    }
    
    @Override
    public Libro crearLibro(Libro libro) {
        return librosRepository.save(libro);
    }

    @Override
    public Libro actualizarLibro(Libro libro) {
        return librosRepository.save(libro);
    }

    @Override
    public void eliminarLibro(Long id) {
        librosRepository.deleteById(id);
    }

    @Override
    public List<Libro> getLibrosByGenero(String genero) {
        return librosRepository.findByGenero(genero);
    }
}