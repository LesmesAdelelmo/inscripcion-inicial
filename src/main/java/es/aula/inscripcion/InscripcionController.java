package es.aula.inscripcion;

import java.io.IOException;
import java.time.LocalDate;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InscripcionController {

    @FXML private TextField nombreField;
    @FXML private ComboBox<String> modalidadCombo;
    @FXML private DatePicker fechaPicker;
    @FXML private CheckBox equipoCheck;
    @FXML private TextArea observacionesArea;
    @FXML private Label vistaPreviaLabel;
    @FXML private Label resumenLabel;

    @FXML private Label errNombre;
    @FXML private Label errModalidad;
    @FXML private Label errFecha;

    @FXML
    public void initialize() {
        modalidadCombo.getItems().addAll(
            "League of Legends (5v5)",
            "Dota 2 (5v5)",
            "EA Sports FC (1v1)",
            "Super Smash Bros (1v1)",
            "Valorant (5v5)",
            "Left 4 dead 2 (4v4)",
            "Overcooked 2 (4v4)"
        );

        // BINDING 3: la vista previa depende del nombre y de las observaciones.
        vistaPreviaLabel.textProperty().bind(
            Bindings.concat(
                "Vista previa: ", nombreField.textProperty(),
                " — ", observacionesArea.textProperty()
            ));

        // Comprobación al perder el foco: avisa, pero no impide seguir.
        nombreField.focusedProperty().addListener((observable, tenia, tiene) -> {
            if (!tiene) {
                validarNombre();
            }
        });
    }

    @FXML
    public void mostrarResumen(ActionEvent event) {
        // Solo presenta los datos: no valida ni añade nada.
        String textoNombre    = nombreField.getText();
        String valorModalidad = modalidadCombo.getValue();
        LocalDate valorFecha  = fechaPicker.getValue();
        String observaciones  = observacionesArea.getText();

        String nombre     = (textoNombre != null && !textoNombre.trim().isEmpty()) ? textoNombre : "Sin nombre";
        String modalidad  = (valorModalidad != null) ? valorModalidad : "Ninguna";
        String fecha      = (valorFecha != null) ? valorFecha.toString() : "No especificada";
        String traeEquipo = equipoCheck.isSelected() ? "Sí" : "No";
        String obs        = (observaciones != null && !observaciones.isBlank()) ? observaciones : "Sin observaciones";

        String resumen = "Resumen de la inscripción (todavía no añadida a la lista):"
                + "\n" + "Participante: " + nombre
                + "\n" + "Juego: " + modalidad
                + "\n" + "Fecha: " + fecha
                + "\n" + "Trae equipo propio: " + traeEquipo
                + "\n" + "Observaciones: " + obs;

        resumenLabel.setText(resumen);
        resumenLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
    }

    @FXML
    public void anadirInscripcion(ActionEvent event) throws IOException {
        // Se comprueban todos los campos para mostrar todos los errores.
        boolean nombreOk = validarNombre();
        boolean modalidadOk = validarModalidad();
        boolean fechaOk = validarFecha();

        // Foco al primer campo incorrecto; la colección no cambia.
        if (!nombreOk) {
            nombreField.requestFocus();
            return;
        }
        if (!modalidadOk) {
            modalidadCombo.requestFocus();
            return;
        }
        if (!fechaOk) {
            fechaPicker.requestFocus();
            return;
        }

        String observaciones = observacionesArea.getText().strip();
        InscripcionTorneo nueva = new InscripcionTorneo(
            nombreField.getText().strip(),
            modalidadCombo.getValue(),
            fechaPicker.getValue(),
            equipoCheck.isSelected(),
            observaciones
        );
        DatosInscripciones.getInscripciones().add(nueva);

        irAGestion(event);
    }

    @FXML
    public void cancelar(ActionEvent event) throws IOException {
        // Vuelve a la tabla sin modificar la colección.
        irAGestion(event);
    }

    @FXML
    public void limpiar(ActionEvent event) {
        nombreField.setText("");
        observacionesArea.setText("");
        resumenLabel.setText("");

        modalidadCombo.setValue(null);
        fechaPicker.setValue(null);

        equipoCheck.setSelected(false);

        errNombre.setText("");
        errModalidad.setText("");
        errFecha.setText("");
    }

    private boolean validarNombre() {
        String nombre = nombreField.getText().strip();
        if (nombre.isEmpty()) {
            errNombre.setText("Error: el nombre del participante es obligatorio.");
            return false;
        }
        errNombre.setText("");
        return true;
    }

    private boolean validarModalidad() {
        if (modalidadCombo.getValue() == null) {
            errModalidad.setText("Error: elige una modalidad de la lista.");
            return false;
        }
        errModalidad.setText("");
        return true;
    }

    private boolean validarFecha() {
        LocalDate fecha = fechaPicker.getValue();
        if (fecha == null) {
            errFecha.setText("Error: selecciona una fecha.");
            return false;
        }
        // Regla adicional: la fecha no puede ser anterior a hoy.
        if (fecha.isBefore(LocalDate.now())) {
            errFecha.setText("Error: la fecha no puede ser anterior a hoy.");
            return false;
        }
        errFecha.setText("");
        return true;
    }

    private void irAGestion(ActionEvent event) throws IOException {
        Parent vistaGestion = FXMLLoader.load(getClass().getResource("gestion.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(vistaGestion);
    }
}