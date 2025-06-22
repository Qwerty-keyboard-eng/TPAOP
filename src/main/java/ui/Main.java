package ui;



import persistencia.ArchivoConcursoDao;
import persistencia.ArchivoParticipanteDao;
import servicios.ServicioInscripcionImpl;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ArchivoConcursoDao concursoDao = new ArchivoConcursoDao();
                    ArchivoParticipanteDao participanteDao = new ArchivoParticipanteDao();
                    ServicioInscripcionImpl servicio = new ServicioInscripcionImpl(concursoDao, participanteDao);

                    new RadioCompetition(servicio);
                } catch (Exception e) {
                    System.err.println("Error al iniciar la aplicación: " + e.getMessage());
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage(), "Error Fatal", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}