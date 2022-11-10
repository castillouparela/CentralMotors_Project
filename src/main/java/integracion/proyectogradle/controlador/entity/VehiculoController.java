package integracion.proyectogradle.controlador.entity;

import integracion.proyectogradle.entity.*;
import integracion.proyectogradle.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class VehiculoController {

    //---VEHICULOS---//
    @Autowired
    private IVehiculoService vehiculoService;
    @Autowired
    private INuevoService nuevoService;
    @Autowired
    private IUsadoService usadoService;


    @GetMapping("/vehiculos")
    public List<Vehiculo> findAllVehicles(){
        return vehiculoService.findAll();
    }
    @GetMapping("/vehiculos/nuevos")
    public List<Nuevo> findAllVehiclesNews(){
        return nuevoService.findAll();
    }
    @GetMapping("/vehiculos/usados")
    public List<Usado> findAllVehiclesUsed(){
        return usadoService.findAll();
    }

    @GetMapping("/vehiculos/{id}")
    public Vehiculo findByIdVehicles(@PathVariable Long id){
        return vehiculoService.findById(id).orElse(null);
    }
    @GetMapping("/vehiculos/nuevos/{id}")
    public Nuevo findByIdVehiclesNews(@PathVariable Long id){
        return nuevoService.findById(id).orElse(null);
    }
    @GetMapping("/vehiculos/usados/{id}")
    public Usado findByIdVehiclesUsed(@PathVariable Long id){
        return usadoService.findById(id).orElse(null);
    }

    @GetMapping("vehiculos/nuevos/mayorValor") //Vehiculos Nuevos que cuestan más de 250M
    public List<Nuevo> findGreater250MNews() {
        List<Nuevo> newsGreater = new ArrayList<Nuevo>();
        for (Nuevo n: nuevoService.findAll()){
            if(n.getValorventa()>250000000)
                newsGreater.add(findByIdVehiclesNews(n.getId_vehiculo()));
        } return newsGreater;
    }
    @GetMapping("vehiculos/nuevos/menorValor") //Vehiculos Nuevos que cuestan menos de 200M
    public List<Nuevo> findSmaller200MNews() {
        List<Nuevo> newsSmaller = new ArrayList<Nuevo>();
        for (Nuevo n: nuevoService.findAll()){
            if(n.getValorventa()<200000000)
                newsSmaller.add(findByIdVehiclesNews(n.getId_vehiculo()));
        } return newsSmaller;
    }
    @GetMapping("vehiculos/usados/menorRenta/{valorrenta}") //Vehiculos Usados que se rentan por menos de un valor X
    public List<Usado> findSmallerUsed(@PathVariable Double valorrenta) {
        List<Usado> usedSmaller = new ArrayList<Usado>();
        for (Usado u: usadoService.findAll()){
            if(u.getValorrenta()<valorrenta)
                usedSmaller.add(findByIdVehiclesUsed(u.getId_vehiculo()));
        } return usedSmaller;
    }

    @DeleteMapping("vehiculos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicles(@PathVariable Long id){
        vehiculoService.delete(id);
    }
    @DeleteMapping("vehiculos/nuevos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehiclesNews(@PathVariable Long id){
        nuevoService.delete(id);
    }
    @DeleteMapping("vehiculos/usados/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehiclesUsed(@PathVariable Long id){
        usadoService.delete(id);
    }

    @PostMapping("/vehiculos")
    @ResponseStatus(HttpStatus.CREATED)
    public Vehiculo createVehicles(@RequestBody Vehiculo vehiculo){
        return vehiculoService.save(vehiculo);
    }
    @PostMapping("/vehiculos/nuevos")
    @ResponseStatus(HttpStatus.CREATED)
    public Nuevo createVehicles(@RequestBody Nuevo nuevo){
        return nuevoService.save(nuevo);
    }
    @PostMapping("/vehiculos/usados")
    @ResponseStatus(HttpStatus.CREATED)
    public Usado createVehicles(@RequestBody Usado usado){
        return usadoService.save(usado);
    }

    @PutMapping("/vehiculos/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Vehiculo updateVehicles(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        Vehiculo changeVehicles=vehiculoService.findById(id).orElse(null);
        changeVehicles.setMatricula(vehiculo.getMatricula());
        changeVehicles.setMarca(vehiculo.getMarca());
        changeVehicles.setModelo(vehiculo.getModelo());
        changeVehicles.setVehiculo_type(vehiculo.getVehiculo_type());
        return vehiculoService.save(changeVehicles);
    }
    @PutMapping("/vehiculos/{id}/vehiculo_type")
    @ResponseStatus(HttpStatus.CREATED)
    public Vehiculo updateTypeVehicles(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        Vehiculo changeVehicles=vehiculoService.findById(id).orElse(null);
        changeVehicles.setVehiculo_type(vehiculo.getVehiculo_type());
        return vehiculoService.save(changeVehicles);
    }
    @PutMapping("/vehiculos/nuevos/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Nuevo updateVehiclesNews(@PathVariable Long id, @RequestBody Nuevo nuevo) {
        Nuevo changeVehiclesNews=nuevoService.findById(id).orElse(null);
        changeVehiclesNews.setColor(nuevo.getColor());
        changeVehiclesNews.setProcedencia(nuevo.getProcedencia());
        changeVehiclesNews.setPropietario(nuevo.getPropietario());
        changeVehiclesNews.setValorventa(nuevo.getValorventa());
        changeVehiclesNews.setGarantia(nuevo.getGarantia());
        return nuevoService.save(changeVehiclesNews);
    }
    @PutMapping("/vehiculos/usados/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Usado updateVehiclesUsed(@PathVariable Long id, @RequestBody Usado usado) {
        Usado changeVehiclesUsed=usadoService.findById(id).orElse(null);
        changeVehiclesUsed.setKilometraje(usado.getKilometraje());
        changeVehiclesUsed.setValorrenta(usado.getValorrenta());
        changeVehiclesUsed.setEstado(usado.getEstado());
        return usadoService.save(changeVehiclesUsed);
    }

}
