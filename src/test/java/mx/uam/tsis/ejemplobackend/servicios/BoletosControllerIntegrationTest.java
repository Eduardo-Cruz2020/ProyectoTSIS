package mx.uam.tsis.ejemplobackend.servicios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

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
import mx.uam.tsis.ejemplobackend.datos.IBoletosRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Boleto;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoletosControllerIntegrationTest {
    
    @Autowired
    private IBoletosRepository boletosRepo;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @BeforeEach
    public void prepare() {
	Boleto boleto = Boleto.builder()
		.Id(1)
		.cliente("Gabriel")
		.pelicula("Gardfield")
		.cantidad(2)
		.fecha("01-01-2021")
		.horario("8:00")
		.build();
	boletosRepo.save(boleto);
    }
    
    //create
    
    @Test
    public void testCreateSuccesfull() {
	
	Boleto boleto = Boleto.builder()
		.Id(2)
		.cliente("Jesus")
		.pelicula("Coraje")
		.cantidad(3)
		.fecha("01-01-2022")
		.horario("9:00")
		.build();
	

		// Creo el encabezado
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type", MediaType.APPLICATION_JSON.toString());

		// Creo la petición con el alumno como body y el encabezado
		HttpEntity<Boleto> request = new HttpEntity<>(boleto, headers);

		ResponseEntity<Boleto> responseEntity = restTemplate.exchange("/boletos/create", HttpMethod.POST, request,
			Boleto.class);

		// Corroboro que el endpoint me regresa el estatus esperado
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

		Optional<Boleto> grupoBusc = boletosRepo.findById(2);
		// grupo = grupoBusc.get();

		assertEquals(grupoBusc.get().getId(), responseEntity.getBody().getId());
	   
    }

    @Test
    public void testCreateGroupUnSuccesfull() {
	// Enviaremos un grupo nulo
	Boleto grupoNuevo = null;

	// Creo el encabezado
	HttpHeaders headers = new HttpHeaders();
	headers.set("content-type", MediaType.APPLICATION_JSON.toString());

	// Creo la petición con el alumno como body y el encabezado
	HttpEntity<Boleto> request = new HttpEntity<>(grupoNuevo, headers);

	// grupo existente
	ResponseEntity<Boleto> responseEntity = restTemplate.exchange("/boletos/create", HttpMethod.POST, request,
		Boleto.class);

	// Corroboro que el endpoint me regresa el estatus esperado
	assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }
    
    
    //Find

    @Test
    public void testFindAllSuccesfull() {
	// Creo el encabezado
	HttpHeaders headers = new HttpHeaders();
	headers.set("content-type", MediaType.APPLICATION_JSON.toString());

	// Creo la petición con el alumno como body y el encabezado
	HttpEntity<Boleto> request = new HttpEntity<>(headers);
	
	ResponseEntity<String> response = restTemplate.exchange("/boletos/find/1", HttpMethod.GET, request,
		String.class);
	
	log.info(""+response.getStatusCode());
	assertEquals(HttpStatus.OK, response.getStatusCode());
	
	
    }

    @Test
    public void testFindByIdSuccesfull() {
	// Creo el encabezado
	HttpHeaders headers = new HttpHeaders();
	headers.set("content-type", MediaType.APPLICATION_JSON.toString());

	// Creo la petición con el alumno como body y el encabezado
	HttpEntity<Boleto> request = new HttpEntity<>(headers);

	// pasamos un id existente
	ResponseEntity<Boleto> responseEntity = restTemplate.exchange("/boletos/find/1", HttpMethod.GET, request,
		Boleto.class);

	// Corroboro que el endpoint me regresa el estatus esperado
	assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testFindByIdUnSuccesfull() {
	// Creo el encabezado
	HttpHeaders headers = new HttpHeaders();
	headers.set("content-type", MediaType.APPLICATION_JSON.toString());

	// Creo la petición con el alumno como body y el encabezado
	HttpEntity<Boleto> request = new HttpEntity<>(headers);

	// pasamos un id no existente
	ResponseEntity<Boleto> responseEntity = restTemplate.exchange("/boletos/find/20", HttpMethod.GET, request,
		Boleto.class);

	// Corroboro que el endpoint me regresa el estatus esperado
	assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
    
    //Update 
    @Test
    public void testUpdateSuccesfull() {
	Boleto boleto = Boleto.builder()
		.Id(1)
		.cliente("Gabriel")
		.pelicula("Coraje")
		.cantidad(3)
		.fecha("01-01-2022")
		.horario("9:00")
		.build();
	

	// Creo el encabezado
	HttpHeaders headers = new HttpHeaders();
	headers.set("content-type", MediaType.APPLICATION_JSON.toString());

	// Creo la petición con el alumno como body y el encabezado
	HttpEntity<Boleto> request = new HttpEntity<>(boleto, headers);

	ResponseEntity<Boleto> responseEntity = restTemplate.exchange("/boletos/update", HttpMethod.PUT, request,
		Boleto.class);

	// Corroboro que el endpoint me regresa el estatus esperado
	assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

	// verifico el cambio la clave del
	assertEquals(boleto.getPelicula(), responseEntity.getBody().getPelicula());
    }

    @Test
    public void testUpdateUnSuccesfull() {
	Boleto boleto = Boleto.builder()
		.Id(10)
		.cliente("Gabriel")
		.pelicula("Coraje")
		.cantidad(3)
		.fecha("01-01-2022")
		.horario("9:00")
		.build();
	

	// Creo el encabezado
	HttpHeaders headers = new HttpHeaders();
	headers.set("content-type", MediaType.APPLICATION_JSON.toString());

	// Creo la petición con el alumno como body y el encabezado
	HttpEntity<Boleto> request = new HttpEntity<>(boleto, headers);

	ResponseEntity<Boleto> responseEntity = restTemplate.exchange("/boletos/update", HttpMethod.PUT, request,
		Boleto.class);

	// Corroboro que el endpoint me regresa el estatus esperado
	assertEquals(HttpStatus.NOT_MODIFIED, responseEntity.getStatusCode());

    }
    
    @Test
    public void testDeleteSuccesful() {
	// Creo el encabezado
	HttpHeaders headers = new HttpHeaders();
	headers.set("content-type", MediaType.APPLICATION_JSON.toString());

	// Creo la petición con el alumno como body y el encabezado
	HttpEntity<Boleto> request = new HttpEntity<>(headers);

	// id existente
	ResponseEntity<String> responseEntity = restTemplate.exchange("/boletos/delete/1", HttpMethod.DELETE, request,
		String.class);

	// Corroboro que el endpoint me regresa el estatus esperado
	assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteUnSuccesful() {

	// Creo el encabezado
	HttpHeaders headers = new HttpHeaders();
	headers.set("content-type", MediaType.APPLICATION_JSON.toString());

	// Creo la petición con el alumno como body y el encabezado
	HttpEntity<Boleto> request = new HttpEntity<>(headers);

	// id existente
	ResponseEntity<String> responseEntity = restTemplate.exchange("/boletos/delete/20", HttpMethod.DELETE, request,
		String.class);

	// Corroboro que el endpoint me regresa el estatus esperado
	assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());

    }
    
    
    

}
