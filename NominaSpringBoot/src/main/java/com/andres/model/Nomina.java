package com.andres.model;

import jakarta.persistence.*;
import java.util.UUID;

/**
 * Clase que representa las nóminas de los empleados.
 * @author Andrés Jesús
 */
@Entity
@Table(name = "Nominas")
public class Nomina {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; // Identificador único de la nómina

    @OneToOne
    @JoinColumn(name = "dni_empleado", referencedColumnName = "dni")
    private Empleados empleado; // Empleado asociado a la nómina

    private int sueldo; // Sueldo de la nómina

    public Nomina() {
    }

    /**
     * Constructor de la nómina para un empleado.
     * @param empleado El empleado asociado a la nómina.
     */
    public Nomina(Empleados empleado) {
        this.empleado = empleado;
        this.sueldo = calcularSueldo(empleado);
    }

    /**
     * Obtiene el ID de la nómina.
     * @return El ID de la nómina.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Establece el ID de la nómina.
     * @param id El ID de la nómina.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Obtiene el empleado asociado a la nómina.
     * @return El empleado asociado a la nómina.
     */
    public Empleados getEmpleado() {
        return empleado;
    }

    /**
     * Establece el empleado asociado a la nómina y recalcula el sueldo.
     * @param empleado El empleado asociado a la nómina.
     */
    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
        this.sueldo = calcularSueldo(empleado);
    }

    /**
     * Obtiene el sueldo de la nómina.
     * @return El sueldo de la nómina.
     */
    public int getSueldo() {
        return sueldo;
    }

    /**
     * Establece el sueldo de la nómina.
     * @param sueldo El sueldo de la nómina.
     */
    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    /**
     * Calcula el sueldo para un empleado específico.
     * @param e El empleado para el que se calcula el sueldo.
     * @return El sueldo calculado para el empleado.
     */
    public int calcularSueldo(Empleados e) {
        int[] SUELDO_BASE = {50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000};
        return SUELDO_BASE[e.getCategoria() - 1] + 5000 * e.getAnyos();
    }
}
