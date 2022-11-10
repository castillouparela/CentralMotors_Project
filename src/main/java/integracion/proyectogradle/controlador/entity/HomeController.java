package integracion.proyectogradle.controlador.entity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping({"/","/index","/home", "/inicio"})
	public String goHome(Model model) {
		model.addAttribute("titulo", "Bienvenido a Concesionario Central Motors");
		return "inicio";
	}

	@GetMapping({"nuevos"})
	public String concesionario(Model model) {
		model.addAttribute("titulo", "Gestión de Vehículos");
		return "personas";
	}

}
