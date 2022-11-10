package integracion.proyectogradle.controlador.entity;

import integracion.proyectogradle.entity.Venta;
import integracion.proyectogradle.services.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class VentaController {

    //---VENTA---//
    @Autowired
    private IVentaService ventaService;

    @GetMapping("/ventas")
    public List<Venta> findAllSales(){
        return ventaService.findAll();
    }

    @GetMapping("/ventas/{id}")
    public Venta findByIdSale(@PathVariable Long id){
        return ventaService.findById(id).orElse(null);
    }

    @GetMapping("ventas/mayorValor") //Ventas que superan los de 200M
    public List<Venta> findGreater200MSales() {
        List<Venta> salesGreater = new ArrayList<Venta>();
        for (Venta v: ventaService.findAll()){
            if(v.getValor()>250000000)
                salesGreater.add(findByIdSale(v.getId_venta()));
        } return salesGreater;
    }

    @DeleteMapping("ventas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSales(@PathVariable Long id){
        ventaService.delete(id);
    }

    @PostMapping("/ventas")
    @ResponseStatus(HttpStatus.CREATED)
    public Venta createSales(@RequestBody Venta venta){
        return ventaService.save(venta);
    }

    @PutMapping("/ventas/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Venta updateSales(@PathVariable Long id, @RequestBody Venta venta) {
        Venta changeSales=ventaService.findById(id).orElse(null);
        changeSales.setAsesor_id_persona(venta.getAsesor_id_persona());
        changeSales.setCliente_id_persona(venta.getCliente_id_persona());
        changeSales.setNuevo_id_vehiculo(venta.getNuevo_id_vehiculo());
        changeSales.setFecha(venta.getFecha());
        changeSales.setTipotransaccion(venta.getTipotransaccion());
        changeSales.setCuotas(venta.getCuotas());
        changeSales.setValor(venta.getValor());
        changeSales.setEstado(venta.getEstado());
        return ventaService.save(changeSales);
    }

}
