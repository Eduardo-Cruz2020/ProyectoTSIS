package mx.uam.tsis.ejemplobackend.servicios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.datos.PeliculaRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Boleto;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Pelicula;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PeliculaControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PeliculaRepository peliculaRepository;
	
	@BeforeEach
	public void prepare() {
		
		// Aqui se puede hacer cosas para preparar sus casos de prueba, incluyendo
		// agregar datos a la BD
		
		
	}
	@Test
public void testCreate201() {
		
		// Creo el alumno que voy a enviar
	Pelicula peli=new Pelicula();	
	peli.setId(1);
	peli.setClasificacion("Familira");
	peli.setDescripcion("nemo");
	peli.setFecha("12-06-20");
	peli.setGenero("Animado");
	peli.setHorarios("8:00");
	peli.setImagenURL("URL");
	peli.setTitulo("Buscando a nemo");
	
	Boleto boleto = Boleto.builder().Id(1).cliente("Gabriel").pelicula("Shrek").cantidad(1).horario("9:00am")
			.fecha("22-07-2020").build();
		
		// Creo el encabezado
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type",MediaType.APPLICATION_JSON.toString());
		
		// Creo la peticiÃ³n con el alumno como body y el encabezado
		HttpEntity <Boleto> request = new HttpEntity <> (boleto, headers);
		Object pelicula;
		HttpEntity <Pelicula> request1 = new HttpEntity <> (peli, headers);
		
		ResponseEntity<Pelicula> responseEntity = restTemplate.exchange("/peliculas", HttpMethod.POST, request1, Pelicula.class);
		log.info("Me regresÃ³:"+responseEntity.getBody());

		// Corroboro que el endpoint me regresa el estatus esperado
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		
		
	}

	@Test
	public void testFindAllSuccesfull() {
	// Creo el encabezado
	HttpHeaders headers = new HttpHeaders();
	headers.set("content-type", MediaType.APPLICATION_JSON.toString());
	
	// Creo la petición con el alumno como body y el encabezado
	HttpEntity<Pelicula> request = new HttpEntity<>(headers);
	
	ResponseEntity<String> response = restTemplate.exchange("/peliculas", HttpMethod.GET, request,
		String.class);
	
	log.info(""+response.getStatusCode());
	assertEquals(HttpStatus.OK, response.getStatusCode());
	
	
	}

}
