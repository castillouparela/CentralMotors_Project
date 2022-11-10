package integracion.proyectogradle.controlador.entity;

import integracion.proyectogradle.entity.*;
import integracion.proyectogradle.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class PersonaController {

    //---PERSONAS---//
    @Autowired
    private IPersonaService personaService;
    @Autowired
    private IAsesorService asesorService;
    @Autowired
    private ITecnicoService tecnicoService;
    @Autowired
    private IClienteService clienteService;

    @GetMapping("personas")
    public String findAllPerson(Model model){
        List <Persona> listadoPersonas =  personaService.findAll();
        model.addAttribute("titulo", "Gestión de Personas");
        model.addAttribute("personas", listadoPersonas);
        return "entities/personas";
    }

    @GetMapping("personas/asesores")
    public List<Asesor> findAllAdvisers(){
        return asesorService.findAll();
    }
    @GetMapping("personas/tecnicos")
    public List<Tecnico> findAllTechnicians(){
        return tecnicoService.findAll();
    }
    @GetMapping("personas/clientes")
    public List<Cliente> findAllClients(){
        return clienteService.findAll();
    }

    @GetMapping("personas/{id}")
    public Persona findByIdPerson(@PathVariable Long id){
        return personaService.findById(id).orElse(null);
    }
    @GetMapping("personas/asesores/{id}")
    public Asesor findByIdAdvisers(@PathVariable Long id){
        return asesorService.findById(id).orElse(null);
    }
    @GetMapping("personas/tecnicos/{id}")
    public Tecnico findByIdTechnicians(@PathVariable Long id){
        return tecnicoService.findById(id).orElse(null);
    }

    @GetMapping("personas/clientes/{id}") //con este método no es necesario especificar el tipo de objeto a retornar
    public ResponseEntity<?> findByIdClients(@PathVariable Long id){
        Cliente cliente = clienteService.findById(id).orElse(null);
        Map<String,String> response= new HashMap<>();
        if(cliente == null){
            response.put("Mensaje","La categoría buscada no existe");
            //el http status permite reconocer la situación que ocurre de fondo en el backend
            return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
    }

    @GetMapping("personas/asesores/mayorSal") //Asesores que ganan más de 2000000
    public List<Asesor> findGreater2MAdvisers() {
        List<Asesor> advisers = new ArrayList<Asesor>();
        for (Asesor a: asesorService.findAll()){
            if(a.getSalario()>2000000)
                advisers.add(findByIdAdvisers(a.getId_persona()));
        } return advisers;
    }

    @GetMapping("personas/tecnicos/mayorSal") //Tecnicos que ganan más de 2000000
    public List<Tecnico> findGreater2MTechnicians() {
        List<Tecnico> technicians = new ArrayList<Tecnico>();
        for (Tecnico t: tecnicoService.findAll()){
            if(t.getSalario()>2000000)
                technicians.add(findByIdTechnicians(t.getId_persona()));
        } return technicians;
    }

    @DeleteMapping("personas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long id){
        personaService.delete(id);
    }
    @DeleteMapping("personas/asesores/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdvisers(@PathVariable Long id){
        asesorService.delete(id);
    }
    @DeleteMapping("personas/tecnicos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTechnicians(@PathVariable Long id){
        tecnicoService.delete(id);
    }
    @DeleteMapping("personas/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClients(@PathVariable Long id){
        clienteService.delete(id);
    }

    @PostMapping("personas")
    @ResponseStatus(HttpStatus.CREATED)
    public Persona createPerson(@RequestBody Persona persona){
        return personaService.save(persona);
    }
    @PostMapping("personas/asesores")
    @ResponseStatus(HttpStatus.CREATED)
    public Asesor createAdvisers(@RequestBody Asesor asesor){
        return asesorService.save(asesor);
    }
    @PostMapping("personas/tecnicos")
    @ResponseStatus(HttpStatus.CREATED)
    public Tecnico createTechnicians(@RequestBody Tecnico tecnico){
        return tecnicoService.save(tecnico);
    }
    @PostMapping("personas/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente createClients(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @PutMapping("personas/{id}") //Modificado, enfocado en el manejo de errores
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updatePerson(@PathVariable Long id, @RequestBody Persona persona) {
        Persona changePerson=personaService.findById(id).orElse(null);
        Map<String,String> response= new HashMap<>();
        if(changePerson == null){
            response.put("Error Update","La persona a actualizar no existe");
            //el http status permite reconocer la situación que ocurre de fondo en el backend
            return new ResponseEntity<Map<String,String>>(response,HttpStatus.NOT_FOUND);
        }
        try{
            changePerson.setNombrecompleto(persona.getNombrecompleto());
            changePerson.setFechanacimiento(persona.getFechanacimiento());
            changePerson.setNick(persona.getNick());
            changePerson.setTelefono(persona.getTelefono());
            changePerson.setEmail(persona.getEmail());
            changePerson.setDireccion(persona.getDireccion());
            changePerson.setPersona_type(persona.getPersona_type());
            personaService.save(changePerson);
        } catch (Exception e) {
            response.put("ERROR", "Error de inserción");
            return new ResponseEntity<Map<String,String>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Persona>(changePerson,HttpStatus.OK);
    }
    @PutMapping("personas/{id}/persona_type")
    @ResponseStatus(HttpStatus.CREATED)
    public Persona updateTypePerson(@PathVariable Long id, @RequestBody Persona persona) {
        Persona changePerson=personaService.findById(id).orElse(null);
        changePerson.setPersona_type(persona.getPersona_type());
        return personaService.save(changePerson);
    }

    @PutMapping("personas/asesores/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Asesor updateAdvisers(@PathVariable Long id, @RequestBody Asesor asesor) {
        Asesor changeAdvisers=asesorService.findById(id).orElse(null);
        changeAdvisers.setComision(asesor.getComision());
        changeAdvisers.setGestionesactivas(asesor.getGestionesactivas());
        changeAdvisers.setGestionescompletadas(asesor.getGestionescompletadas());
        changeAdvisers.setSalario(asesor.getSalario());
        return asesorService.save(changeAdvisers);
    }
    @PutMapping("personas/tecnicos/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Tecnico updateTechnicians(@PathVariable Long id, @RequestBody Tecnico tecnico) {
        Tecnico changeTechnicians=tecnicoService.findById(id).orElse(null);
        changeTechnicians.setEspecialidad(tecnico.getEspecialidad());
        changeTechnicians.setExperiencia(tecnico.getExperiencia());
        changeTechnicians.setSalario(tecnico.getSalario());
        changeTechnicians.setVehiculosreparados(tecnico.getVehiculosreparados());
        return tecnicoService.save(changeTechnicians);
    }
    @PutMapping("personas/clientes/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente updateClients(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente changeClients=clienteService.findById(id).orElse(null);
        changeClients.setCompras(cliente.getCompras());
        changeClients.setTipo(cliente.getTipo());
        return clienteService.save(changeClients);
    }

}
