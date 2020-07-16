package mx.uam.tsis.ejemplobackend.negocio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.tsis.ejemplobackend.datos.FormaPagoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.FormaPago;


@Service
public class FormaPagoService {
	
	@Autowired
	private FormaPagoRepository formapagoRepository ;
	
	public FormaPago create(FormaPago pago) 
	{
		
		Optional<FormaPago> pagoOpt = formapagoRepository.findById(pago.getIdFormaPago());
				
				if(!pagoOpt.isPresent()) {
					
					return formapagoRepository.save(pago);
					
				} else {
					
					return null;
					
				}
		
	}
	

}
