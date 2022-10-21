package integracion.proyectogradle.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="asesores")

public class Asesor implements Serializable {

    @Id
    private Long id_persona;

    @Column
    @NotNull //se pueden especificar las excepciones desde el api rest o directamente en la base de datos
    private double comision;
    @Column
    @Size(min=4,max=16) //se pueden especificar tamaños para las variables
    private double salario;
    @Column
    private byte gestionesactivas;
    @Column
    private byte gestionescompletadas;

    public Long getId_persona() {
        return id_persona;
    }
    public void setId_persona(Long id) {
        this.id_persona = id;
    }
    public double getComision() {
        return comision;
    }
    public void setComision(double comision) {
        this.comision = comision;
    }
    public byte getGestionesactivas() {
        return gestionesactivas;
    }
    public void setGestionesactivas(byte gestionesactivas) {
        this.gestionesactivas = gestionesactivas;
    }
    public byte getGestionescompletadas() {
        return gestionescompletadas;
    }
    public void setGestionescompletadas(byte gestionescompletadas) {
        this.gestionescompletadas = gestionescompletadas;
    }
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Asesor{" +
                "id_persona=" + id_persona +
                ", comision=" + comision +
                ", salario=" + salario +
                ", gestionesactivas=" + gestionesactivas +
                ", gestionescompletadas=" + gestionescompletadas +
                '}';
    }
}

