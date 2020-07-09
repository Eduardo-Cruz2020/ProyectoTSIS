package mx.uam.tsis.ejemplobackend.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.tsis.ejemplobackend.datos.IBoletosRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Boleto;

@ExtendWith(MockitoExtension.class)
public class BoletosServiceTest {

    @Mock
    private IBoletosRepository boletosRepositoryMock;

    @InjectMocks
    private BoletosService boletosService;

    // create
    @Test
    public void testSuccesfulCreate() {
	Boleto boleto = Boleto.builder().Id(1).cliente("Gabriel").pelicula("Shrek").cantidad(1).horario("9:00am")
		.fecha("22-07-2020").build();

	when(boletosRepositoryMock.save(boleto)).thenReturn(boleto);

	Optional<Boleto> bolOpt = Optional.of(boleto);

	assertEquals(bolOpt, boletosService.save(boleto));

    }

    @Test
    public void testUnSuccesfulCreate() {
	Boleto boleto = Boleto.builder().Id(1).cliente("Gabriel").pelicula("Shrek").cantidad(0).horario("9:00am")
		.fecha("22-07-2020").build();

	Optional<Boleto> bolOpt = Optional.empty();

	assertEquals(bolOpt, boletosService.save(boleto));

    }

    // Read

    @Test
    public void testSuccesfulFindAll() {

	List<Boleto> boletos = new ArrayList<Boleto>();

	when(boletosRepositoryMock.findAll()).thenReturn(boletos);

	Optional<Iterable<Boleto>> bolOpt = Optional.of(boletos);

	assertEquals(bolOpt, boletosService.findAll());

    }

    @Test
    public void testSuccesfullFindById() {
	Boleto boleto = Boleto.builder().Id(1).cliente("Gabriel").pelicula("Shrek").cantidad(1).horario("9:00am")
		.fecha("22-07-2020").build();

	Optional<Boleto> boletoOpt = Optional.of(boleto);

	when(boletosRepositoryMock.findById(1)).thenReturn(boletoOpt);

	assertEquals(boletoOpt, boletosService.findById(1));
    }

    @Test
    public void testUnSuccesfullFindById() {

	Optional<Boleto> boletoOpt = Optional.empty();

	assertEquals(boletoOpt, boletosService.findById(null));
    }

    // Update
    @Test
    public void testSuccesfulUpdate() {
	Boleto boleto = Boleto.builder().Id(1).cliente("Gabriel").pelicula("Shrek").cantidad(1).horario("9:00am")
		.fecha("22-07-2020").build();

	Optional<Boleto> boletoOpt = Optional.of(boleto);

	when(boletosRepositoryMock.findById(1)).thenReturn(boletoOpt);
	when(boletosRepositoryMock.save(boleto)).thenReturn(boleto);

	assertEquals(boletoOpt, boletosService.update(boleto));

    }

    @Test
    public void testUnSuccesfulUpdate() {
	Boleto boleto = Boleto.builder().Id(1).cliente("Gabriel").pelicula("Shrek").cantidad(1).horario("9:00am")
		.fecha("22-07-2020").build();

	Optional<Boleto> boletoOpt = Optional.empty();

	when(boletosRepositoryMock.findById(1)).thenReturn(boletoOpt);

	assertEquals(boletoOpt, boletosService.update(boleto));

    }

    // Delete
    @Test
    public void testSuccesfullDelete() {
	Boleto boleto = Boleto.builder().Id(1).cliente("Gabriel").pelicula("Shrek").cantidad(1).horario("9:00am")
		.fecha("22-07-2020").build();

	Optional<Boleto> boletoOpt = Optional.of(boleto);

	when(boletosRepositoryMock.findById(1)).thenReturn(boletoOpt);

	assertEquals(true, boletosService.deleteById(1));
    }
    
    @Test
    public void testUnSuccesfullDelete() {
	Optional<Boleto> boletoOpt = Optional.empty();

	when(boletosRepositoryMock.findById(1)).thenReturn(boletoOpt);

	assertEquals(false, boletosService.deleteById(1));
    }

}
