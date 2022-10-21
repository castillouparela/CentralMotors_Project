package integracion.proyectogradle.controlador;

import integracion.proyectogradle.entity.Asesor;
import integracion.proyectogradle.entity.Cliente;
import integracion.proyectogradle.entity.Persona;
import integracion.proyectogradle.entity.Tecnico;
import integracion.proyectogradle.services.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControladorRestTest {

    @InjectMocks
    IPersonaService personaService = Mockito.mock(PersonaService.class);
    @InjectMocks
    IAsesorService asesorService = Mockito.mock(AsesorService.class);
    @InjectMocks
    IClienteService clienteService = Mockito.mock(ClienteService.class);
    @InjectMocks
    ITecnicoService tecnicoService = Mockito.mock(TecnicoService.class);

    @Mock
    Persona persona;
    @Mock
    Asesor asesor;
    @Mock
    Cliente cliente;
    @Mock
    Tecnico tecnico;

    @BeforeEach
    void setUp() {
        System.out.println("La prueba ha iniciado");

        //--- Instanciación de Persona ---//
        persona = new Persona();
        persona.setId_persona(1620L);
        persona.setNombrecompleto("Roberto Camus");
        persona.setPersona_type("Clientes");
        persona.setEmail("testing@gmail.com");
        persona.setTelefono("8392458");
        persona.setDireccion("Calle 43A # 42 - 09");
        persona.setNick("RobertKamus01");
        persona.setFechanacimiento(new Date(1998, Calendar.FEBRUARY, 4));
        //-------------------------------//
        //--- Instanciación de Asesor ---//
        asesor = new Asesor();
        asesor.setId_persona(2620L);
        asesor.setComision(12.88);
        asesor.setGestionesactivas((byte) 2);
        asesor.setGestionescompletadas((byte)5);
        asesor.setSalario(4500000);
        //--- Instanciación de Cliente ---//
        cliente = new Cliente();
        cliente.setId_persona(3620L);
        cliente.setCompras(2);
        cliente.setTipo("VIP");
        //--- Instanciación de Tecnico ---//
        tecnico = new Tecnico();
        tecnico.setId_persona(4620L);
        tecnico.setEspecialidad("Vehículos pesados");
        tecnico.setExperiencia(2);
        tecnico.setSalario(2800000);
        tecnico.setVehiculosreparados((byte) 3);

    }

    @AfterEach
    void tearDown() {
        System.out.println("La prueba se ha completado");
    }

    @Test
    void createPerson() {
        when(personaService.save(persona)).thenReturn(persona);
        System.out.println(persona.toString());
    }

    @Test
    void createAdvisers() {
        when(asesorService.save(asesor)).thenReturn(asesor);
        System.out.println(asesor.toString());
    }

    @Test
    void createTechnicians() {
        when(tecnicoService.save(tecnico)).thenReturn(tecnico);
        System.out.println(tecnico.toString());
    }

    @Test
    void createClients() {
        when(clienteService.save(cliente)).thenReturn(cliente);
        System.out.println(cliente.toString());
    }

    @Test
    void findAllPerson() {
        when(personaService.findAll()).thenReturn(Arrays.asList(persona));
        assertNotNull(personaService.findAll());
    }

    @Test
    void findAllAdvisers() {
        when(asesorService.findAll()).thenReturn(Arrays.asList(asesor));
        assertNotNull(asesorService.findAll());
    }

    @Test
    void findAllTechnicians() {
        when(tecnicoService.findAll()).thenReturn(Arrays.asList(tecnico));
        assertNotNull(tecnicoService.findAll());
    }

    @Test
    void findAllClients() {
        when(clienteService.findAll()).thenReturn(Arrays.asList(cliente));
        assertNotNull(clienteService.findAll());
    }

    @Test
    void findByIdPerson() {
        when(personaService.findById(1620L)).thenReturn(Optional.ofNullable(persona));
        assertEquals(personaService.findById(persona.getId_persona()), Optional.of(persona));
    }

    @Test
    void findByIdAdvisers() {
        when(asesorService.findById(2620L)).thenReturn(Optional.ofNullable(asesor));
        assertEquals(asesorService.findById(asesor.getId_persona()), Optional.of(asesor));
    }

    @Test
    void findByIdTechnicians() {
        when(tecnicoService.findById(4620L)).thenReturn(Optional.ofNullable(tecnico));
        assertEquals(tecnicoService.findById(tecnico.getId_persona()), Optional.of(tecnico));
    }

    @Test
    void findByIdClients() {
        when(clienteService.findById(3620L)).thenReturn(Optional.ofNullable(cliente));
        assertEquals(clienteService.findById(cliente.getId_persona()), Optional.of(cliente));
    }

    @Test
    void deletePerson() {
        when(personaService.findById(1620L)).thenReturn(Optional.of(persona));
        personaService.delete(persona.getId_persona());
        verify(personaService).delete(persona.getId_persona());
    }

    @Test
    void deleteAdvisers() {
        when(asesorService.findById(2620L)).thenReturn(Optional.of(asesor));
        asesorService.delete(asesor.getId_persona());
        verify(asesorService).delete(asesor.getId_persona());
    }

    @Test
    void deleteTechnicians() {
        when(tecnicoService.findById(4620L)).thenReturn(Optional.of(tecnico));
        tecnicoService.delete(tecnico.getId_persona());
        verify(tecnicoService).delete(tecnico.getId_persona());
    }

    @Test
    void deleteClients() {
        when(clienteService.findById(3620L)).thenReturn(Optional.of(cliente));
        clienteService.delete(cliente.getId_persona());
        verify(clienteService).delete(cliente.getId_persona());
    }

}