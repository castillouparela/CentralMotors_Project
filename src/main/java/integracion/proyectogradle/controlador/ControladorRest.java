package integracion.proyectogradle.controlador;

import integracion.proyectogradle.entity.*;
import integracion.proyectogradle.services.*;
import org.hibernate.boot.archive.scan.spi.ClassDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/concesionario")
@CrossOrigin(origins="201.184.144.194", methods = {RequestMethod.GET,RequestMethod.POST})
//permisos que le doy a ciertas aplicaciones para pedir, enviar información, etc.
//con * cualquier ip se puede conectar
public class ControladorRest {










}