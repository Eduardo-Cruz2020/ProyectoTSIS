package mx.uam.tsis.ejemplobackend.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String index() {
		
	
		return "index";
	}
	@GetMapping("/login")
	public String login() {
		
	
		return "login";
	}
	@GetMapping("/cartelera")
	public String cartelera() {
		
	
		return "cartelera";
	}
	@GetMapping("/compraBoletos")
	public String compraBoletos() {
		
		
		return "compraBoletos";
	}
	@GetMapping("/peliculasCatalogo")
	public String peliculasCatalogo() {
		
	
		return "peliculasCatalogo";
	}
	@GetMapping("/crearPelicula")
	public String crearPelicula() {
	
		return "crearPelicula";
	}
	@GetMapping("/boletos/pago")
	public String pagoBoleto() {
		
	
		return "pagoTarjeta";
	}

	@GetMapping("/error") // "resuelve" problema de redireccion de login
	public String aux() {
	    return "peliculasCatalogo";
	}
	
}