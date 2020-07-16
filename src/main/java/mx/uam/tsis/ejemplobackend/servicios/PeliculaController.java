package mx.uam.tsis.ejemplobackend.servicios;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.PeliculaService;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Pelicula;
 




@RestController
@Slf4j

public class PeliculaController {
	@Autowired
	private PeliculaService peliculaService;
	@ApiOperation(
			value="Crear Pelicula nueva",
			notes="Permite crear una pelicula ")
	@PostMapping(path = "/peliculas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Pelicula nuevaPelicula) {
				
		log.info("Recibí llamada con una pelicula "+nuevaPelicula);
		
		Pelicula pelicula = peliculaService.create(nuevaPelicula);
		
		if(pelicula != null) {
			log.info("Creación de pelicula exitosa");
			return ResponseEntity.status(HttpStatus.CREATED).body(pelicula);
			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede crear la película");
		}
	

	}
	
	@ApiOperation(
			value="Recupera todas la peliculas",
			notes="Permite recuperar todas las peliculas existentes")
	@GetMapping(path = "/peliculas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		log.info("recuperando lista de peliculas");

		Iterable <Pelicula> result = peliculaService.retrieveAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(result); 
		
	}

	
	@ApiOperation(
			value="Recupera una pelicula",
			notes="Permite recuperar una pelicula, necesitas su id para recupera todos sus datos")
	@GetMapping(path = "/peliculas/{Id_Pelicula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieve(@Valid @PathVariable ("Id_Pelicula") Integer Id_Pelicula) {
		log.info("Buscando al alumno con matricula "+Id_Pelicula);
		Pelicula pelicula=peliculaService.retrieve(Id_Pelicula);
		if(pelicula != null) {
			return ResponseEntity.status(HttpStatus.OK).body(pelicula);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El alumno con la matricula"+Id_Pelicula+"no exite");
		}
	
		
	}
	

	@ApiOperation(
			value="Modifica una pelicula",
			notes="Permite modificar los datos de una Pelicula, Se necesita el id de la pelicula y un objeto con todos los datos de la pelicula")
	@PutMapping(path = "/peliculas/{Id_Pelicula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@Valid @PathVariable("Id_Pelicula") Integer matricula,@RequestBody  @Valid Pelicula nuevaPelicula ) {
		Pelicula pelicula = peliculaService.update(matricula, nuevaPelicula);
		System.out.println("PELICULA"+pelicula);
		if(pelicula!= null) {
			return ResponseEntity.status(HttpStatus.OK).body(pelicula);
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	
	@ApiOperation(
			value="Borra pelicula",
			notes="Permite borrar una pelicula de nuestra base de datos. Requiere el id de la pelicula")
	@DeleteMapping(path="/peliculas/{Id_Pelicula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?>delete(@Valid @PathVariable("Id_Pelicula") Integer Id_Pelicula) {
		Boolean pelicula = peliculaService.delete(Id_Pelicula);
		if(pelicula==true) {
			log.info("Eliminando pelicula  con id: "+ Id_Pelicula);

			return ResponseEntity.status(HttpStatus.OK).body("ha sido eliminado: "+pelicula);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
	}
	
	
	
	
}
