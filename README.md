# Torneo de Videojuegos — Prácticas de Desarrollo de Interfaces

**Nombre:** Valdemar Sanchez

**Temática elegida:** Torneo de Videojuegos

------------------

# Entrega AC2.1

## Funcionalidades
- Navegación entre pantalla de inicio y formulario.
- Validación de campos vacíos con cambio de color.
- Actualización del resumen.
- Soporte completo para el teclado.
- CSS externo.

## Uso de IA
La usé para resolver un bloqueo de seguridad de Windows (Smart App Control) que no me dejaba arrancar el proyecto.

## Comprobaciones de accesibilidad
Los colores del CSS están escogidos para que cumplan con el contraste.

1. Texto normal sobre el fondo de la ventana:
   - Color de fondo: blanco (#ffffff)
   - Color de texto: gris oscuro (#333333)
   - Ratio de contraste medido: 12.6:1
2. Botón principal:
   - Color de fondo del botón: azul (#0056b3)
   - Color de texto del botón: blanco (#ffffff)
   - Ratio de contraste medido: 7.1:1

## Comprobaciones de usabilidad
- Datos agrupados lógicamente y bien alineados usando GridPane.
- La acción principal «Mostrar resumen» destaca visualmente frente al botón «Volver».
- La ficha técnica y los bocetos previos están guardados en la carpeta `/diseno` de mi proyecto.

-----------------

# Actividad AC2.2 — Gestión de inscripciones con tabla y formulario

Continuación de AC2.1. Ahora la aplicación permite consultar, crear y eliminar varias inscripciones durante una misma ejecución, usando objetos Java, una colección observable y una tabla. Los datos se guardan **solo en memoria**: al cerrar la aplicación se pierden y se recuperan los ejemplos iniciales. No se envía nada a ningún servicio real.

## Cómo ejecutar
- Requisitos: Java 21 y Maven (JavaFX).
- Desde Eclipse: clic derecho sobre `App.java` → *Run As* → *Java Application*.
- Por línea de comandos: `mvn javafx:run`.

## Recorrido
Bienvenida → Gestión de inscripciones → Nueva inscripción → Formulario → Añadir → Tabla actualizada.

Toda la navegación se hace en la misma ventana. Tamaño inicial 800×650, tamaño mínimo del Stage 700×600.

## Qué cambia respecto a AC2.1
- **Pantallas:** en AC2.1 había inicio y formulario. En AC2.2 se añade la pantalla de gestión con la tabla.
- **Botón de inicio:** en AC2.1 «Inscribirse» llevaba al formulario. En AC2.2 «Gestionar inscripciones» lleva a la tabla.
- **Datos:** en AC2.1 solo se leían los campos del formulario. En AC2.2 hay una clase `InscripcionTorneo` y una colección compartida en `DatosInscripciones`.
- **Formulario:** en AC2.1 «Mostrar resumen» era la acción principal. En AC2.2 la acción principal es «Añadir inscripción», «Mostrar resumen» solo presenta datos y se añade el botón «Cancelar».
- **Validación:** en AC2.1 se validaban campos vacíos con cambio de color. En AC2.2 hay mensajes junto a cada campo, foco al primer error, regla adicional de fecha y comprobación al perder el foco.
- **Bindings:** en AC2.1 no había ninguno. En AC2.2 hay tres (contador, botón eliminar y vista previa).
- **Ventana:** en AC2.1 se usaba el tamaño por defecto. En AC2.2 se fija un tamaño mínimo del Stage.
- **Archivos nuevos:** `InscripcionTorneo`, `DatosInscripciones`, `GestionController` y `gestion.fxml`.
- **Archivos modificados:** `App`, `InicioController`, `InscripcionController`, `inicio.fxml`, `inscripcion.fxml` y `estilos.css`.

## Funcionalidades realizadas
- Pantalla de gestión con `TableView` de cuatro columnas: participante, modalidad, fecha y equipo.
- Colección `ObservableList` compartida entre pantallas, con 6 inscripciones de ejemplo cargadas una sola vez.
- Clase `InscripcionTorneo` con tipos adecuados: `LocalDate` para la fecha, `boolean` para el equipo propio y `StringProperty` observable para el nombre.
- Cell value factories: propiedad directa para el nombre y wrappers para el resto.
- Cell factories: la fecha se muestra como `dd/MM/yyyy` y el booleano como «Trae su equipo» / «Usa el del torneo». Las celdas vacías se limpian.
- Ordenación desde los encabezados con `SortedList` enlazada al comparador de la tabla.
- Zona de detalles (incluidas las observaciones) que se actualiza al seleccionar con ratón o teclado. Sin selección muestra una indicación.
- Contador de registros y mensaje de tabla vacía.
- Alta desde formulario, con «Mostrar resumen», «Limpiar» y «Cancelar (volver sin añadir)».
- Eliminación de la inscripción seleccionada sobre la colección original, también con la tabla ordenada.
- CSS externo con foco visible en campos, tabla y botones.

## Funcionalidades pendientes
- Edición directa en la tabla (mejora optativa).
- `ListView` lateral de modalidades (mejora optativa).
- Deshacer la última eliminación (mejora optativa).

## Reglas de validación
- **Nombre:** obligatorio, no puede estar vacío ni contener solo espacios. Mensaje: «Error: el nombre del participante es obligatorio.»
- **Modalidad:** obligatoria. Mensaje: «Error: elige una modalidad de la lista.»
- **Fecha:** obligatoria. Mensaje: «Error: selecciona una fecha.»
- **Fecha (regla adicional):** no puede ser anterior a hoy, porque no tiene sentido inscribirse en una fecha pasada. Mensaje: «Error: la fecha no puede ser anterior a hoy.»

Comportamiento de los errores:
- Al pulsar «Añadir inscripción» se comprueban **todos** los campos y se muestra un mensaje junto a cada campo incorrecto.
- El foco va al primer campo incorrecto, en el orden del formulario.
- Se conservan los valores escritos y, si hay errores, la colección no cambia.
- Los mensajes se retiran cuando el campo pasa a ser correcto.
- Los errores empiezan por «Error:» además de ir en rojo, para no depender solo del color.
- Comprobación al perder el foco: el campo nombre se valida al salir de él, sin impedir seguir por el formulario.

## Data binding
Los bindings se configuran una sola vez en `initialize()` y no se modifican esas propiedades con `setText` ni con listeners.

1. **Contador de registros**
   - Propiedad vinculada: `contadorLabel.textProperty()`.
   - Depende de: el tamaño de la lista `inscripciones` (`Bindings.size(...).asString(...)`).
   - Por qué: el contador se actualiza solo al añadir o eliminar, sin escribir código en cada método.
2. **Botón «Eliminar seleccionada»**
   - Propiedad vinculada: `eliminarButton.disableProperty()`.
   - Depende de: que `selectedItemProperty()` de la tabla sea `null`.
   - Por qué: evita eliminar sin selección, el botón se deshabilita y habilita solo.
3. **Vista previa del formulario**
   - Propiedad vinculada: `vistaPreviaLabel.textProperty()`.
   - Depende de: `nombreField.textProperty()` y `observacionesArea.textProperty()` (`Bindings.concat(...)`).
   - Por qué: la etiqueta refleja lo que se escribe sin actualizarla a mano.

## Pruebas realizadas
1. Consultar los registros iniciales: [COMPLETADO]
2. Seleccionar diferentes filas y revisar detalles: [COMPLETADO]
3. Crear una inscripción válida: [COMPLETADO]
4. Intentar añadir con varios datos incorrectos: [COMPLETADO]
5. Corregir los errores y confirmar: [COMPLETADO]
6. Cancelar un alta sin modificar la colección: [COMPLETADO]
7. Volver entre pantallas sin perder ni duplicar registros: [COMPLETADO]
8. Ordenar y eliminar el registro correcto: [COMPLETADO]
9. Eliminar todos los registros y añadir uno después: [COMPLETADO]
10. Completar el recorrido con teclado: [COMPLETADO]
11. Comprobar el tamaño inicial y el mínimo permitido: [COMPLETADO]
12. Observaciones largas consultables completas: [COMPLETADO]

## Accesibilidad
- **Teclado:** recorrido con Tab entre campos, tabla y botones. En la tabla, las flechas cambian la selección y los detalles se actualizan.
- **Acción predeterminada:** «Añadir inscripción» en el formulario y «Nueva inscripción» en la gestión. Se activan con Enter desde un campo de una línea.
- **Salida del TextArea:** con Tab paso de campo a campo, pero dentro del `TextArea` de observaciones Tab inserta un tabulador. Para salir de él hay que pulsar **Ctrl+Tab**. Es el comportamiento estándar del `TextArea`, no hay bloqueo de teclado.
- «Eliminar seleccionada» no está asociado a ninguna tecla.
- **Foco visible:** borde naranja (#ff9900) en botones, campos de texto, selector de fecha, casilla y tabla.
- **Alternativas al color:** los errores empiezan por «Error:», el equipo se muestra con texto y el botón eliminar se deshabilita cuando no hay selección.
- **Redimensionado:** el Stage tiene tamaño mínimo 700×600 y la tabla ajusta sus columnas al ancho disponible.

**Contraste** (referencia mínima 4,5:1). Los colores de AC2.1 no han cambiado, se mantienen sus mediciones:

1. Texto normal sobre fondo de ventana:
   - Fondo: blanco (#ffffff)
   - Texto: gris oscuro (#333333)
   - Ratio de contraste: 12.6:1
2. Botón principal:
   - Fondo: azul (#0056b3)
   - Texto: blanco (#ffffff)
   - Ratio de contraste: 7.1:1
3. Mensajes de error (nuevo en AC2.2):
   - Fondo: blanco (#ffffff)
   - Texto: rojo oscuro (#b00020)
   - Ratio de contraste:  7,3:1

## Usabilidad
- Datos agrupados lógicamente con `GridPane`; cada campo con su etiqueta visible.
- La acción principal destaca frente a las secundarias («Volver», «Cancelar», «Limpiar»).
- Terminología coherente entre pantallas y acciones diferenciadas: consultar, añadir, cancelar y eliminar.
- Formatos comprensibles: fecha `dd/MM/yyyy` y el booleano como texto, nunca `true`/`false`.
- Los textos indican que los datos solo se guardan en memoria mientras la aplicación está abierta.
- La ficha de diseño actualizada y los bocetos iniciales están en `diseno/AC2.2`.

## Uso de IA
- En AC2.1: Resolver el bloqueo de Windows (Smart App Control).
- En AC2.2: Explicación de mejores prácticas con el codigo para table view y de comom comentar un fichero .md.