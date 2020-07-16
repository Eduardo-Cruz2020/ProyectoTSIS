package mx.uam.tsis.ejemplobackend.servicios;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.FormaPagoService;
import mx.uam.tsis.ejemplobackend.negocio.modelo.FormaPago;

@RestController
@Slf4j
public class FormaPagoController {
	
	@Autowired
	private FormaPagoService servicio;
	
	@PostMapping(path = "/formaPago", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid FormaPago nuevoPago) {
				
		
		FormaPago pago = servicio.create(nuevoPago);
		
		if(pago != null) {
			log.info("entre el metodo post");
			return ResponseEntity.status(HttpStatus.CREATED).body(pago);
			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede crear pago");
		}
	

	}

}
