package es.aula.inscripcion;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InscripcionTorneo {

    // Propiedad observable.
    private final StringProperty nombre = new SimpleStringProperty();

    // Atributos ordinarios.
    private String modalidad;
    private LocalDate fecha;
    private boolean equipoPropio;
    private String observaciones;

    public InscripcionTorneo(String nombre, String modalidad, LocalDate fecha,
                             boolean equipoPropio, String observaciones) {
        this.nombre.set(nombre);
        this.modalidad = modalidad;
        this.fecha = fecha;
        this.equipoPropio = equipoPropio;
        this.observaciones = observaciones;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nuevoNombre) {
        nombre.set(nuevoNombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isEquipoPropio() {
        return equipoPropio;
    }

    public void setEquipoPropio(boolean equipoPropio) {
        this.equipoPropio = equipoPropio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}