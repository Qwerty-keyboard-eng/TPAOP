package modelo;

public class ExcepcionAplicacion extends RuntimeException {
    public ExcepcionAplicacion(String message) {
        super(message);
    }

    public ExcepcionAplicacion(String message, Throwable cause) {
        super(message, cause);
    }
}