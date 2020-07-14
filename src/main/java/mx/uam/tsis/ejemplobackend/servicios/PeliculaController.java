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
import org.springframework.web.bind.annotation.RequestParam;
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
	@PostMapping(path = "/listapeliculas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Pelicula nuevaPelicula) {
				
		log.info("Recibí llamada con una pelicula "+nuevaPelicula);
		
		Pelicula pelicula = peliculaService.create(nuevaPelicula);
		
		if(pelicula != null) {
			log.info("entre el metodo post");
			return ResponseEntity.status(HttpStatus.CREATED).body(pelicula);
			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede crear alumno");
		}
	

	}
	
	@ApiOperation(
			value="Recupera todas la peliculas",
			notes="Permite recuperar todas las peliculas existentes")
	@GetMapping(path = "/listapeliculas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		
		Iterable <Pelicula> result = peliculaService.retrieveAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(result); 
		
	}

	
	@ApiOperation(
			value="Recupera una pelicula",
			notes="Permite recuperar una pelicula, necesitas su id para recupera todos sus datos")
	@GetMapping(path = "/listapeliculas/{Id_Pelicula}", produces = MediaType.APPLICATION_JSON_VALUE)
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
	@PutMapping(path = "/listapeliculas/{Id_Pelicula}", produces = MediaType.APPLICATION_JSON_VALUE)
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
	@DeleteMapping(path="/listapeliculas/{Id_Pelicula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?>delete(@Valid @PathVariable("Id_Pelicula") Integer Id_Pelicula) {
		Boolean pelicula = peliculaService.delete(Id_Pelicula);
		if(pelicula==true) {
			return ResponseEntity.status(HttpStatus.OK).body("ha sido eliminado: "+pelicula);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
	}
	
	
	@PostMapping(path = "/peliculas/{id_pelicula}/boleto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> addStudentToGroup(
			@PathVariable("id_pelicula") Integer id_pelicula,
			@RequestParam("id_boleto") Integer id_boleto) {
		
		boolean result = peliculaService.addBoletoToPelicula(id_pelicula, id_boleto);
		
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).build(); 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}
		
	
	}
	
	
	
	
}
