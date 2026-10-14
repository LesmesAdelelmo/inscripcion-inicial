package es.aula.inscripcion;

import java.io.IOException;
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
    @FXML private Label resumenLabel;

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
    }

    @FXML
    public void mostrarResumen(ActionEvent event) {
        String textoNombre    = nombreField.getText();
        String valorModalidad = modalidadCombo.getValue();
        Object valorFecha     = fechaPicker.getValue();
        
        String nombre     = (textoNombre != null && !textoNombre.trim().isEmpty()) ? textoNombre : "Sin nombre";
        String modalidad  = (valorModalidad != null) ? valorModalidad : "Ninguna";
        String fecha      = (valorFecha != null) ? valorFecha.toString() : "No especificada";
        String traeEquipo = equipoCheck.isSelected() ? "Sí" : "No";
        
        String resumen = "Resumen de la inscripción:"
                + "\n" + "Participante:		  " + nombre
                + "\n" + "Juego:			  " + modalidad 
                + "\n" + "Fecha:			  " + fecha 
                + "\n" + "Trae equipo propio: " + traeEquipo;
        
        resumenLabel.setText(resumen);
        resumenLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
    }
    

    @FXML
    public void volverInicio(ActionEvent event) throws IOException {
        Parent vistaInicio = FXMLLoader.load(getClass().getResource("inicio.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(vistaInicio);
    }
}