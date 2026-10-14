# Plantilla inicial: inscripción a una actividad

Proyecto mínimo para la práctica de consolidación de Desarrollo de Interfaces.
Incluye Java 21, JavaFX 21.0.12, Maven y una ventana con una etiqueta.

## 1. Importar en Eclipse

1. Descomprime el ZIP en una carpeta de trabajo. No trabajes dentro del ZIP.
2. En Eclipse: File > Import > Maven > Existing Maven Projects.
3. En Root Directory selecciona la carpeta `inscripcion-inicial` que contiene `pom.xml`.
4. Selecciona el proyecto y pulsa Finish. Espera a que Maven descargue las dependencias.

No necesitas instalar el SDK de JavaFX ni Maven por separado si tu Eclipse ya tiene integración Maven.
La primera ejecución necesita conexión a Internet para descargar las dependencias que falten.

## 2. Ejecutar

1. Clic derecho en el proyecto > Run As > Maven build…
2. En Goals escribe: `clean javafx:run`.
3. En la pestaña JRE selecciona el JDK 21 instalado (Alternate JRE).
4. Pulsa Apply y después Run.

La ventana debe mostrar «Mi aplicación de inscripción».
Para repetir: Run > Run Configurations > Maven Build y selecciona esta configuración guardada.
No ejecutes App mediante Run As > Java Application.

Si no aparece el JDK: Window > Preferences > Java > Installed JREs > Add > Standard VM,
selecciona su carpeta de instalación y vuelve a elegirlo en la configuración Maven.
El pom hace que la aplicación use el mismo Java con el que se ejecuta Maven.

## 3. Trabajar en la práctica

- Abre `src/main/resources/es/aula/inscripcion/inicio.fxml` desde Scene Builder (File > Open).
- Construye la bienvenida y crea la segunda vista en esa misma carpeta de recursos.
- Crea tus controladores dentro de `src/main/java/es/aula/inscripcion`.
- Añade tu hoja CSS junto a los FXML y enlázala desde las vistas o desde la Scene.
- Guarda en Scene Builder y actualiza el proyecto con F5 en Eclipse si fuera necesario.
- Ejecuta después de cada cambio importante.

La plantilla no incluye controlador, navegación, formulario ni CSS resueltos.
El FXML inicial no necesita controlador porque solo muestra una etiqueta.
Cuando añadas eventos, crea el controlador y asigna su nombre completo en Scene Builder.
Si conservas el paquete y la clase App no necesitas modificar el pom.xml.

## 4. Git y entrega

El ZIP no contiene un repositorio Git. Inicializa uno en esta carpeta con el procedimiento
utilizado en clase (en Eclipse: Team > Share Project > Git).
Haz un primer commit de la plantilla y otro significativo al finalizar cada sesión.
Entrega el proyecto sin la carpeta target. Completa este README con:

- Nombre del alumno:
- Temática elegida:
- Funcionalidades realizadas y pendientes:
- Comprobación por un compañero y mejora realizada:
- Uso de IA, si lo ha habido:

Referencia de configuración: https://openjfx.io/openjfx-docs/






Entrega AC.2.1

Nombre: Valdemar Sanchez
Temática elegida: Torneo de Videojuegos
Funcionalidades:
-Navegacion entre pantalla de inicio y formulario. 
-validacion de campos vacios con cambio de color. 
-Actualizacion del resumen.
-Soporte completo para el teclado.
-css externo.
Uso de IA: La use para resolver un bloqueo de seguridad de windows (Smart App Control)que no me dejaba arrancar el proyecto.

Comprobaciones de Accesibilidad:
Los colores del css son escojidos para que cumpla con del contraste
1.- Texto normla sobre el fondo de la ventana:
-Color de fondo: Blanco (#ffffff)
-Color de texto:Gris oscuro (#333333)
-Ratio de contraste medio: 12.6:1
2.- Boton principal:
-Color de fondo del boton: Azul (#0056b3)
-Color de texto del boton: Blanco (#ffffff)
-Ratio de contraste medio: 7.1:1

Comprobasiones de Usabilidad:
-Datosagrupados logicamente y bien alineados usando GridPane
-La accion principal mostrar resumen destaca visualmente frente al boton volvver
-La ficha tecnica y los bocetos previos están guardados en la carpeta /diseno de mi proyecto


