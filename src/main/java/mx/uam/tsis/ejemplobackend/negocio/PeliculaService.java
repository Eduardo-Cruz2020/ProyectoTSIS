package mx.uam.tsis.ejemplobackend.negocio;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.tsis.ejemplobackend.datos.PeliculaRepository;

import mx.uam.tsis.ejemplobackend.negocio.modelo.Pelicula;


@Service
public class PeliculaService {
	@Autowired
	private PeliculaRepository peliculaRepository;
	
	public Pelicula create(Pelicula nuevaPelicula) 
	{
		
		Optional<Pelicula> peliculaOpt = peliculaRepository.findById(nuevaPelicula.getId_Pelicula());
				
				if(!peliculaOpt.isPresent()) {
					
					return peliculaRepository.save(nuevaPelicula);
					
				} else {
					
					return null;
					
				}
		
	}
	
	public Iterable <Pelicula> retrieveAll () {
		return peliculaRepository.findAll();
	}


	public Pelicula retrieve(Integer Id_Pelicula) {
		
		  Optional<Pelicula> pelicula=peliculaRepository.findById(Id_Pelicula);
		  return pelicula.get();
	}


	public Pelicula update(@Valid Integer Id_Pelicula, @Valid Pelicula nuevaPelicula) {
		if(peliculaRepository.existsById(Id_Pelicula)) {
			return peliculaRepository.save(nuevaPelicula);
		}else
			return null;
	}
	public boolean delete(Integer Id_Pelicula) {
		if (peliculaRepository.existsById(Id_Pelicula)) {
			peliculaRepository.deleteById(Id_Pelicula);
			return true;
		}else 
		{
			return false;
		}
	}
	
	

	
}
