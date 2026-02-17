# Sistema de Máquina de Snacks (Vending Machine)

Aplicacion de consola en Java que simula una maquina expendedora de snacks, con inventario, seleccion de productos, generacion de ticket y persistencia en archivo de texto

## Descripcion

Este proyecto muestra cómo modelar una máquina de snacks utilizando Programación Orientada a Objetos (POO) y separación por capas:

- **Dominio**: `Snack` (modelo de datos).
- **Servicios**: `IServicioSnacks`, `ServicioSnacksLista`, `ServicioSnacksArchivos`.
- **Presentación (UI)**: `MaquinaSnacks` (interfaz de consola).

La versión actual del proyecto está organizada en un único módulo con capas claras (`dominio`, `servicio`, `presentacion`) y persistencia en archivo de texto mediante servicios.

## Características principales

- Menú interactivo por consola:
  - Comprar snack por ID.
  - Ver ticket de compra con total.
  - Agregar nuevos snacks al inventario.
  - Listar inventario actual.
- Dos estrategias de almacenamiento:
  - **En memoria** (`ServicioSnacksLista`).
  - **En archivo de texto** (`ServicioSnacksArchivos` + `snacks.txt`).
- Manejo de archivos con `File`, `FileWriter`, `PrintWriter`, `Files.readAllLines`.
- Uso de `List<Snack>` como colección principal.
- Manejo básico de excepciones al leer/escribir archivos y entradas de usuario.

## Estructura del proyecto

```text
MaquinaSnack/
├── snacks.txt                             # Archivo de datos (inventario persistido)
├── src/
│     ├── dominio/
│     │   └── Snack.java                   # Modelo de dominio con Serializable, equals, hashCode
│     ├── presentacion/
│     │   └── MaquinaSnacks.java           # UI de consola, orquesta el flujo
│     └── servicio/
│           ├── IServicioSnacks.java       # Interfaz (contrato) para servicios
│           ├── ServicioSnacksArchivos.java# Implementación con archivo de texto
│           └── ServicioSnacksLista.java   # Implementación en memoria
└── MaquinaSnak.iml                        # Configuración de módulo IntelliJ
```

## Conceptos y buenas prácticas demostradas

- **POO en Java**:
  - Encapsulamiento (`private` + getters/setters)
  - Uso de atributos estáticos para generar IDs únicos
  - Implementación de `equals` y `hashCode`
  - Implementación de `Serializable` en la entidad `Snack`

- **Diseño por capas**:
  - Dominio (`Snack`)
  - Servicios (`IServicioSnacks` y sus implementaciones)
  - Presentación (`MaquinaSnacks`)

- **Interfaces y polimorfismo**:
  - `IServicioSnacks` permite cambiar la implementacion (lista vs archivo) sin tocar la UI.

- **Manejo de archivos (I/O)**:
  - Lectura y escritura de texto plano (`snacks.txt`) con formato CSV `id,nombre,precio`.

- **Colecciones**:
  - Uso de `ArrayList` y bucles mejorados (`for-each`) para gestionar el inventario.

- **Manejo de excepciones**:
  - Control de errores al leer/escribir archivos.
  - Captura genérica de excepciones en la capa de presentación para evitar que la app se caiga.

## Cómo ejecutar el proyecto

### Ejecutar version con persistencia en archivo (`maquina_sanks_archivos`)

1. Abrir el proyecto en tu IDE.
2. Asegurarte de que el directorio `src` está marcado como *Source Root* (en IntelliJ ya está en `MaquinaSnak.iml`).
3. Ejecutar la clase:
   - `presentacion.MaquinaSnacks`
4. Se usará el archivo `snacks.txt` en la raíz del proyecto para:
   - Cargar el inventario si ya existe.
   - Crear el archivo y cargar snacks iniciales si no existe.

## Flujo de uso (versión con archivos)

1. Se muestra el inventario inicial (cargado desde `snacks.txt` o creado por defecto).
2. Menú principal:
   - **1. Comprar Snack**: el usuario ingresa el `id` del snack, se agrega a la lista de compras.
   - **2. Mostrar Ticket**: muestra todos los snacks comprados y el total a pagar.
   - **3. Agregar Nuevo Snack**: permite registrar un snack nuevo (nombre + precio) en memoria y en archivo.
   - **4. Mostrar Inventario**: vuelve a listar los snacks disponibles.
   - **5. Salir**: finaliza la aplicación.


## Posibles mejoras futuras

- Reemplazar el archivo de texto por una base de datos (JDBC/JPA).
- Añadir validación más robusta de entrada de usuario (evitar `NumberFormatException`).
- Implementar test unitarios para los servicios (`ServicioSnacksArchivos`, `ServicioSnacksLista`).
- Internacionalización de mensajes (soporte multi-idioma).
- Migrar a una interfaz gráfica (JavaFX o Swing) usando los mismos servicios.

---

Este proyecto es una buena muestra de POO, manejo de archivos y diseño por capas para mi perfil como desarrollador Java Junior.

