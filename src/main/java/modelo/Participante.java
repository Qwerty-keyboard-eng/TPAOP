package modelo;

import java.util.Objects;

public class Participante {
    private String apellido;
    private String nombre;
    private String dni;
    private String telefono;
    private String email;

    public Participante(String apellido, String nombre, String dni, String telefono, String email) {
        if (apellido == null || apellido.isBlank()) {
            throw new ExcepcionAplicacion("El apellido no puede ser vacío.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new ExcepcionAplicacion("El nombre no puede ser vacío.");
        }
        if (dni == null || dni.isBlank()) {
            throw new ExcepcionAplicacion("El DNI no puede ser vacío.");
        }
        if (!checkEmail(email)) {
            throw new ExcepcionAplicacion("El email debe ser válido.");
        }
        if (!checkPhone(telefono)) {
            throw new ExcepcionAplicacion("El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN.");
        }

        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.dni = dni;
    }

    private boolean checkEmail(String email) {
        if (email == null || email.isBlank()) return false;
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    private boolean checkPhone(String telefono) {
        if (telefono == null || telefono.isBlank()) return false;
        String regex = "\\d{4}-\\d{6}";
        return telefono.matches(regex);
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participante that = (Participante) o;
        return Objects.equals(dni, that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}