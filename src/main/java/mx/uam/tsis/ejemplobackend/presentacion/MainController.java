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
	@GetMapping("/boletos")
	public String boleto() {
		
		
		return "boletos";
	}
	@GetMapping("/pelicula")
	public String pelicula() {
		
	
		return "pelicula";
	}

}