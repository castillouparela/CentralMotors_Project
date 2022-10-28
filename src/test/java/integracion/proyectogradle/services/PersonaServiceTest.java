package integracion.proyectogradle.services;

import integracion.proyectogradle.dao.IPersonaDao;
import integracion.proyectogradle.entity.Persona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PersonaServiceTest {

    @InjectMocks
    PersonaService personaService;
    @Mock
    IPersonaDao personaDao;
    Persona persona;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Persona persona = new Persona();
        persona.setId_persona((long)1620);
        persona.setNombrecompleto("Roberto Camus");
        persona.setPersona_type("Clientes");
        persona.setEmail("testing@gmail.com");
        persona.setTelefono("8392458");
        persona.setDireccion("Calle 43A # 42 - 09");
        persona.setNick("RobertKamus01");
        persona.setFechanacimiento(new Date(1998, Calendar.FEBRUARY, 4));
    }

    @Test
    void findAll() {
        when(personaDao.findAll()).thenReturn(Arrays.asList(persona));
        assertNotNull(personaService.findAll());
    }
}