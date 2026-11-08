package es.aula.inscripcion;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class GestionController {

    @FXML private TableView<InscripcionTorneo> tabla;
    @FXML private TableColumn<InscripcionTorneo, String> colNombre;
    @FXML private TableColumn<InscripcionTorneo, String> colModalidad;
    @FXML private TableColumn<InscripcionTorneo, LocalDate> colFecha;
    @FXML private TableColumn<InscripcionTorneo, Boolean> colEquipo;

    @FXML private TextArea detalleArea;
    @FXML private Label contadorLabel;
    @FXML private Label mensajeLabel;
    @FXML private Button eliminarButton;

    private final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    public void initialize() {
        // Colección compartida: siempre es la misma, no se crea otra.
        ObservableList<InscripcionTorneo> inscripciones = DatosInscripciones.getInscripciones();

        // Cell value factories
        // Atributo observable: se devuelve directamente su propiedad.
        colNombre.setCellValueFactory(dato -> dato.getValue().nombreProperty());
        // Atributos ordinarios: se adaptan con wrappers.
        colModalidad.setCellValueFactory(dato ->
            new ReadOnlyStringWrapper(dato.getValue().getModalidad()));
        colFecha.setCellValueFactory(dato ->
            new ReadOnlyObjectWrapper<LocalDate>(dato.getValue().getFecha()));
        colEquipo.setCellValueFactory(dato ->
            new ReadOnlyObjectWrapper<Boolean>(dato.getValue().isEquipoPropio()));

        // Cell factory: la fecha sigue siendo LocalDate, solo cambia cómo se muestra.
        colFecha.setCellFactory(columna -> new TableCell<InscripcionTorneo, LocalDate>() {
            @Override
            protected void updateItem(LocalDate fecha, boolean empty) {
                super.updateItem(fecha, empty);
                if (empty || fecha == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(fecha.format(formato));
                    setGraphic(null);
                }
            }
        });

        // Cell factory: el booleano se muestra con un texto de la temática.
        colEquipo.setCellFactory(columna -> new TableCell<InscripcionTorneo, Boolean>() {
            @Override
            protected void updateItem(Boolean equipoPropio, boolean empty) {
                super.updateItem(equipoPropio, empty);
                if (empty || equipoPropio == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(equipoPropio ? "Trae su equipo" : "Usa el del torneo");
                    setGraphic(null);
                }
            }
        });

        // Ordenación: SortedList con el comparador vinculado al de la tabla.
        SortedList<InscripcionTorneo> ordenadas = new SortedList<>(inscripciones);
        ordenadas.comparatorProperty().bind(tabla.comparatorProperty());
        tabla.setItems(ordenadas);

        tabla.setPlaceholder(new Label(
            "No hay inscripciones. Pulsa «Nueva inscripción» para añadir una."));

        // Selección: listener que actualiza los detalles (ratón y teclado).
        tabla.getSelectionModel()
            .selectedItemProperty()
            .addListener((observable, anterior, actual) -> {
                mostrarDetalle(actual);
            });
        mostrarDetalle(null);

        // BINDING 1: contador de registros.
        contadorLabel.textProperty().bind(
            Bindings.size(inscripciones).asString("Total de inscripciones: %d"));

        // BINDING 2: «Eliminar seleccionada» deshabilitado si no hay selección.
        eliminarButton.disableProperty().bind(
            tabla.getSelectionModel().selectedItemProperty().isNull());
    }

    private void mostrarDetalle(InscripcionTorneo inscripcion) {
        if (inscripcion == null) {
            detalleArea.setText("Selecciona una inscripción de la tabla para ver sus detalles.");
            return;
        }
        String observaciones = inscripcion.getObservaciones();
        if (observaciones == null || observaciones.isBlank()) {
            observaciones = "Sin observaciones";
        }
        String equipo = inscripcion.isEquipoPropio() ? "Trae su propio mando / teclado"
                                                      : "Usará el equipo del torneo";
        detalleArea.setText(
            "Participante: " + inscripcion.getNombre()
            + "\nModalidad: " + inscripcion.getModalidad()
            + "\nFecha: " + inscripcion.getFecha().format(formato)
            + "\nEquipo: " + equipo
            + "\nObservaciones: " + observaciones);
    }

    @FXML
    public void nuevaInscripcion(ActionEvent event) throws IOException {
        Parent vistaInscripcion = FXMLLoader.load(getClass().getResource("inscripcion.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(vistaInscripcion);
    }

    @FXML
    public void eliminarSeleccionada(ActionEvent event) {
        InscripcionTorneo seleccionada = tabla.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mensajeLabel.setText("Selecciona una inscripción para eliminarla.");
            return;
        }
        // Se elimina el objeto de la colección original, no por índice.
        DatosInscripciones.getInscripciones().remove(seleccionada);
        mensajeLabel.setText("Inscripción de " + seleccionada.getNombre() + " eliminada de la lista.");
    }

    @FXML
    public void volverInicio(ActionEvent event) throws IOException {
        Parent vistaInicio = FXMLLoader.load(getClass().getResource("inicio.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(vistaInicio);
    }
}