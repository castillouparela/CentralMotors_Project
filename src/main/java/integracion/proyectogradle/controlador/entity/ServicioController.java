package integracion.proyectogradle.controlador.entity;

import integracion.proyectogradle.entity.Servicio;
import integracion.proyectogradle.services.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ServicioController {

    //---SERVICIO---//
    @Autowired
    private IServicioService servicioService;

    @GetMapping("/servicios")
    public List<Servicio> findAllServices(){
        return servicioService.findAll();
    }

    @GetMapping("/servicios/{id}")
    public Servicio findByIdService(@PathVariable Long id){
        return servicioService.findById(id).orElse(null);
    }

    @GetMapping("servicios/mayorValor") //Ventas que superan los de 2M
    public List<Servicio> findGreater2MServices() {
        List<Servicio> servicesGreater = new ArrayList<Servicio>();
        for (Servicio s: servicioService.findAll()){
            if(s.getValorservicio()>2000000)
                servicesGreater.add(findByIdService(s.getId_servicio()));
        } return servicesGreater;
    }

    @DeleteMapping("servicios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServices(@PathVariable Long id){
        servicioService.delete(id);
    }

    @PostMapping("/servicios")
    @ResponseStatus(HttpStatus.CREATED)
    public Servicio createServices(@RequestBody Servicio servicio){
        return servicioService.save(servicio);
    }

    @PutMapping("/servicios/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Servicio updateServices(@PathVariable Long id, @RequestBody Servicio servicio) {
        Servicio changeServices=servicioService.findById(id).orElse(null);
        changeServices.setUsado_id_vehiculo(servicio.getUsado_id_vehiculo());
        changeServices.setCliente_id_persona(servicio.getCliente_id_persona());
        changeServices.setTecnico_id_persona(servicio.getTecnico_id_persona());
        changeServices.setFechainicio(servicio.getFechainicio());
        changeServices.setFechafinalizacion(servicio.getFechafinalizacion());
        changeServices.setTiposervicio(servicio.getTiposervicio());
        changeServices.setValorservicio(servicio.getValorservicio());
        changeServices.setObservaciones(servicio.getObservaciones());
        return servicioService.save(changeServices);
    }

}
