package mx.uam.tsis.ejemplobackend.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.tsis.ejemplobackend.negocio.modelo.tarjetaPago;

public interface FormaPagoRepository extends CrudRepository< tarjetaPago,Integer>{

}
