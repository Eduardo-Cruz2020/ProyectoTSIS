package mx.uam.tsis.ejemplobackend.negocio;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.tsis.ejemplobackend.datos.PeliculaRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Boleto;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Pelicula;


@Service
public class PeliculaService {
	
	@Autowired
	private PeliculaRepository peliculaRepository;
	@Autowired
	private IBoletosService boletoservice;
	
	public Pelicula create(Pelicula nuevaPelicula) 
	{
		
		Optional<Pelicula> peliculaOpt = peliculaRepository.findById(nuevaPelicula.getId());
				
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
	
	public boolean addBoletoToPelicula(Integer peliculaId, Integer idBoleto) {
		
		
		// 1.- Recuperamos primero al boleto
		Boleto boleto = boletoservice.findByIdPelicula(idBoleto);
		
		// 2.- Recuperamos la pelicula
		Optional <Pelicula> peliOpt = peliculaRepository.findById(peliculaId);
		
		// 3.- Verificamos que existan ambos elemetos
		if(!peliOpt.isPresent() || boleto == null) {
			
			
			return false;
		}
		
		// 4.- Agrego el alumno al grupo
		Pelicula peli = peliOpt.get();
		peli.addBoletos(boleto);
		
		// 5.- Persistir el cambio
		peliculaRepository.save(peli);
		
		return true;
	}

	
}
