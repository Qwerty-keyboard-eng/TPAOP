package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Concurso {
    private int id;
    private String nombre;
    private LocalDate fechaInicioInscripcion;
    private LocalDate fechaFinInscripcion;

    public Concurso(int id, String nombre, LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion) {
        if (id <= 0) {
            throw new ExcepcionAplicacion("El ID del concurso debe ser positivo.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new ExcepcionAplicacion("El nombre del concurso no puede ser vacío.");
        }
        if (fechaInicioInscripcion == null || fechaFinInscripcion == null) {
            throw new ExcepcionAplicacion("Las fechas de inscripción no pueden ser nulas.");
        }
        if (fechaInicioInscripcion.isAfter(fechaFinInscripcion)) {
            throw new ExcepcionAplicacion("La fecha de inicio de inscripción no puede ser posterior a la fecha de fin.");
        }

        this.id = id;
        this.nombre = nombre;
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
    }

    public Concurso(int id, String nombre, String fechaInicioStr, String fechaFinStr, String dateFormat) {
        this(id, nombre,
                LocalDate.parse(fechaInicioStr, DateTimeFormatter.ofPattern(dateFormat)),
                LocalDate.parse(fechaFinStr, DateTimeFormatter.ofPattern(dateFormat)));
    }

    public boolean estaInscripcionAbierta(LocalDate fechaActual) {
        if (fechaActual == null) {
            fechaActual = LocalDate.now();
        }
        return !fechaActual.isBefore(fechaInicioInscripcion) && !fechaActual.isAfter(fechaFinInscripcion);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicioInscripcion() {
        return fechaInicioInscripcion;
    }

    public LocalDate getFechaFinInscripcion() {
        return fechaFinInscripcion;
    }

    @Override
    public String toString() {
        return id + " - " + nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concurso concurso = (Concurso) o;
        return id == concurso.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}