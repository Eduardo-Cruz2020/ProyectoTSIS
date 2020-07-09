package mx.uam.tsis.ejemplobackend.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.tsis.ejemplobackend.negocio.modelo.Boleto;

public interface IBoletosRepository extends CrudRepository<Boleto, Integer> {

}
