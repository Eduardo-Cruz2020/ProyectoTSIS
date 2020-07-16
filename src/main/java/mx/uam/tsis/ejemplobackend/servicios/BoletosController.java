package mx.uam.tsis.ejemplobackend.servicios;

import java.util.Optional;

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

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.IBoletosService;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Boleto;

@Slf4j
@RestController
public class BoletosController {
    
    @Autowired
    private IBoletosService boletosService;
   
    @PostMapping(path = "/boletos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create( @RequestBody @Valid Boleto boleto) {
    	log.info("recibi llamada a create boleto con" );
	
	Optional<Boleto> bol = boletosService.save(boleto);
	if(bol.isPresent()) {
	    return ResponseEntity.status(HttpStatus.CREATED).body(bol.get());
	}else {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
    }

    @GetMapping(path = "/boletos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
	Optional<Iterable<Boleto>> boletos = boletosService.findAll();
	if(boletos.isPresent()) {
	    return ResponseEntity.status(HttpStatus.OK).body(boletos.get());
	}else {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
    }

    @GetMapping(path = "/boletos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
	Optional<Boleto> boleto = boletosService.findById(id);
	if(boleto.isPresent()) {
	    return ResponseEntity.status(HttpStatus.OK).body(boleto.get());
	}else {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
    }

    @PutMapping(path = "/boletos/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody @Valid Boleto boleto) {
	log.info("Entrando a update");
	Optional<Boleto> bol = boletosService.update(boleto);
	if(bol.isPresent()) {
	    return ResponseEntity.status(HttpStatus.CREATED).body(bol.get());
	}else {
	    return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
	}
    }

    @DeleteMapping("/boletos/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
	if(boletosService.deleteById(id)) {
	    return ResponseEntity.status(HttpStatus.OK).build();
	}else {
	    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}
    }


}
