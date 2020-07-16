package mx.uam.tsis.ejemplobackend.servicios;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.tarjetaPagoService;
import mx.uam.tsis.ejemplobackend.negocio.modelo.tarjetaPago;

@RestController
@Slf4j
public class tarjetaPagoController {
	
	@Autowired
	private tarjetaPagoService servicio;
	
	@ApiOperation(
			value=" pago nuevo",
			notes="Permite guardar informacion de un nuevo pago ")
	@PostMapping(path = "/pagos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid tarjetaPago nuevoPago) {
				
		log.info("Recibí llamada a create con un nuevo pago "+ nuevoPago);
		
		tarjetaPago pago = servicio.create(nuevoPago);
		
		if(pago != null) {
			log.info("Creación de pelicula exitosa");
			return ResponseEntity.status(HttpStatus.CREATED).body(pago);
			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede guardar la informacion de pago");
		}
	

	}

}
