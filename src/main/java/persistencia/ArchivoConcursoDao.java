package persistencia;

import modelo.Concurso;
import modelo.ExcepcionAplicacion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArchivoConcursoDao implements ConcursoDao {

    private static final String CONCURSOS_FILE = "concursos.txt";
    private static final String DATE_FORMAT = "yyyy/MM/dd";

    @Override
    public List<Concurso> obtenerTodosLosConcursos() {
        List<Concurso> concursos = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(CONCURSOS_FILE))) {
            stream.forEach(line -> {
                try {
                    String[] parts = line.split(", ");
                    int id = Integer.parseInt(parts[0]);
                    String nombre = parts[1];
                    String fechaInicioStr = parts[2];
                    String fechaFinStr = parts[3];

                    Concurso concurso = new Concurso(id, nombre, fechaInicioStr, fechaFinStr, DATE_FORMAT);
                    concursos.add(concurso);
                } catch (Exception e) {
                    System.err.println("Error al parsear línea del archivo de concursos: " + line + " - " + e.getMessage());
                }
            });
        } catch (IOException e) {
            throw new ExcepcionAplicacion("Error al leer el archivo de concursos: " + CONCURSOS_FILE, e);
        }
        return concursos;
    }
}