package es.aula.inscripcion;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class InicioController {

    @FXML
    public void irAInscripcion(ActionEvent event) throws IOException {
        Parent vistaInscripcion = FXMLLoader.load(getClass().getResource("inscripcion.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(vistaInscripcion);
    }
}