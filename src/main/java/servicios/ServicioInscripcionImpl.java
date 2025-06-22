package servicios;

import annotations.Log;
import modelo.Concurso;
import modelo.Participante;
import persistencia.ConcursoDao;
import persistencia.ParticipanteDao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioInscripcionImpl implements ServicioInscripcion {

    private ConcursoDao concursoDao;
    private ParticipanteDao participanteDao;

    public ServicioInscripcionImpl(ConcursoDao concursoDao, ParticipanteDao participanteDao) {
        this.concursoDao = concursoDao;
        this.participanteDao = participanteDao;
    }

    @Override
    @Log
    public List<Concurso> todosLosConcursos(LocalDate fechaActual) {
        return concursoDao.obtenerTodosLosConcursos().stream()
                .filter(c -> c.estaInscripcionAbierta(fechaActual))
                .collect(Collectors.toList());
    }

    @Override
    @Log
    public void saveInscription(String apellido, String nombre, String dni, String telefono, String email, Concurso concurso) {
        Participante participante = new Participante(apellido, nombre, dni, telefono, email);
        participanteDao.guardarInscripcion(participante, concurso);
    }
}