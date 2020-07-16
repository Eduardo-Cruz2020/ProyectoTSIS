package mx.uam.tsis.ejemplobackend.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.tsis.ejemplobackend.datos.PeliculaRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Boleto;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Pelicula;
import mx.uam.tsis.ejemplobackend.servicios.PeliculaController;

@ExtendWith(MockitoExtension.class)
public class PeliculaServicesTest {
	@Mock
	private PeliculaRepository peliculaRepositoryMock;
	 @InjectMocks
	 private PeliculaService peliculaService;
	 
	 @Test
		public void  testSuccesfulCreate (){
			
			Pelicula peli=new Pelicula();	
			peli.setId(1);
			peli.setClasificacion("Familira");
			peli.setDescripcion("nemo");
			peli.setFecha("12-06-20");
			peli.setGenero("Animado");
			peli.setHorarios("8:00");
			peli.setImagenURL("URL");
			peli.setTitulo("Buscando a nemo");
			
			when(peliculaRepositoryMock.save(peli)).thenReturn(peli);
			// Aqui ejecuto a la unidad que quiero probar
			peli = peliculaService.create(peli);
					
			// Aqui compruebo el resultado
			assertNotNull(peli); // Probar que la referencia a alumno no es nula
		}
	 
	 @Test
		public void testSuccesfulretrieveAll() {
			
			Iterable<Pelicula> result ;
			result= peliculaService.retrieveAll();
					
			// Aqui compruebo el resultado
			assertNotNull(result); // Probar que la referencia a alumno no es nula
			
		}
	 
	 @Test
		public void testSuccesfulretriveById() {
		    Pelicula peli=new Pelicula();	
			peli.setId(2);
			peli.setClasificacion("Familira");
			peli.setDescripcion("nemo");
			peli.setFecha("12-06-20");
			peli.setGenero("Animado");
			peli.setHorarios("8:00");
			peli.setImagenURL("URL");
			peli.setTitulo("Buscando a nemo");
			
			// Stubbing para grupoRepository
			when(peliculaRepositoryMock.findById(peli.getId())).thenReturn(Optional.of(peli));
					
			Pelicula result ;
			result= peliculaService.retrieve(2);
					
			// Aqui compruebo el resultado
			assertNotNull(result); // Probar que la referencia a alumno no es nula
	 }
	
	 
	 @Test
		public void testSuccesfulDelete() {
			
			boolean delete=peliculaService.delete(1);
			boolean delete2=peliculaService.delete(2);
			boolean delete3=peliculaService.delete(3);
			assertEquals(false,delete);
		}

	 

}
