package persistencia;

import modelo.Concurso;
import java.util.List;

public interface ConcursoDao {
    List<Concurso> obtenerTodosLosConcursos();
}