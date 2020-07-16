package mx.uam.tsis.ejemplobackend.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.tsis.ejemplobackend.negocio.modelo.Pelicula;

public interface PeliculaRepository  extends CrudRepository<Pelicula,Integer>{
	
	
 
}