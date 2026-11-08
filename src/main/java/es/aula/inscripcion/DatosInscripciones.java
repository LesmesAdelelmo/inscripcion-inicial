package es.aula.inscripcion;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class DatosInscripciones {

    private static final ObservableList<InscripcionTorneo> inscripciones =
        FXCollections.observableArrayList(
            new InscripcionTorneo("Lucía Fernández", "League of Legends (5v5)",
                LocalDate.of(2026, 12, 10), true, "Capitana del equipo Los Halcones."),
            new InscripcionTorneo("Marcos Ortega", "EA Sports FC (1v1)",
                LocalDate.of(2026, 12, 12), false, ""),
            new InscripcionTorneo("Sara Gómez", "Valorant (5v5)",
                LocalDate.of(2026, 12, 11), true, "Necesita acceso para silla de ruedas a la sala."),
            new InscripcionTorneo("Daniel Ruiz", "Super Smash Bros (1v1)",
                LocalDate.of(2026, 12, 15), true, "Trae su propio mando de GameCube."),
            new InscripcionTorneo("Elena Navarro", "Overcooked 2 (4v4)",
                LocalDate.of(2026, 12, 13), false, "Se apunta con tres amigas. Prefiere turno de tarde."),
            new InscripcionTorneo("Pablo Castillo", "Dota 2 (5v5)",
                LocalDate.of(2026, 12, 14), false, "Primera vez en un torneo presencial.")
        );

    public static ObservableList<InscripcionTorneo> getInscripciones() {
        return inscripciones;
    }
}