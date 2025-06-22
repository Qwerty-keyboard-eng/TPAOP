package persistencia;

import modelo.Concurso;
import modelo.ExcepcionAplicacion;
import modelo.Participante;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ArchivoParticipanteDao implements ParticipanteDao {

    private static final String INSCRIPTOS_FILE = "inscriptos.txt";

    public ArchivoParticipanteDao() {

        try {
            if (!Files.exists(Paths.get(INSCRIPTOS_FILE))) {
                Files.createFile(Paths.get(INSCRIPTOS_FILE));
                // Opcional: escribir cabecera si el archivo está vacío
                try (PrintWriter out = new PrintWriter(new FileWriter(INSCRIPTOS_FILE, true))) {
                    out.println("apellido, nombre, telefono, email, idconcurso");
                }
            }
        } catch (IOException e) {
            throw new ExcepcionAplicacion("No se pudo crear o verificar el archivo de inscriptos: " + INSCRIPTOS_FILE, e);
        }
    }

    @Override
    public void guardarInscripcion(Participante participante, Concurso concurso) {
        try (PrintWriter out = new PrintWriter(new FileWriter(INSCRIPTOS_FILE, true))) { // 'true' para append
            String line = String.format("%s, %s, %s, %s, %d",
                    participante.getApellido(),
                    participante.getNombre(),
                    participante.getTelefono(),
                    participante.getEmail(),
                    concurso.getId());
            out.println(line);
        } catch (IOException e) {
            throw new ExcepcionAplicacion("Error al escribir en el archivo de inscriptos: " + INSCRIPTOS_FILE, e);
        }
    }
}