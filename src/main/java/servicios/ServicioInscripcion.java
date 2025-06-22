package servicios;

import modelo.Concurso;
import java.time.LocalDate;
import java.util.List;

public interface ServicioInscripcion {
    List<Concurso> todosLosConcursos(LocalDate fechaActual);
    void saveInscription(String apellido, String nombre, String dni, String telefono, String email, Concurso concurso);
}