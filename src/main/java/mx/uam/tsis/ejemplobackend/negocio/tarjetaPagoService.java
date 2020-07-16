package mx.uam.tsis.ejemplobackend.negocio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.tsis.ejemplobackend.datos.FormaPagoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.tarjetaPago;


@Service
public class tarjetaPagoService {
	
	@Autowired
	private FormaPagoRepository formapagoRepository ;
	
	public tarjetaPago create(tarjetaPago pago) 
	{
		
		Optional<tarjetaPago> pagoOpt = formapagoRepository.findById(pago.getId());
				
				if(!pagoOpt.isPresent()) {
					
					return formapagoRepository.save(pago);
					
				} else {
					
					return null;
					
				}
		
	}
	

}
