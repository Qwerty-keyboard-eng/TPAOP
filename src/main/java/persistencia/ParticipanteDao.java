package persistencia;

import modelo.Participante;
import modelo.Concurso;

public interface ParticipanteDao {
    void guardarInscripcion(Participante participante, Concurso concurso);
}