package integracion.proyectogradle.services;

import integracion.proyectogradle.dao.IAsesorDao;
import integracion.proyectogradle.entity.Asesor;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AsesorServiceTest {

    @InjectMocks
    AsesorService asesorService;

    @Mock
    IAsesorDao dao;

    private Asesor asesor;
    @BeforeEach //antes de que corra cada test
    public void init(){
        System.out.println("Entro a Init");
        MockitoAnnotations.initMocks(this);
        System.out.println("CONTITI");
        //creo el asesor completamente
        asesor = new Asesor();
        long id = 10024;
        asesor.setId_persona(id);
        asesor.setSalario(5000900);
        byte completadas=5, activas =2;
        asesor.setGestionescompletadas(completadas);
        asesor.setGestionesactivas(activas);
        asesor.setComision(0.36);

        System.out.println("ID_Person" + asesor.getId_persona());
    }

    @Test
    void findAll(){
        System.out.println("Entro a findAll");
        when(dao.findAll()).thenReturn(Arrays.asList(asesor));
        assertNotNull(asesorService.findAll());
        System.out.println("Salgoooo");
    }

}