# Guía rápida de compilación y ejecución - Máquina de Snacks

## Requisitos

- Java **JDK 17+**
- Terminal (PowerShell, CMD, bash, etc.) o un IDE Java (IntelliJ, Eclipse, VS Code).

> Nota: El proyecto está organizado como módulo simple de IntelliJ (`MaquinaSnak.iml`) con `src` como raíz de código fuente.

---

## Estructura relevante

```text
MaquinaSnack/
├── snacks.txt
├── src/
│    ├── dominio/
│    │   └── Snack.java
│    ├── presentacion/
│    │     └── MaquinaSnacks.java
│    └── servicio/
│         ├── IServicioSnacks.java
│         ├── ServicioSnacksArchivos.java
│         └── ServicioSnacksLista.java
└── MaquinaSnak.iml
```

## Ejecución desde un IDE

### IntelliJ IDEA

1. Abrir IntelliJ.
2. `File` → `Open...` → seleccionar la carpeta `MaquinaSnack`.
3. Verificar que `src` está configurado como *Source Root* (ya definido en `MaquinaSnak.iml`).
4. Ejecutar la versión con archivos:
   - Abrir `maquina_sanks_archivos/presentacion/MaquinaSnacks.java`.
   - Click derecho → **Run 'MaquinaSnacks.main()'**.

### Eclipse

1. `File` → `Open Projects from File System...`.
2. Seleccionar la carpeta `MaquinaSnack`.
3. Click derecho sobre `MaquinaSnack` → `Build Project` (si es necesario).
4. Ejecutar:
   - `maquina_sanks_archivos.presentacion.MaquinaSnacks` como **Java Application**.

### VS Code

1. Instalar extensión **Extension Pack for Java**.
2. `File` → `Open Folder...` → seleccionar `MaquinaSnack`.
3. Abrir la clase `MaquinaSnacks`.
4. Usar el botón de **Run** sobre el método `main` o presionar `F5`.

---


