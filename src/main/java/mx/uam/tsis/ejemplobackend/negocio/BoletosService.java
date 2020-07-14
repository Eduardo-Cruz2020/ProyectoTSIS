package mx.uam.tsis.ejemplobackend.negocio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.datos.IBoletosRepository;

import mx.uam.tsis.ejemplobackend.negocio.modelo.Boleto;

@Slf4j
@Service
public class BoletosService implements IBoletosService{
    
    @Autowired
    private IBoletosRepository boletosRepository;

    
    @Override
    public Optional<Boleto> save(Boleto boleto) {
	//si el numero de boletos es mayor a cero
	if(boleto.getCantidad() > 0) {
	    return Optional.of(boletosRepository.save(boleto));
	}else {
	    return Optional.empty();
	}
    }

    @Override
    public Optional<Iterable<Boleto>> findAll() {
	return Optional.of(boletosRepository.findAll());
    }

    @Override
    public Optional<Boleto> findById(Integer id) {
	if(id != null) {
	    return boletosRepository.findById(id);
	}else {
	    return Optional.empty();
	}
	
    }

    @Override
    public Optional<Boleto> update(Boleto boleto) {
	if(boleto != null) {
	    if(boletosRepository.findById(boleto.getId()).isPresent()) {
		    return Optional.of(boletosRepository.save(boleto));
		}else {
		  return Optional.empty();		  
		}
	}else {
	    return Optional.empty();
	}
    }

    @Override
    public boolean deleteById(Integer id) {
	if(id != null) {
	    if(boletosRepository.findById(id).isPresent()) {
		    try {
			    boletosRepository.deleteById(id);
			    return true;
			}catch(Exception e) {
			    log.error("Error al eliminar boleto: "+e);
			    return false;
			}
		}else {
		    return false;
		}
	}else {
	    return false;
	}
	
	
    }
    
    public Boleto findByIdPelicula(Integer idboleto) {

		// LÃ³gica de negocio
		
		Optional <Boleto> boletoOpt = boletosRepository.findById(idboleto);
		
		return boletoOpt.get();
	}


}
