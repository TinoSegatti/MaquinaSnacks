package maquina_sanks_archivos.presentacion;

import maquina_sanks_archivos.dominio.Snack;
import maquina_sanks_archivos.servicio.IServicioSnacks;
import maquina_sanks_archivos.servicio.ServicioSnacksArchivos;
import maquina_sanks_archivos.servicio.ServicioSnacksLista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Capa de presentacion (UI) de la aplicacion de maquina de snacks
 * Esta clase se encarga de:
 *   Mostrar el menu en consola
 *   Leer y validar la entrada del usuario
 *   Orquestar las llamadas a la capa de servicio (IServicioSnacks)
 * Separacion de responsabilidades: la logica de negocio y persistencia se
 * delega a los servicios, y aca solo se gestiona la interaccion con el usuario.
 */
public class MaquinaSnacks {


    static void main(String[] args) {
        maquinaSnacks();
    }

    /**
     * Bucle principal de la aplicación de consola.
     * Responsabilidades:
     *   Crear el Scanner para leer desde consola.
     *   Instanciar la implementación de IServicioSnacks (archivo o lista).
     *   Mostrar el inventario inicial.
     *   Iterar mostrando el menú y ejecutando las opciones seleccionadas.
     */
    public static void maquinaSnacks(){
        boolean salir = false;
        Scanner sc = new Scanner(System.in);

        // Podemos alternar facilmente entre implementacion en memoria o en archivo
        //IServicioSnacks servicioSnacks = new ServicioSnacksLista();
        IServicioSnacks servicioSnacks = new ServicioSnacksArchivos();

        // Lista de productos seleccionados por el cliente (carrito de compras)
        List<Snack> productos = new ArrayList<>();

        System.out.println("--- Maquina de Snacks ---");
        servicioSnacks.mostrarSnacks(); // Muestra el inventario inical

        while(!salir){
            try {
                var opcion = mostrarMenu(sc);
                salir = ejecutarOpciones (opcion, sc, productos, servicioSnacks);
            }catch(Exception e){
                System.out.println("Ocurrio un error al intentar realizar la operacion: "+e.getMessage());
            }
            finally {
                System.out.println();
            }
        }
    }

    
    // Muestra el menu principal y devuelve la opcion seleccionada por el usuario.
    private static int mostrarMenu(Scanner sc){
        System.out.print("""
                Menu:
                1. Comprar Snack
                2. Mostrar Ticket
                3. Agregar Nuevo Snack
                4. Mostrar Inventario
                5. Salir
                Elige una opcion: """);
        return Integer.parseInt(sc.nextLine());
    }

    // Ejecuta la opcion seleccionada por el usuario.
    private static boolean ejecutarOpciones(int opcion, Scanner sc, List<Snack> productos,  IServicioSnacks servicioSnacks){
        boolean salir = false;
        switch (opcion){
            case 1 -> comprarSnack(sc, productos, servicioSnacks);
            case 2 -> mostrarTicket(productos);
            case 3 -> agregarSnack(sc, servicioSnacks);
            case 4 -> listarInventarioSnacks(servicioSnacks);
            case 5 -> {
                System.out.println("Regresa Pronto!");
                salir = true;
            }
            default -> System.out.println("Opcion Invalida: " + opcion);
        }
        return salir;
    }

    // Muestra el inventario completo de snacks.
    private static void listarInventarioSnacks(IServicioSnacks servicioSnacks){
        servicioSnacks.mostrarSnacks();
    }

    // Permite al usuario seleccionar un snack por ID y lo agrega al carrito.
    private static void comprarSnack(Scanner sc, List<Snack> productos, IServicioSnacks servicioSnacks){
        System.out.print("Que Snack quieres comprar (id): ");
        int idSnack = Integer.parseInt(sc.nextLine());

        boolean snackEncontrado = false;

        for (var snack : servicioSnacks.getSnacks()){
            if (idSnack == snack.getIdSnack()){
                productos.add(snack);
                System.out.println("Ok, Snack agregado exitosamente: " + snack);
                snackEncontrado = true;
                break;
            }
        }
        if (!snackEncontrado){
            System.out.println("Snack no encontrado: " + idSnack);
        }
    }

    // Muestra el ticket de venta con el detalle de productos y el total acumulado.
    private static void mostrarTicket(List<Snack> productos){
        var ticket = "*** Ticket de Venta ***";
        var total = 0.00;
        for (var producto : productos){
            ticket += "\n\t-" + producto.getNameSnack() + " - $" + producto.getPriceSnack();
            total += producto.getPriceSnack();
        }
        ticket += "\n\tTotal: $" + total;
        System.out.println(ticket);
    }

    // Permite agregar un nuevo snack al inventario a través del servicio.
    private static void agregarSnack(Scanner sd, IServicioSnacks servicioSnacks){
        System.out.print("Nombre del Snack: ");
        var nombre = sd.nextLine();

        System.out.print("Precio del Snack: ");
        var precio = Double.parseDouble(sd.nextLine());

        servicioSnacks.agregarSnack(new Snack(nombre, precio));
        System.out.println("Snack agregado exitosamente: " + nombre);
        servicioSnacks.mostrarSnacks();
    }
}
