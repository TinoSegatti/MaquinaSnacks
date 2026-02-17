package maquina_sanks_archivos.servicio;

import maquina_sanks_archivos.dominio.Snack;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


// Implementacion de IServicioSnacks que utiliza un archivo de texto como mecanismo de persistencia
 
public class ServicioSnacksArchivos implements IServicioSnacks{

    // Nombre del archivo fisico donde se persisten los snacks
    private static final String NOMBRE_ARCHIVO = "snacks.txt";

    // Lista en memoria que actua como cache del contenido del archivo
    private List<Snack> snacks = new ArrayList<>();

    /**
     * Constructor de la clase.
     * Al inicializar el servicio:
     *   Verifica si el archivo existe.
     *   Si existe, carga los snacks desde el archivo.
     *   Si no existe, lo crea y carga un inventario inicial por defecto.
     */
    public ServicioSnacksArchivos(){
        File archivo = new File(NOMBRE_ARCHIVO);
        boolean existe = false;
        try {
            existe = archivo.exists();
            if (existe) {
                // Si el archivo ya existe, cargamos los snacks persistidos
                this.snacks = obtenerSnacks();
            } else {
                // Si no existe, lo creamos vacio
                PrintWriter salida = new PrintWriter(new FileWriter(archivo));
                salida.close(); // Guarda el archivo en el disco
                System.out.println("Archivo creado exitosamente");
            }
        } catch (Exception e){
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }

        // Si el archivo no existia, inicializamos con algunos snacks por defecto
        if(!existe){
            cargarSnacksIniciales();
        }
    }

    // Carga un inventario inicial de ejemplo cuando el archivo se crea por primera vez
    // Este metodo demuestra como inicializar datos por defecto
    private void cargarSnacksIniciales(){
        this.agregarSnack(new Snack("Papas", 70));
        this.agregarSnack(new Snack("Refresco", 90));
        this.agregarSnack(new Snack("Sanguche", 120));
    }

    // Lee el archivo de texto y reconstruye la lista de snacks en memoria
    // Devuelve la lista de snacks leida desde el archivo
    private List<Snack> obtenerSnacks(){
        // Variable temporal para recuperar todos los snacks del archivo
        ArrayList<Snack> snacks = new ArrayList<>();
        try {
           List<String> lineas =  Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            for(String linea : lineas){
                // Cada línea tiene el formato: id,nombre,precio
                String [] lineaSnack = linea.split(","); // parseo separado por comas
                // El id se almacena pero no se utiliza directamente en esta version
                var idSnack = lineaSnack[0];
                var nombre = lineaSnack[1];
                var precio = Double.parseDouble(lineaSnack[2]);
                Snack snack = new Snack(nombre, precio);

                snacks.add(snack);
            }

        }catch (Exception e){
            System.out.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        return snacks;
    }

    // Agrega un nuevo snack al inventario
    // La operacion se realiza en dos pasos:
    @Override
    public void agregarSnack(Snack snack) {
        // 1. Se guarda en la lista en memoria
        this.snacks.add(snack);
        // 2. Se guarda el nuevo snack en el archivo
        this.agregarSnackArchivo(snack);
    }

    // Persiste un snack en el archivo de texto
    private void agregarSnackArchivo(Snack snack) {
        boolean anexar;

        File archivo = new File(NOMBRE_ARCHIVO);

        try {
            // Definimos si se agrega informacion o se sobrescribe el archivo
            anexar = archivo.exists();

            PrintWriter salida = new PrintWriter(new FileWriter(archivo, anexar));
            // Se escribe una linea con el formato CSV definido en Snack.escribirSnack()
            salida.println(snack.escribirSnack());
            salida.close(); // Se guarda en disco

        }catch (Exception e){
            System.out.println("Error al agregar snack: " + e.getMessage());
        }
    }

    // Muestra en consola el inventario completo de snacks
    @Override
    public void mostrarSnacks() {
        System.out.println("--- Snacks en el Inventario ---");
        var inventarioSnack = "";
        for (var snack : this.snacks) {
            inventarioSnack += snack.toString() + "\n";
        }
        System.out.println(inventarioSnack);
    }

    // Devuelve la lista de snacks actualmente cargada en memoria
    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }
}
