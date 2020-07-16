package mx.uam.tsis.ejemplobackend.negocio;

import java.util.Optional;

import mx.uam.tsis.ejemplobackend.negocio.modelo.Boleto;

public interface IBoletosService {
    
    public Optional<Boleto> save(Boleto boleto);

    public Optional<Iterable<Boleto>> findAll();

    public Optional<Boleto> findById(Integer id);
    
    public Optional<Boleto> update(Boleto boleto);

    public boolean deleteById(Integer id);
    
    public Boleto findByIdPelicula(Integer idBoleto);


}
