package maquina_sanks_archivos.servicio;

import maquina_sanks_archivos.dominio.Snack;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion de IServicioSnacks que almacena los datos solo en memoria.
 * Ideal para pruebas rapidas o escenarios donde no se necesita
 * persistencia en disco
 */
public class ServicioSnacksLista implements IServicioSnacks {

    // Lista estatic que mantiene los snacks en memoria
    private static final List<Snack> snacks;

    // Bloque estatic inicializador: se ejecuta una sola vez al cargar la clase
    static{
        snacks = new ArrayList<>();
        snacks.add(new Snack("Papas", 70));
        snacks.add(new Snack("CocaCola", 50));
        snacks.add(new Snack("Doritos", 120));
    }

    // Agrega un nuevo snack a la lista en memoria
    @Override
    public void agregarSnack(Snack snack){
        snacks.add(snack);
        System.out.println("Agregando snack");
    }

    // Muestra el inventario actual de snacks en consola
    @Override
    public void mostrarSnacks(){
        var inventarioSnacks = "";
        for (var snack : snacks){
            inventarioSnacks += snack.toString() + "\n";
        }
        System.out.println("--- Snacks Inventario ---");
        System.out.println(inventarioSnacks);
    }

    // Devuelve la lista de snacks almacenados en memoria
    @Override
    public List<Snack> getSnacks() {
        return snacks;
    }
}
