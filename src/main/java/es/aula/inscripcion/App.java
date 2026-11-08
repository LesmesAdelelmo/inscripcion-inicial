package es.aula.inscripcion;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            App.class.getResource("/es/aula/inscripcion/inicio.fxml")
        );
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 650);
        stage.setTitle("Torneo de Videojuegos - Gestión de inscripciones");
        stage.setMinWidth(700);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}